package com.tekion.match;
import com.tekion.match.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MatchController extends Exception{
    private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
     enum Teams
    {       INDIA,
            SRI_LANKA ,
            PAKISTAN,
            AUSTRALIA,
            NEW_ZEALAND,
            ENGLAND,
            RSA,
            SCOTLAND,
            WEST_INDIES,
            AFGHANISTAN,
            IRELAND;

        public static Teams getRandomTeam() {
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }
    }

    public void startMatch() throws IOException {
        Teams team_one = Teams.getRandomTeam();
        Teams team_two= Teams.getRandomTeam();
        while(team_one.equals(team_two))
        {
            team_two=Teams.getRandomTeam();
        }

        System.out.println("Welcome to today's match!!");
        System.out.println(team_one+" vs "+team_two);
        System.out.println("Select the number of overs ?");
        System.out.println(" 10 Overs");
        System.out.println(" 20 Overs");
        System.out.println(" 50 Overs");


        int overs=Integer.parseInt(br.readLine());
        while(overs!=10 && overs!=20 && overs!=50)
        {
            System.out.println("Enter number of overs again");
            overs=Integer.parseInt(br.readLine());
        }

        Team team1=new Team(team_one);
        Team team2=new Team(team_two);

        team1.setTeams();
        team2.setTeams();

        Match m1=new Match(team1,team2,overs);
        m1.playMatch();
    }

}
