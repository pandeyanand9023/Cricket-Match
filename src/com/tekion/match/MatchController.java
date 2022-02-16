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

    public void startMatch() throws IOException{
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
        for(int playerNumber=1; playerNumber<=11; playerNumber++) {
            System.out.println("Enter the Player" + playerNumber + " name");
            playerName[playerNumber-1]=br.readLine();
            System.out.println("1. Batsman\n2. Bowler\n3. WicketKeeper");
            String type = br.readLine();
            while(invalidPlayerType(type)){
                System.out.println("Enter numbers only from 1-3 only");
                type=br.readLine();
            }
            playerType[playerNumber-1]= type;
        }
    }

    private boolean invalidPlayerType(String playerType){
        ArrayList<String> allowedTypes=new ArrayList<>();
        allowedTypes.add("1");
        allowedTypes.add("2");
        allowedTypes.add("3");
        if(MatchUtil.validateInputs(playerType, allowedTypes)) {
            return false;
        } else {
            return true;
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

}
