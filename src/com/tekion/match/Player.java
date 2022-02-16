package com.tekion.match;
public class Player {
     enum Role{
     BATSMAN,
     BOWLER,
     WICKET_KEEPER;
    };

    private String name;
    private Role playerType;
    private int runsScored;
    private int bowlsPlayed;
    private int numberOfWickets;
    private int overs;

    Player(Role playerType, String name){
        this.playerType=playerType;
        this.name=name;
        this.runsScored=0;
        this.bowlsPlayed=0;
        this.numberOfWickets=0;
        this.overs=0;
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

    public int getOvers() {
        return this.overs;
    }

}
