package models;

public class Location extends PetriNetObj{
    private int tokens;

    public int getTokens() {
        return tokens;
    }

    public void setTokens(int tokens) {
        this.tokens = tokens;
    }

    public boolean decreaseTokens(){
        if(tokens==0){
            return false;
        } else {
            tokens--;
            return true;
        }
    }

    public void addToken(){
        tokens++;
    }
}
