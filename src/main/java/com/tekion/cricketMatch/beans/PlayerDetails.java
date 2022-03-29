package com.tekion.cricketMatch.beans;

public class PlayerDetails {
    int playerId;
    int teamId;
    String playerName;
    /**
    {@link com.tekion.cricketMatch.enums.PlayerType}
    */
    String playerType;
    /**
     {@link com.tekion.cricketMatch.enums.BowlerType}
     */
    String bowlingType;

    PlayerDetails(int playerId, int teamId, String playerName, String playerType, String bowlingType){
        this.playerId= playerId;
        this.teamId= teamId;
        this.playerName=playerName;
        this.playerType=playerType;
        this.bowlingType=bowlingType;
    }

    public PlayerDetails(){

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

    public void setBowlingType(String bowlingType) {
        this.bowlingType = bowlingType;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
