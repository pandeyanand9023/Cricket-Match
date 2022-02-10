package com.tekion.match;
import com.tekion.match.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.tekion.match.Player.*;

public class Team extends MatchController{
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    private static final int teamSize=11;
    private int score;
    private int wickets;
    private int total_balls_played;
    private Teams name;
    private ArrayList<Player> Squad;
    Team(Teams name)
    {
        this.name=name;
        Squad=new ArrayList<Player>();
    }
    public Teams getName(){
        return this.name;
    }
    public ArrayList<Player> getSquad() {
     return Squad;
    }
    public void addWicket() {
        this.wickets++;
    }

    public int getWickets(){
        return wickets;
    }
    public void addRuns(int run) {
        this.score+=run;
    }

    public int getScore(){
        return score;
    }

    public void incrementBalls() {
        this.total_balls_played++;
    }

    public int getBalls(){
        return total_balls_played;
    }

    public void setTeams()  throws IOException{
        System.out.println("Time to select players of " + name);
        for (int i = 1; i <= teamSize; i++) {
            Player P;
            System.out.println("Enter the Player" + i + " name");
            String name = br.readLine();
            System.out.println("1. Batsman\n2. Bowler\n3. WicketKeeper");
            int num=Integer.parseInt(br.readLine());
            if(num==1)
            {
                P=new Player(Role.BATSMAN);
            }
            else if(num==2){
                P=new Player(Role.BOWLER);
            }
            else {
                P=new Player(Role.WICKET_KEEPER);
            }
            P.setName(name);
            Squad.add(P);
        }

    }
}
