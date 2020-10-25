package models;

import java.util.ArrayList;
import java.util.List;

public class PetriNetObj {
    public String id;
    public List<String> input = new ArrayList<String>();
    public List<String> output = new ArrayList<String>();

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public List<String> getInput() {
        return input;
    }

    public void setInput(List<String> input) {
        this.input = input;
    }

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }
}
