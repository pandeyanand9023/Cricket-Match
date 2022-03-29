package com.tekion.cricketMatch.dto;

import com.tekion.cricketMatch.enums.*;

public class Player {
    private int playerId;
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

    Player(PlayerDTO playerDTO) {
        this.playerId=playerDTO.getId();
        this.playerType=playerDTO.getPlayerType();
        this.bowlerType=playerDTO.getBowlerType();
        this.name=playerDTO.getName();
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

    public int getPlayerId() {
        return playerId;
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
