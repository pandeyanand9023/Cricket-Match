package com.tekion.cricketMatch.enums;

import java.util.Random;

public enum WayOfGettingOut {
    BOWLED,
    CAUGHT,
    RUN_OUT,
    HIT_WICKET,
    STUMPED;
    public static WayOfGettingOut getRandomWayOfGettingOut() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}

