package com.tekion.cricketMatch.beans;

public class MatchDetails {
    int matchId;
    int teamOneId;
    int teamTwoId;
    String result;

    public MatchDetails(){
    /* Default constructor*/
    }

    public MatchDetails(int matchId, int teamOneId, int teamTwoId, String result){
        this.matchId=matchId;
        this.teamOneId=teamOneId;
        this.teamTwoId=teamTwoId;
        this.result=result;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public void setTeamOneId(int teamOneId) {
        this.teamOneId = teamOneId;
    }

    public void setTeamTwoId(int teamTwoId) {
        this.teamTwoId = teamTwoId;
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

    public void setResult(String result) {
        this.result=result;
    }

    public String getResult() {
        return this.result;
    }
}
