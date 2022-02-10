package com.tekion.match;
import com.tekion.match.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MatchController extends Exception{
    private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public void startMatch() throws IOException {
        Country team_one = Country.getRandomTeam();
        Country team_two= Country.getRandomTeam();

        while(team_one.equals(team_two))
        {
            team_two=Country.getRandomTeam();
        }

        System.out.println("Welcome to today's match!!\n"+team_one+" vs "+team_two);
        System.out.println("Select the number of overs ?\n10 Overs \n20 Overs \n30 Overs");
        int overs=Integer.parseInt(br.readLine());

        while(!validateOvers(overs))
        {
            System.out.println("Enter number of overs again");
            overs=Integer.parseInt(br.readLine());
        }


        Team team1=new Team(team_one);
        Team team2=new Team(team_two);
        team1.setTeams(takeInput(team_one));
        team2.setTeams(takeInput(team_two));
        Match m1=new Match(team1,team2,overs);
        m1.playMatch();
    }

    private boolean validateOvers(int Overs)
    {
        if(Overs!=10 && Overs!=20 && Overs!=30)
            return false;
        else
            return true;
    }
    private String[] takeInput(Country c) throws IOException{
        String[] input=new String[11];
        System.out.println("Time to select players of " + c);

        for(int i=1;i<=11;i++) {
            System.out.println("Enter the Player" + i + " name");
            String name = br.readLine();
            System.out.println("1. Batsman\n2. Bowler\n3. WicketKeeper");
            int num = Integer.parseInt(br.readLine());
            while(!isValid(num)){
                num=Integer.parseInt(br.readLine());
            }
            input[i]=name+"_"+num;
        }
        return input;
    }

    private boolean isValid(int num){
        if(num<=0 || num>3)
            return false;

        return true;
    }

}