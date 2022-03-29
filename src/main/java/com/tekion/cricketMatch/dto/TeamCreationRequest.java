package com.tekion.cricketMatch.dto;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
public class TeamCreationRequest {
    private int matchId;

    private int teamOneId;

    private int teamTwoId;

    private ArrayList<PlayerDTO> teamOne;

    private ArrayList<PlayerDTO> teamTwo;

    TeamCreationRequest(int matchId, int teamOneId, int teamTwoId, ArrayList<PlayerDTO> teamOne, ArrayList<PlayerDTO> teamTwo) {
        this.matchId=matchId;
        this.teamOneId = teamOneId;
        this.teamTwoId = teamTwoId;
        this.teamOne = new ArrayList<>(teamOne);
        this.teamTwo = new ArrayList<>(teamTwo);
    }

    public int getTeamOneId() {
        return teamOneId;
    }

    public int getTeamTwoId() {
        return teamTwoId;
    }
    public int getMatchId() {
        return matchId;
    }

    public ArrayList<PlayerDTO> getTeamOne(){
        return teamOne;
    }

    public ArrayList<PlayerDTO> getTeamTwo(){
        return teamTwo;
    }
}
