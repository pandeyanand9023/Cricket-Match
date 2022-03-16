package com.tekion.Cricket_Match.beans;

public class TeamStats {
    int matchId;
    int teamId;
    int runs;
    int wicketsTaken;

    public TeamStats(int matchId, int teamId, int runs, int wicketsTaken){
        this.matchId=matchId;
        this.teamId=teamId;
        this.runs=runs;
        this.wicketsTaken=wicketsTaken;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public int getRuns() {
        return runs;
    }
}
