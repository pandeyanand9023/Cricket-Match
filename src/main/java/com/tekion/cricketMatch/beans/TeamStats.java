package com.tekion.cricketMatch.beans;

public class TeamStats {
    int matchId;
    int teamId;
    int runs;
    int wicketsTaken;

    public TeamStats(){
     /*Default

      */
    }

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

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }
}
