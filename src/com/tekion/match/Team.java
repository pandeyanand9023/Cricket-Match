package com.tekion.match;
import com.tekion.match.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.tekion.match.Player.*;
public class Team {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    private static final int teamSize=11;
    private int score;
    private int wickets;
    private int totalBallsPlayed;
    private Country name;
    private ArrayList<Player> Squad;
    private int strike=0;
    private int nonStrike=1;
    Team(Country name)
    {
        this.name=name;
        Squad=new ArrayList<Player>();
    }

    public Country getName(){
        return this.name;
    }
    public ArrayList<Player> getSquad() {
     return Squad;
    }

    public void fallOfWicket(){
        this.wickets++;
        System.out.println("Batsman Out !!");
    }

    public int getWickets(){
        return wickets;
    }

    public void incrementRuns(int run,int place) {
        this.score+=run;
        System.out.println("Runs scored on this ball" + run);
        Squad.get(place).incrementRuns(run);
    }

    public int getScore(){
        return score;
    }

    public void incrementBalls(int place) {
        this.totalBallsPlayed++;
        Squad.get(place).incrementBalls();
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

    public void setTeams(String [] input)  throws IOException{

        for(int i=0;i<input.length;i++) {
            Player newPlayer;
            String details[] = input[i].split("_");
            if (details[1].equals("1")) {
                newPlayer = new Player(Role.BATSMAN, details[0]);
            } else if (details[1].equals("2")) {
                newPlayer = new Player(Role.BOWLER, details[1]);
            } else {
                newPlayer = new Player(Role.WICKET_KEEPER, details[2]);
            }

            Squad.add(newPlayer);
        }
    }

}
