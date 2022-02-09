package com.tekion.match;
import com.tekion.match.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MatchController extends Exception{
    private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

    public void startMatch() throws IOException {

        String teams[]={"India", "Sri Lanka" , "Pakistan", "Australia", "New Zealand", "England", "RSA", "Scotland", "West Indies", "Afghanistan" ,
                "Ireland"};

        int team1=(int)(Math.random()*10);
        int team2=(int)(Math.random()*10);
        while(team1==team2)
        {
            team2=(int)(Math.random()*10);
        }

        System.out.println("Welcome to today's match!!");
        System.out.println(teams[team1]+" vs "+teams[team2]);
        System.out.println("Select the number of overs ?");
        System.out.println("Press 1 : 10 Overs");
        System.out.println("Press 2 : 20 Overs");
        System.out.println("Press 3 : 50 Overs");

        /*
        Feedbacks
        1. Why was it throwing IO Exception?
        2. Create ENUM for Teams
        3. Break method (Modularity)
        4. Check user input for overs
        5. Final team size and other features
        6. A player doesn't need to have a team as its feature
        7. Use an ENUM for the role of a player
        8. Runs score, balls faced, bowls bowled, wickets taken,
        9. Match should be a seperate class. (which should have runs, wickets etc)
        10. Match Controller should only be concerned about squad etc.
        */

        int num=Integer.parseInt(br.readLine());

        Team t1=new Team();
        Team t2=new Team();
        ArrayList<Player> Squad1=t1.setTeams(teams[team1]);
        ArrayList<Player> Squad2=t2.setTeams(teams[team2]);
        ScoreBoard innings1=new ScoreBoard();
        int j=0;
        for(int i=1;i<=num*6 && j<11;i++)
        {
            int val1=(int)(Math.random()*6);
            int val2=(int)(Math.random()*6);
            if(val1==val2)
            {
                innings1.addWicket();
                System.out.println("Batsman Out !!");
                j++;
                if(j==11)
                    break;
                System.out.println("Next Batsman "+Squad1.get(j).getName());
            }
            else
            {
                innings1.setRuns(val1);
                System.out.println("Runs scored on this ball"+val1);
            }
            innings1.setBalls();
        }
        if(j!=11)
        {
            System.out.println("Innings Over!!");
        }
        j=0;
        ScoreBoard innings2=new ScoreBoard();

        for(int i=1;i<=num*6 && j<11;i++)
        {
            int val1=(int)(Math.random()*6);
            int val2=(int)(Math.random()*6);
            if(innings2.getRuns()>innings1.getRuns())
            {
                System.out.println(teams[team2]+" won the match !!");
                break;
            }
            if(val1==val2)
            {
                innings2.addWicket();
                System.out.println("Batsman Out !!");
                j++;
                if(j==11)
                    break;
                System.out.println("Next Batsman "+Squad2.get(j).getName());
            }
            else
            {
                innings1.setRuns(val1);
                System.out.println("Runs scored on this ball"+val1);
            }
            innings2.setBalls();
        }

        if(innings1.getRuns()>innings2.getRuns())
        {
            System.out.println(teams[team2]+" won the match !!");
        }

    }

}
