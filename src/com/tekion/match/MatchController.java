package com.tekion.match;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class MatchController extends Exception{
    private BufferedReader br;
    Match match;
    public MatchController(BufferedReader br) throws IOException{
        this.br=br;
        match = initializeMatch();
    }

    public void startMatch(){
        match.playMatch();
    }

    public Match initializeMatch() throws IOException {
        CountryName teamOne = CountryName.getRandomTeam();
        CountryName teamTwo= CountryName.getRandomTeam();
        while(teamOne.equals(teamTwo)) {
            teamTwo= CountryName.getRandomTeam();
        }
        System.out.println("Welcome to today's match!!\n"+teamOne+" vs "+teamTwo);
        System.out.println("Select the number of overs ?\n10 Overs \n20 Overs \n50 Overs");
        String checkOvers=br.readLine();
        while(invalidateOvers(checkOvers)){
            System.out.println("Enter valid number of overs (10-20-30) ");
            checkOvers=br.readLine();
        }
        int overs=Integer.parseInt(checkOvers);
        Team team1=new Team(teamOne,setPlayerDetails(teamOne));
        Team team2=new Team(teamTwo,setPlayerDetails(teamTwo));
        Match match=new Match(team1, team2, overs);
        return match;
    }

    private boolean invalidateOvers(String checkOvers) {
        if(checkOvers.equals("10") || checkOvers.equals("20") || checkOvers.equals("50")) {
            return false;
        }
        else {
            return true;
        }
    }

    private String[] setPlayerDetails(CountryName c) throws IOException{
        String[] playerDetails=new String[11];
        System.out.println("Time to select players of " + c);
        for(int playerNumber=1; playerNumber<=11; playerNumber++) {
            System.out.println("Enter the Player" + playerNumber + " name");
            String name = br.readLine();
            System.out.println("1. Batsman\n2. Bowler\n3. WicketKeeper");
            String choice = br.readLine();
            while(notValid(choice)){
                System.out.println("Enter numbers only from 1-3 only");
                choice=br.readLine();
            }
            int role=Integer.parseInt(choice);
            playerDetails[playerNumber-1]=name+"_"+role;
        }
        return playerDetails;
    }

    private boolean notValid(String choice){
        if(choice.equals("1") || choice.equals("2") || choice.equals("3")) {
            return false;
        }else {
            return true;
        }
     }

}
