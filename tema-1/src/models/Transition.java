package models;

import java.util.Random;

public class Transition extends PetriNetObj{
    private int minTime;
    private int maxTime;
    private int delay;
    private int tempoTokens;

    public Transition(){
        delay = 0;
    }

    public void setMinTime(int minTime){
        this.minTime=minTime;
    }

    public void setMaxTime(int maxTime){
        this.maxTime=maxTime;
    }

    public int getDelay(){
        return delay;
    }

    public int getTempoTokens(){
        return tempoTokens;
    }

    public void addTempoToken(){
        tempoTokens++;
    }

    public boolean decreaseTokens(){
        if(tempoTokens == 0){
            return  false;
        } else {
            tempoTokens--;
            return true;
        }
    }

    public void setDelay(){
        if(minTime == 0 && maxTime == 0){
            System.out.println(id+" has no delay!");
        } else {
            Random r = new Random();
            delay = r.nextInt((maxTime - minTime) + 1) + minTime;
            System.out.println(id+" delay set: "+delay);
        }

    }

    public void decreaseDelay(){
        if(delay == 0){
            System.out.println(id+" delay is already 0");
        } else {
            delay--;
            System.out.println(id+" delay decreased ! now: "+delay);
        }
    }
}
