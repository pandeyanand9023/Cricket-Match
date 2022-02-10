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

    Player(Role role){
        this.role=role;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public int getRunsScored(){
        return this.runsScored;
    }
    public void addRuns(int runs){
        this.runsScored+=runs;
    }
    public void addBalls() {
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
