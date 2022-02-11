package com.tekion.match;
import java.util.Random;
public enum CountryName {
    INDIA,
    SRI_LANKA ,
    PAKISTAN,
    AUSTRALIA,
    NEW_ZEALAND,
    ENGLAND,
    RSA,
    SCOTLAND,
    WEST_INDIES,
    AFGHANISTAN,
    IRELAND;

    public static CountryName getRandomTeam() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
