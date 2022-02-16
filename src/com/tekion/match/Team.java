package com.tekion.match;
import java.io.IOException;
import java.util.ArrayList;

import static com.tekion.match.Player.*;
public class Team {

    private static final int teamSize=11;
    private int score;
    private int wickets;
    private int totalBallsPlayed;
    private CountryName name;
    private ArrayList<Player> squad;
    private int strike=0;
    private int nonStrike=1;

    Team(CountryName name, String[] playerName, String[] playerType)throws IOException {
        this.name=name;
        squad =new ArrayList<Player>();
        setTeams(playerName, playerType);
    }

    public CountryName getName(){
        return this.name;
    }

    public ArrayList<Player> getSquad() {
     return squad;
    }

    public void fallOfWicket(){
        this.wickets++;
        System.out.println("Batsman Out !!");
    }

    public int getWickets(){
        return wickets;
    }

    public void incrementRuns(int run, int place) {
        this.score+=run;
        System.out.println("Runs scored on this ball " + run);
        squad.get(place).incrementRuns(run);
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

}
