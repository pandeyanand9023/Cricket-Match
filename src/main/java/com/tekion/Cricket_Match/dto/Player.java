package com.tekion.Cricket_Match.dto;

import java.util.Random;

public class Player {
     public enum Role{
     BATSMAN,
     BOWLER,
     ALL_ROUNDER;
    };
    enum BowlerType{
     FAST,
     MEDIUM_FAST,
     SPIN;
    };
    enum WayOfGettingOut{
        BOWLED,
        CAUGHT,
        RUN_OUT,
        HIT_WICKET,
        STUMPED;
         public static WayOfGettingOut getRandomWayOfGettingOut() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    };

    private String name;
    private Role playerType;
    private WayOfGettingOut wayOfGettingOut;
    private BowlerType bowlerType;
    private int runsScored;
    private int bowlsPlayed;
    private int bowlsBowled;
    private int numberOfWickets;
    private int overs;
    private boolean batsmanOut;

    Player(Role playerType, String name, BowlerType bowlerType) {
        this.playerType=playerType;
        this.bowlerType=bowlerType;
        this.name=name;
        this.runsScored=0;
        this.bowlsPlayed=0;
        this.bowlsBowled=0;
        this.numberOfWickets=0;
        this.overs=0;
        this.batsmanOut=false;
    }

    public String getName() {
        return name;
    }

    public Role getPlayerType() {
      return this.playerType;
    }

    public int getRunsScored(){
        return this.runsScored;
    }

    public void incrementRuns(int runs) {
        this.runsScored+=runs;
    }

    public void incrementBallsPlayed() {
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

    public void incrementOversBowled() {
        this.overs++;
    }

    public void setWayOfGettingOut(){
        this.wayOfGettingOut=WayOfGettingOut.getRandomWayOfGettingOut();
    }

    public BowlerType getBowlerType(){
        return this.bowlerType;
    }

    public int getBowlsBowled(){
        return bowlsBowled;
    }

    public void setBatsmanOut(){
        batsmanOut=true;
        setWayOfGettingOut();
    }

    public void incrementBallsBowled() {
        this.bowlsBowled++;
        if(bowlsBowled==6) {
            this.bowlsBowled=0;
            incrementOversBowled();
        }
    }

    public int getOversBowled(){
        return overs;
    }

}
