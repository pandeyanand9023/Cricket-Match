package com.tekion.Cricket_Match.beans;

public class CountryName {
    int countryId;
    String countryName;

    public CountryName(int countryId, String countryName){
        this.countryId=countryId;
        this.countryName=countryName;
    }

    public int getCountryId(){
        return this.countryId;
    }

    public String getCountryName(){
        return this.countryName;
    }
}
