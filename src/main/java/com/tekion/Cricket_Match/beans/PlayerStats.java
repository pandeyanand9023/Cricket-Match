package com.tekion.Cricket_Match.beans;

public class PlayerStats {
    int matchId;
    int playerId;
    int teamId;
    int runsScored;
    String oversBowled;
    int wicketsTaken;

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
}
