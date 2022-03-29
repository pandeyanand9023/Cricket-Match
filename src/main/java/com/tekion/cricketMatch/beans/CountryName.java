package com.tekion.cricketMatch.beans;

public class CountryName {
    int countryId;
    String countryName;

    public CountryName(){
    }
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

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
