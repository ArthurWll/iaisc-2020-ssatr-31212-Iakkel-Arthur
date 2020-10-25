package simulator;

import models.Location;
import models.Transition;

import java.io.PrintWriter;

public class Simulator {
    private int steps;

    public void setSteps(int steps){
        this.steps=steps;
    }

    private boolean checkInputTokens(Transition transition){
        boolean status = true;
        if(transition.getInput().size() == 0)
            return false;
        for(int i = 0; i < transition.getInput().size(); i++){
            Location location = PetriNet.getInstance().getLocationById(transition.getInput().get(i));
            if(location.getTokens() == 0)
                status = false;
        }
        return status;
    }

    private void takeInputTokens(Transition transition){
        for(int i = 0; i < transition.getInput().size(); i++){
            Location location = PetriNet.getInstance().getLocationById(transition.getInput().get(i));
            if(location.decreaseTokens())
                System.out.println("Token taken from "+location.getID());

            else
                System.out.println("Error ! No tokens in "+location.getID());
        }
        transition.addTempoToken();
        System.out.println("Token added in "+transition.getID());
        transition.setDelay();
    }

    private void putOutputTokens(Transition transition){
        if(transition.getDelay() == 0) {
            if (transition.decreaseTokens()) {
                System.out.println("Temp token taken from " + transition.getID());
                for (int i = 0; i < transition.getOutput().size(); i++) {
                    Location location = PetriNet.getInstance().getLocationById(transition.getOutput().get(i));
                    location.addToken();
                    System.out.println("Token added in " + location.getID());
                }
            } else {
                //System.out.println("No temp token in " + transition.getId());
            }
        } else {
            System.out.println(transition.getID()+" has delay: "+transition.getDelay());
        }
    }

    private void checkAllTransitions(){
        for (int i = 0; i < PetriNet.getInstance().getTransitions().size(); i++) {

            Transition transition = PetriNet.getInstance().getTransitions().get(i);

            if(transition.getDelay() > 0){
                transition.decreaseDelay();
            }

            if(transition.getTempoTokens()== 0) {
                if (true == checkInputTokens(transition)) {
                    System.out.println("Valid input for " + transition.getID());
                    takeInputTokens(transition);
                } else {
                    //System.out.println("No valid input for " + transition.getId());
                }
            } else
                System.out.println("Transition "+transition.getID()+" already activated");
        }
        for (int i = 0; i < PetriNet.getInstance().getTransitions().size(); i++) {
            Transition transition = PetriNet.getInstance().getTransitions().get(i);
            putOutputTokens(transition);
        }
    }

    public void simulatePetriNet(PrintWriter printWriter){
        for(int i = 0; i < steps; i++){
            printPetriNetState(i, printWriter);
            checkAllTransitions();
        }
    }

    private void printPetriNetState(int i, PrintWriter printWriter){
        printWriter.print("STEP "+i+" : ");
        PetriNet.getInstance().getLocations().forEach(location ->{
            System.out.print("| "+location.getID() + ": "+location.getTokens()+" ");
            printWriter.print("| "+location.getID() + ": "+location.getTokens()+" ");
        });
        printWriter.println();
        System.out.println();
        PetriNet.getInstance().getTransitions().forEach(
                transition -> System.out.print("| "+transition.getID()+" : "+transition.getTempoTokens()+" ")
        );
        System.out.println();
    }
}
