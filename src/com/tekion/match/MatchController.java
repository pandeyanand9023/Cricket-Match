package com.tekion.match;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
public class MatchController extends Exception{
    BufferedReader br;
    Match match;
    public MatchController(BufferedReader br) throws IOException{
        this.br=br;
        match = initializeMatch();
    }

    public void startMatch() throws IOException, InterruptedException {
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
        String[] teamOnePlayerName=new String[11];
        String[] teamOnePlayerType=new String[11];
        setPlayerDetails(teamOne, teamOnePlayerName, teamOnePlayerType);
        String[] teamTwoPlayerName=new String[11];
        String[] teamTwoPlayerType=new String[11];
        setPlayerDetails(teamTwo, teamTwoPlayerName, teamTwoPlayerType);
        Match match=new Match(teamOne, teamOnePlayerName, teamOnePlayerType, teamTwo, teamTwoPlayerName, teamTwoPlayerType, overs, br);
        return match;
    }

    private void setPlayerDetails(CountryName c, String[] playerName, String[] playerType) throws IOException{
        System.out.println("Time to select players of " + c);
        System.out.println("Enter the number of Batsman ? (Max 6)");
        String numberOfBatsman=br.readLine();
        while(invalidNumberOfBatsman(numberOfBatsman)){
         System.out.println("Enter valid number of Batsman (Between 1-6)");
         numberOfBatsman=br.readLine();
        }
        int playerNumber=1;
        for( playerNumber=1; playerNumber<=Integer.parseInt(numberOfBatsman); playerNumber++) {
            System.out.println("Enter the Player " + playerNumber + " name");
            playerName[playerNumber-1]=br.readLine();
            playerType[playerNumber-1]= ""+1;
        }
        System.out.println("Enter the names of Bowlers now");
        while(playerNumber<=11) {
            System.out.println("Enter the Player " + playerNumber + " name");
            playerName[playerNumber-1]=br.readLine();
            playerType[playerNumber-1]= ""+2;
            playerNumber++;
        }
    }

    private boolean invalidateOvers(String checkOvers) {
        ArrayList<String> allowedOvers=new ArrayList<>();
        allowedOvers.add("10");
        allowedOvers.add("20");
        allowedOvers.add("50");
        if(MatchUtil.validateInputs(checkOvers, allowedOvers)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean invalidNumberOfBatsman(String numberOfBatsman){
        ArrayList<String> allowedNumberOfBatsman=new ArrayList<>();
        for(int i=1; i<=6; i++){
            allowedNumberOfBatsman.add(""+i);
        }
        return !(MatchUtil.validateInputs(numberOfBatsman, allowedNumberOfBatsman));
    }

}
