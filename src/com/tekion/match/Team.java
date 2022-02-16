package com.tekion.match;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.tekion.match.Player.*;
public class Team {
    private BufferedReader br;
    private static final int teamSize=11;
    private int score;
    private int wicketsFallen;
    private int wicketsTaken;
    private int totalBallsPlayed;
    private int numberOfBowlers=0;
    private CountryName name;
    private ArrayList<Player> squad;
    private int fallOfWicket[];
    private int strike=0;
    private int nonStrike=1;
    private int currentBowler=10;

    Team(BufferedReader br, CountryName name, String[] playerName, String[] playerType)throws IOException {
        this.br=br;
        this.name=name;
        fallOfWicket=new int[10];
        Arrays.fill(fallOfWicket,-1);
        squad =new ArrayList<Player>();
        setTeams(playerName, playerType);
    }

    public void getFallOfWicket(){
        if(fallOfWicket[0]==-1) {
            System.out.println("No wicket has fallen till now");
            return;
        }
        for(int i=0; i<10 && fallOfWicket[i]!=-1; i++){
            System.out.println("Wicket number "+i+" fell on "+fallOfWicket[i]);
        }
    }

    public void setFallOfWicket(int place){
        fallOfWicket[place-1]=score;
    }

    public void wicketsTaken(int currentBowler){
        this.wicketsTaken++;
        squad.get(currentBowler).addWicket();
    }

    public int getWicketsTaken(){
        return this.wicketsTaken;
    }

    public CountryName getName(){
        return this.name;
    }

    public ArrayList<Player> getSquad() {
     return squad;
    }

    public void fallOfWicket(){
        this.wicketsFallen++;
        setFallOfWicket(wicketsFallen);
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

    public void incrementBalls(int place) {
        this.totalBallsPlayed++;
        squad.get(place).incrementBalls();
    }

    public int getBallsPlayed(){
        return totalBallsPlayed;
    }

    public void changeStrike(){
        int temp=strike;
        strike=nonStrike;
        nonStrike=temp;
    }

    public int getCurrentBowler(){
        return currentBowler;
    }

    public void changeBowler() throws IOException{
        System.out.println("Select which player will bowl the next over ?");
        for(int i=0; i<11; i++){
            System.out.println("Press "+(i+1)+" for "+squad.get(i).getName());
        }
        String nextBowler=br.readLine();
        while( (nextBowler.equals(""+currentBowler)) || invalidBowlerNumber(nextBowler)){
            System.out.println("Select a valid number from 1-11 except  "+(currentBowler+1));
            nextBowler= br.readLine();
        }
           currentBowler=(Integer.parseInt(nextBowler)-1);
    }

    public int getStrike(){
        return strike;
    }

    public void setStrike(){
        this.strike=Math.max(strike,nonStrike)+1;
    }

    public void getPlayerWiseScore(){
        System.out.println("-------------------------------------");
        for(int i=0; i<teamSize; i++){
            System.out.println("Name : "+squad.get(i).getName());
            System.out.println("Runs Scored : "+squad.get(i).getRunsScored());
            System.out.println("Balls Played : "+squad.get(i).getBowlsPlayed());
            System.out.println("-------------------------------------");
        }
    }

    public void setTeams(String[] playerName, String[] playerType) throws IOException{
        for(int i=0; i<teamSize; i++) {
            Player newPlayer;
            if (playerType[i].equals("1")) {
                newPlayer = new Player(Role.BATSMAN, playerName[i]);
            } else if (playerType[i].equals("2")) {
                newPlayer = new Player(Role.BOWLER, playerName[i]);
            } else {
                newPlayer = new Player(Role.WICKET_KEEPER, playerName[i]);
            }
            squad.add(newPlayer);
        }
    }

    private boolean invalidBowlerNumber(String bowlerNumber){
        ArrayList<String> allowedValues=new ArrayList<>();
        for(int i=1; i<=11; i++){
            allowedValues.add(""+i);
        }
        return !(MatchUtil.validateInputs(bowlerNumber, allowedValues));
    }

}
