package com.tekion.cricketMatch.dto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class Team {
    private static final int teamSize=11;
    private int teamId;
    private int score;
    private int wicketsFallen;
    private int wicketsTaken;
    private String teamName;
    private ArrayList<Player> squad;
    private int previousBowler;
    private HashMap<Integer, String> availableBowlers;
    private int scoresAtFallOfWicket[];
    private int maxOversAllowed;
    private int strike=0;
    private int nonStrike=1;
    private int currentBowler=10;

    Team(int teamId, String teamName, ArrayList<PlayerDTO> players,  int overs) {
        this.teamId=teamId;
        this.teamName=teamName;
        this.wicketsFallen=0;
        scoresAtFallOfWicket =new int[10];
        Arrays.fill(scoresAtFallOfWicket,-1);
        squad =new ArrayList<Player>();
        maxOversAllowed =overs/5;
        availableBowlers=new HashMap<>();
        previousBowler=-1;
        setTeams(players);
        setAvailableBowlers();
    }

    public void setTeams(ArrayList<PlayerDTO> players){
        for(int i=0;i<teamSize;i++){
            squad.add(new Player(players.get(i)));
        }
    }

    public int getTeamId() {
        return teamId;
    }
    public String getTeamName() {
        return teamName;
    }

    public int getScore() {
        return score;
    }

    public int getWicketsFallen() {
        return wicketsFallen;
    }

    public int getStrike() {
        return strike;
    }

    public ArrayList<Player> getSquad() {
        return squad;
    }

    public void incrementRuns(){
        score++;
    }
    public void incrementBallsPlayed(int strikeBatsman){
        getSquad().get(strikeBatsman).incrementBallsPlayed();
    }

    public void incrementRuns(int runs, int strikeBatsman){
        this.score+=runs;
        getSquad().get(strikeBatsman).incrementRuns(runs);
    }

    public int getCurrentBowler(){
        return this.currentBowler;
    }

    public int getPreviousBowler() {
        return previousBowler;
    }

    public void wicketsTaken(int bowlerId){
        getSquad().get(bowlerId).addWicket();
    }

    public int getNonStrike() {
        return nonStrike;
    }

    public void fallOfWicket(){
        scoresAtFallOfWicket[wicketsFallen]=score;
        wicketsFallen++;
    }
    public void setStrike(){
        strike=Math.max(getStrike(), getNonStrike())+1;
    }

    public void changeStrike(){
        int temp=strike;
        strike=nonStrike;
        nonStrike=temp;
    }


    public void changeBowler() {
        previousBowler=currentBowler;
        Random random=new Random();
        int val=random.nextInt(0,11);
        while(!availableBowlers.containsKey(val)){
            val=random.nextInt(0,11);
        }
        currentBowler=val;
    }

    private void setAvailableBowlers(){
        for(int i=0; i<11; i++){
            if(squad.get(i).getOversBowled()<maxOversAllowed){
                availableBowlers.put(i, squad.get(i).getName());
            }
        }
    }

    public void maintainsAvailableBowlers(){
        availableBowlers.remove(currentBowler);
        if(previousBowler!=-1 && squad.get(previousBowler).getOversBowled()<maxOversAllowed){
            availableBowlers.put(previousBowler, squad.get(previousBowler).getName());
        }
        changeBowler();
    }
}
