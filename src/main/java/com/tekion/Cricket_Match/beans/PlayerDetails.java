package com.tekion.Cricket_Match.beans;

public class PlayerDetails {
    int playerId;
    int teamId;
    String playerName;
    String playerType;
    String bowlingType;

    PlayerDetails(int playerId, int teamId, String playerName, String playerType, String bowlingType){
        this.playerId= playerId;
        this.teamId= teamId;
        this.playerName=playerName;
        this.playerType=playerType;
        this.bowlingType=bowlingType;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public String getBowlingType() {
        return bowlingType;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getPlayerType() {
        return playerType;
    }
}
