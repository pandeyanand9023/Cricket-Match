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
        Country teamOne = Country.getRandomTeam();
        Country teamTwo= Country.getRandomTeam();

        while(teamOne.equals(teamTwo))
        {
            teamTwo=Country.getRandomTeam();
        }

        System.out.println("Welcome to today's match!!\n"+teamOne+" vs "+teamTwo);
        System.out.println("Select the number of overs ?\n10 Overs \n20 Overs \n30 Overs");
        int overs=Integer.parseInt(br.readLine());

        while(!validateOvers(overs))
        {
            System.out.println("Enter number of overs again");
            overs=Integer.parseInt(br.readLine());
        }


        Team team1=new Team(teamOne);
        Team team2=new Team(teamTwo);

        team1.setTeams(setPlayerDetails(teamOne));
        team2.setTeams(setPlayerDetails(teamTwo));

        Match match=new Match(team1,team2,overs);
        match.playMatch();
    }

    private boolean validateOvers(int overs)
    {
        if(overs!=10 && overs!=20 && overs!=30)
            return false;
        else
            return true;
    }

    private String[] setPlayerDetails(Country c) throws IOException{
        String[] playerDetails=new String[11];
        System.out.println("Time to select players of " + c);

        for(int i=1;i<=11;i++) {
            System.out.println("Enter the Player" + i + " name");
            String name = br.readLine();
            System.out.println("1. Batsman\n2. Bowler\n3. WicketKeeper");
            int choice = Integer.parseInt(br.readLine());
            while(!isValid(choice)){
                choice=Integer.parseInt(br.readLine());
            }
            playerDetails[i]=name+"_"+choice;
        }
        return playerDetails;
    }

    private boolean isValid(int choice){
        if(choice<=0 || choice>3)
            return false;

        return true;
    }

}