package com.tekion.cricketMatch.dto;

import com.tekion.cricketMatch.enums.BowlerType;
import com.tekion.cricketMatch.enums.PlayerType;

public class PlayerDTO {
    private int id;
    private String name;
    private PlayerType playerType;
    private BowlerType bowlerType;

    public PlayerDTO(int id, String name, PlayerType playerType, BowlerType bowlerType) {
        this.id=id;
        this.name=name;
        this.playerType=playerType;
        this.bowlerType=bowlerType;
    }
    public int getId() {
        return id;
    }
    public BowlerType getBowlerType() {
        return this.bowlerType;
    }

    public PlayerType getPlayerType() {
        return this.playerType;
    }
    public String getName() {
        return name;
    }
}
