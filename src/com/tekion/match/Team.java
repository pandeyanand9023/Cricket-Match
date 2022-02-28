package com.tekion.match;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.*;

import static com.tekion.match.Player.*;
public class Team {
    private BufferedReader br;
    private static final int teamSize=11;
    private int teamId;
    private int score;
    private int wicketsFallen;
    private String name;
    private ArrayList<Player> squad;
    private int previousBowler;
    private HashMap<Integer, String> availableBowlers;
    private int scoresAtFallOfWicket[];
    private int maxOversAllowed;
    private int strike=0;
    private int nonStrike=1;
    private int currentBowler=10;

    Team(BufferedReader br, int teamId, String name, String[] playerName, String[] playerType, String[] bowlerType, int overs)throws IOException {
        this.br=br;
        this.name=name;
        this.teamId=teamId;
        scoresAtFallOfWicket =new int[10];
        Arrays.fill(scoresAtFallOfWicket,-1);
        squad =new ArrayList<Player>();
        maxOversAllowed =overs/5;
        availableBowlers=new HashMap<>();
        previousBowler=-1;
        setTeams(playerName, playerType, bowlerType);
        setAvailableBowlers();
    }

    public void getScoresAtFallOfWicket(){
        if(scoresAtFallOfWicket[0]==-1) {
            System.out.println("No wicket has fallen till now");
            return;
        }
        for(int i = 0; i<10 && scoresAtFallOfWicket[i]!=-1; i++){
            System.out.println("Wicket number "+i+" fell on "+ scoresAtFallOfWicket[i]);
        }
    }

    public void topPerformers(){
        int maxScore=0;
        int maxWicket=0;
        int highestScorer=0;
        int highestWicketTaker=0;
        for(int i=0;i<11;i++){
            if(squad.get(i).getRunsScored()>maxScore){
                maxScore=squad.get(i).getRunsScored();
                highestScorer=i;
            }
            if(squad.get(i).getNumberOfWickets()>maxWicket){
                maxWicket=squad.get(i).getNumberOfWickets();
                highestWicketTaker=i;
            }
        }
        System.out.println("Highest Scorer: "+squad.get(highestScorer).getName());
        System.out.println("Highest Scorer: "+squad.get(highestWicketTaker).getName());
    }

    public void setScoresAtFallOfWicket(int place){
        scoresAtFallOfWicket[place-1]=score;
    }

    public void wicketsTaken(int currentBowler){
        squad.get(currentBowler).addWicket();
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Player> getSquad() {
     return squad;
    }

    public void fallOfWicket(){
        this.wicketsFallen++;
        squad.get(strike).setBatsmanOut();
        setScoresAtFallOfWicket(wicketsFallen);
        System.out.println("Batsman Out !!");
    }

    public int getWicketsFallen(){
        return wicketsFallen;
    }

    public void incrementRuns(int run, int place) {
        this.score+=run;
        System.out.println("Runs scored on this ball " + run);
        squad.get(place).incrementRuns(run);
    }

    public void incrementRuns() {
        this.score++;
        System.out.println("1 Extra run added in the total");
    }

    public int getScore(){
        return score;
    }

    public int getPlayerScore(int batsmanNumber){
        return squad.get(batsmanNumber).getRunsScored();
    }

    public void incrementBallsPlayed(int place) {
        squad.get(place).incrementBallsPlayed();
    }

    public void changeStrike(){
        int temp=strike;
        strike=nonStrike;
        nonStrike=temp;
    }

    public int getTeamId(){
        return teamId;
    }

    public int getMaxOversAllowed(){
        return maxOversAllowed;
    }

    public int getCurrentBowler(){
        return currentBowler;
    }

    public void changeBowler() throws IOException{
        if(availableBowlers.size()==2 && exhaustOvers()) {
            System.out.println("Choose bowlers wisely ");
        }
        System.out.println("Select which player will bowl the next over ?");
        System.out.println(availableBowlers);
        String nextBowler=br.readLine();
        while(invalidBowlerNumber(nextBowler)){
            System.out.println("Select a valid choice from the above list");
            nextBowler= br.readLine();
        }
           previousBowler=currentBowler;
           currentBowler=Integer.parseInt(nextBowler);
    }
    private Player.BowlerType  setBowlerType(String bowlerType){
        if(bowlerType.equals("1")){
            return BowlerType.FAST;
        } else if(bowlerType.equals("2")){
            return BowlerType.MEDIUM_FAST;
        } else{
            return BowlerType.SPIN;
        }
    }

    public int getStrike(){
        return strike;
    }

    public void setStrike(){
        this.strike=Math.max(strike,nonStrike)+1;
    }

    public int getNonStrike(){
        return nonStrike;
    }

    public void getPlayerWiseScore(){
        System.out.println("-------------------------------------");
        for(int i=0; i<teamSize; i++){
            System.out.println("Name : "+squad.get(i).getName());
            System.out.println("Runs Scored : "+squad.get(i).getRunsScored());
            System.out.println("Balls Played : "+squad.get(i).getBowlsPlayed());
            System.out.print("Overs Bowled: "+squad.get(i).getOversBowled());
            if(squad.get(i).getBowlsBowled()!=0) {
                System.out.println("."+squad.get(i).getBowlsBowled());
            } else {
                System.out.println();
            }
            System.out.println("Wickets Taken: "+squad.get(i).getNumberOfWickets());
            System.out.println("-------------------------------------");
        }
    }

    @Override
    public String toString() {
        return "Team{" +
                " score=" + score +
                ", wicketsFallen=" + wicketsFallen +
                ", Batting Team=" + name +
                ", maxOversAllowed=" + maxOversAllowed +
                ", strike=" + strike +
                ", nonStrike=" + nonStrike +
                '}';
    }

    public void maintainsAvailableBowlers() throws IOException{
        availableBowlers.remove(currentBowler);
        if(previousBowler!=-1 && squad.get(previousBowler).getOversBowled()<maxOversAllowed){
            availableBowlers.put(previousBowler, squad.get(previousBowler).getName());
        }
        changeBowler();
    }

    public void setTeams(String[] playerName, String[] playerType, String[] bowlerType) throws IOException{
        for(int i=0; i<teamSize; i++) {
            Player newPlayer;
            if (playerType[i].equals("1")) {
                newPlayer = new Player(Role.BATSMAN, playerName[i], setBowlerType(bowlerType[i]));
            } else if(playerType[i].equals("2")) {
                newPlayer = new Player(Role.BOWLER, playerName[i], setBowlerType(bowlerType[i]));
            } else {
                newPlayer =new Player(Role.ALL_ROUNDER, playerName[i], setBowlerType(bowlerType[i]));
            }
            squad.add(newPlayer);
        }
    }
    private boolean exhaustOvers(){
         boolean moreThanOneOvers=false;
         boolean onlyOneOver=false;
         for(Integer playerNumber: availableBowlers.keySet()){
             if(squad.get(playerNumber).getOversBowled()==1){
                 onlyOneOver=true;
             } else {
                 moreThanOneOvers=true;
             }
         }
         return (onlyOneOver && moreThanOneOvers);
    }

    private void setAvailableBowlers(){
        for(int i=0; i<11; i++){
            if(squad.get(i).getOversBowled()<maxOversAllowed){
                availableBowlers.put(i, squad.get(i).getName());
            }
        }
    }

    private boolean invalidBowlerNumber(String bowlerNumber){
        ArrayList<String> allowedValues=new ArrayList<>();
        for(Integer playerNumber: availableBowlers.keySet()){
            allowedValues.add(""+playerNumber);
        }
        return !(MatchUtil.validateInputs(bowlerNumber, allowedValues));
    }
}
