package com.tekion.match;
import java.util.*;
public class Player {
    private String name;

    ENUM


    public void setName(String name){
     this.name=name;
    }
    public void setTeam(String team){
        this.team=team;
    }
    public void setBatsman(){
        this.Batsman=true;
    }
    public void setBowler(){
       this.Bowler=true;
    }
    public String getName() {
        return name;
    }
    public String getTeam() {
        return team;
    }
    public boolean isBatsman() {
       return Batsman;
    }
    public boolean isBowler(){
        return Bowler;
    }
}
