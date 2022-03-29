package com.tekion.cricketMatch.beans;

public class PlayerStats {
    int matchId;
    int playerId;
    int teamId;
    int runsScored;
    String oversBowled;
    int wicketsTaken;

    public PlayerStats(){
        //Default constructor just for use
    }

    public PlayerStats(int matchId, int playerId, int teamId, int runsScored, String oversBowled, int wicketsTaken){
        this.matchId= matchId;
        this.playerId= playerId;
        this.teamId= teamId;
        this.runsScored=runsScored;
        this.oversBowled=oversBowled;
        this.wicketsTaken=wicketsTaken;
    }

    public int getMatchId() {
        return matchId;
    }

    public String getOversBowled() {
        return oversBowled;
    }

    public int getPlayerId() {
        return playerId;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setOversBowled(String oversBowled) {
        this.oversBowled = oversBowled;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }
}
