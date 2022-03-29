package com.tekion.cricketMatch.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class MatchCreationResponse {
    @JsonView
    private int matchId;
    @JsonView
    private int teamOneId;
    @JsonView
    private String teamOneName;
    @JsonView
    private int teamTwoId;
    @JsonView
    private String teamTwoName;
    @JsonView
    private int overs;

    public MatchCreationResponse(int matchId, int teamOneId, String teamOneName, int teamTwoId, String teamTwoName, int overs){
        this.matchId=matchId;
        this.teamOneId=teamOneId;
        this.teamOneName=teamOneName;
        this.teamTwoId=teamTwoId;
        this.teamTwoName=teamTwoName;
        this.overs=overs;
    }
}
