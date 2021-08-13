package id.ac.polman.astra.serojamatchmaker.model;

import java.util.ArrayList;
import java.util.List;

public class BracketArrayed {
    private BracketCard[] bracket = new BracketCard[50];


    public void addBracket(BracketCard brckt, int index){
        this.bracket[index] = brckt;
    }

    public BracketCard[] getBracket() {
        return bracket;
    }

    public void setBracket(BracketCard[] bracket) {
        this.bracket = bracket;
    }
}
