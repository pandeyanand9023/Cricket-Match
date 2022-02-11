package com.tekion.match;
import java.util.*;
public class Player {
     enum Role{
     BATSMAN,
     BOWLER,
     WICKET_KEEPER;
    };

    private String name;
    private Role role;
    private int runsScored;
    private int bowlsPlayed;
    private int numberOfWickets;

    Player(Role role, String name){
        this.role=role;
        this.name=name;
        this.runsScored=0;
        this.bowlsPlayed=0;
        this.numberOfWickets=0;
    }

    public String getName() {
        return name;
    }

    public int getRunsScored(){
        return this.runsScored;
    }

    public void incrementRuns(int runs) {
        this.runsScored+=runs;
    }

     public void incrementBalls() {
        this.bowlsPlayed++;
    }

    public void addWicket(){
        this.numberOfWickets++;
    }
    public int getBowlsPlayed(){
        return bowlsPlayed;
    }

    public int getNumberOfWickets(){
        return this.numberOfWickets;
    }

}
