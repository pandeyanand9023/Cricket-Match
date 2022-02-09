package com.tekion.match;
import com.tekion.match.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Team {

    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    public ArrayList<Player> setTeams(String team1)  throws IOException{

        ArrayList<Player> Team1=new ArrayList<Player>();
        System.out.println("Time to select players of " + team1);
        for (int i = 1; i <= 11; i++) {
            System.out.println("Enter the Player" + i + " name");
            String name = br.readLine();
            System.out.println("1. Batsman\n2. Bowler");
            int x = Integer.parseInt(br.readLine());
            if (x == 1) {
                Batsman b = new Batsman();
                b.setName(name);
                b.setTeam(team1);
                b.setBatsman();
                Team1.add(b);
            } else {
                Bowler b = new Bowler();
                b.setName(name);
                b.setTeam(team1);
                b.setBowler();
                Team1.add(b);
            }
        }
        return Team1;
    }
}
