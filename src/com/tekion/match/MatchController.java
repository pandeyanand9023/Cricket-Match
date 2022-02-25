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
        while(invalidOvers(checkOvers)){
            System.out.println("Enter valid number of overs (10-20-30) ");
            checkOvers=br.readLine();
        }
        int overs=Integer.parseInt(checkOvers);
        String[] teamOnePlayerName=new String[11];
        String[] teamOnePlayerType=new String[11];
        String[] teamOneBowlerType=new String[11];
        setPlayerDetails(teamOne, teamOnePlayerName, teamOnePlayerType, teamOneBowlerType);
        String[] teamTwoPlayerName=new String[11];
        String[] teamTwoPlayerType=new String[11];
        String[] teamTwoBowlerType=new String[11];
        setPlayerDetails(teamTwo, teamTwoPlayerName, teamTwoPlayerType, teamTwoBowlerType);
        Match match=new Match(teamOne, teamOnePlayerName, teamOnePlayerType, teamOneBowlerType,
                              teamTwo, teamTwoPlayerName, teamTwoPlayerType, teamTwoBowlerType,
                              overs, br);
        return match;
    }

    private void setPlayerDetails(CountryName countryName, String[] playerName, String[] playerType, String[] bowlerType) throws IOException{
        System.out.println("Time to select players of " + countryName);
        setBatsmanDetails(getPlayerNumber(playerType), playerName, playerType, bowlerType);
        setAllRounderDetails(getPlayerNumber(playerType), playerName, playerType, bowlerType);
        setBowlerDetails(getPlayerNumber(playerType), playerName, playerType, bowlerType);
    }

    private void setBatsmanDetails(int playerNumber, String[] playerName, String[] playerType, String[] bowlerType) throws IOException{
        System.out.println("Enter the number of Batsman ? (Max 6)");
        String numberOfBatsman=br.readLine();
        while(invalidNumberOfBatsman(numberOfBatsman)){
            System.out.println("Enter valid number of Batsman (Between 1-6)");
            numberOfBatsman=br.readLine();
        }
        for( playerNumber=1; playerNumber<=Integer.parseInt(numberOfBatsman); playerNumber++) {
            System.out.println("Enter the Player " + playerNumber + " name");
            playerName[playerNumber-1]=br.readLine();
            System.out.println("Enter the type of Bowler the player is :\n1. Fast \n2. Medium Fast \n3. Spin");
            String bowlerTypeChoice=br.readLine();
            while(invalidBowlerType(bowlerTypeChoice)){
                System.out.println("Enter a valid number :\n1. Fast \n2. Medium Fast \n3. Spin");
                bowlerTypeChoice=br.readLine();
            }
            bowlerType[playerNumber-1]=bowlerTypeChoice;
            playerType[playerNumber-1]= ""+1;
        }
    }

    private void setAllRounderDetails(int playerNumber, String[] playerName, String[] playerType, String[] bowlerType) throws IOException{
        System.out.println("Enter the number of All-rounders ? (Max 2)");
        String numberOfAllRounders=br.readLine();
        while(invalidNumberOfAllrounders(numberOfAllRounders)){
            System.out.println("Enter valid number of All-rounders");
            numberOfAllRounders=br.readLine();
        }
        int numberOfAllrounder=0;
        while(numberOfAllrounder<Integer.parseInt(numberOfAllRounders)){
            System.out.println("Enter the Player " + (playerNumber+1) + " name");
            playerName[playerNumber]=br.readLine();
            System.out.println("Enter the type of Bowler the player is :\n1. Fast \n2. Medium Fast \n3. Spin");
            String bowlerTypeChoice=br.readLine();
            while(invalidBowlerType(bowlerTypeChoice)){
                System.out.println("Enter a valid number :\n1. Fast \n2. Medium Fast \n3. Spin");
                bowlerTypeChoice=br.readLine();
            }
            bowlerType[playerNumber]=bowlerTypeChoice;
            playerType[playerNumber]= ""+3;
            playerNumber++;
            numberOfAllrounder++;
        }
    }

    private void setBowlerDetails(int playerNumber, String[] playerName, String[] playerType, String[] bowlerType) throws IOException{
        System.out.println("Enter the names of Bowlers now");
        while(playerNumber<11) {
            System.out.println("Enter the Player " + (playerNumber+1) + " name");
            playerName[playerNumber]=br.readLine();
            System.out.println("Enter the type of Bowler the player is :\n1. Fast \n2. Medium Fast \n3. Spin");
            String bowlerTypeChoice=br.readLine();
            while(invalidBowlerType(bowlerTypeChoice)){
                System.out.println("Enter a valid number :\n1. Fast \n2. Medium Fast \n3. Spin");
                bowlerTypeChoice=br.readLine();
            }
            bowlerType[playerNumber]=bowlerTypeChoice;
            playerType[playerNumber]= ""+2;
            playerNumber++;
        }
    }

    private int getPlayerNumber(String[] playerType){
        int playerNumber=0;
        for(int i=0; i<11; i++){
            if(playerType[i]!=null){
                playerNumber++;
            }
        }
        return playerNumber;
    }

    private boolean invalidOvers(String checkOvers) {
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

    private boolean invalidNumberOfAllrounders(String numberOfAllRounders){
     ArrayList<String> allowedNumberOfAllRounders=new ArrayList<>();
     for(int i=0; i<=2; i++){
         allowedNumberOfAllRounders.add(""+i);
     }
     return !(MatchUtil.validateInputs(numberOfAllRounders, allowedNumberOfAllRounders));
    }

    private boolean invalidNumberOfBatsman(String numberOfBatsman){
        ArrayList<String> allowedNumberOfBatsman=new ArrayList<>();
        for(int i=1; i<=6; i++){
            allowedNumberOfBatsman.add(""+i);
        }
        return !(MatchUtil.validateInputs(numberOfBatsman, allowedNumberOfBatsman));
    }

    private boolean invalidBowlerType(String bowlerTypes){
        ArrayList<String> allowedNumberOfBowlerTypes=new ArrayList<>();
        for(int i=1; i<=3; i++){
            allowedNumberOfBowlerTypes.add(""+i);
        }
        return !(MatchUtil.validateInputs(bowlerTypes, allowedNumberOfBowlerTypes));
    }

}
