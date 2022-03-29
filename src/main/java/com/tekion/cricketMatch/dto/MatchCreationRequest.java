package com.tekion.cricketMatch.dto;

public class MatchCreationRequest {
    private int matchId;
    private int teamOneId;
    private int teamTwoId;
    private int overs;

    MatchCreationRequest(int matchId, int teamOneId, int teamTwoId, int overs){
        this.matchId=matchId;
        this.teamOneId=teamOneId;
        this.teamTwoId=teamTwoId;
        this.overs=overs;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getTeamTwoId() {
        return teamTwoId;
    }

    public int getTeamOneId() {
        return teamOneId;
    }

    public int getOvers() {
        return overs;
    }
}
