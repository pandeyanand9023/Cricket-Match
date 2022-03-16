package com.tekion.Cricket_Match.dto;

import com.tekion.Cricket_Match.enums.*;

public class Player {
    private String name;
    private PlayerType playerType;
    private WayOfGettingOut wayOfGettingOut;
    private BowlerType bowlerType;
    private int runsScored;
    private int bowlsPlayed;
    private int bowlsBowled;
    private int numberOfWickets;
    private int overs;
    private boolean batsmanOut;

    Player(PlayerType playerType, String name, BowlerType bowlerType) {
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

    public PlayerType getPlayerType() {
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
