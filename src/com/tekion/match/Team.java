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
    private int total_balls_played;
    private Country name;
    private ArrayList<Player> Squad;
    private int strike=0;
    private int non_strike=1;

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
        this.total_balls_played++;
        Squad.get(place).incrementBalls();
    }

    public int getTotalBalls(){

        return total_balls_played;
    }
    public void changeStrike(){
        int temp=strike;
        strike=non_strike;
        non_strike=temp;
    }
    public int getStrike(){
        return strike;
    }

    public void setStrike(){
        this.strike=Math.max(strike,non_strike)+1;
    }

    public void setTeams(String [] input)  throws IOException{

        for(int i=0;i<input.length;i++) {
            Player P;
            String str[] = input[i].split("_");
            if (str[1].equals("1"))
                P = new Player(Role.BATSMAN, str[0]);
            else if (str[1].equals("2")) {
                P = new Player(Role.BOWLER, str[1]);
            } else {
                P = new Player(Role.WICKET_KEEPER, str[2]);
            }

            Squad.add(P);
        }
    }

}
