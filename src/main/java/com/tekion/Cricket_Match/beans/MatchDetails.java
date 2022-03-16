package com.tekion.Cricket_Match.beans;

public class MatchDetails {
    int matchId;
    int teamOneId;
    int teamTwoId;
    String result;

    public MatchDetails(int matchId, int teamOneId, int teamTwoId, String result){
        this.matchId=matchId;
        this.teamOneId=teamOneId;
        this.teamTwoId=teamTwoId;
        this.result=result;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getTeamOneId() {
        return teamOneId;
    }

    public int getTeamTwoId() {
        return teamTwoId;
    }

    public String getResult() {
        return result;
    }
}
