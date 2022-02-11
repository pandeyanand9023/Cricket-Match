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
        System.out.println("Select the number of overs ?\n10 Overs \n20 Overs \n30 Overs");
        int overs=Integer.parseInt(br.readLine());
        while(invalidateOvers(overs)){
            System.out.println("Enter number of overs again");
            overs=Integer.parseInt(br.readLine());
        }
        Team team1=new Team(teamOne,setPlayerDetails(teamOne));
        Team team2=new Team(teamTwo,setPlayerDetails(teamTwo));
        Match match=new Match(team1, team2, overs);
        return match;
    }

    private boolean invalidateOvers(int overs) {
        if(overs!=10 && overs!=20 && overs!=30) {
            return true;
        }
        else {
            return false;
        }
    }

    private String[] setPlayerDetails(CountryName c) throws IOException{
        String[] playerDetails=new String[11];
        System.out.println("Time to select players of " + c);
        for(int playerNumber=1; playerNumber<=11; playerNumber++) {
            System.out.println("Enter the Player" + playerNumber + " name");
            String name = br.readLine();
            System.out.println("1. Batsman\n2. Bowler\n3. WicketKeeper");
            int choice = Integer.parseInt(br.readLine());
            while(!isValid(choice)){
                choice=Integer.parseInt(br.readLine());
            }
            playerDetails[playerNumber]=name+"_"+choice;
        }
        return playerDetails;
    }

    private boolean isValid(int choice){
        if(choice<=0 || choice>3) {
            return false;
        }
        return true;
    }

}
