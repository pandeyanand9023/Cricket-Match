package com.tekion.match;
import java.util.*;
public class ScoreBoard {
    private int runs=0;
    private int wickets=0;
    private int overs=0;
    private int balls=0;
    public void setRuns(int runs){
        this.runs+=runs;
    }
    public void addWicket(){
        this.wickets++;
        if(this.wickets==10)
            System.out.println("Innings Over!!");
    }
    public void setBalls(){
        this.balls++;
        if(this.balls==6) {
            this.overs++;
            this.balls=0;
        }
    }
    public int getOvers() {
        return overs;
    }
    public int getBalls() {
        return this.balls;
    }
    public int getRuns() {
        return this.runs;
    }
    public int getWickets(){
        return this.wickets;
    }
}
