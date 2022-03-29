package com.tekion.cricketMatch.utils;
import com.tekion.cricketMatch.dto.Team;
import com.tekion.cricketMatch.enums.BowlerType;
import com.tekion.cricketMatch.enums.PlayerType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class MatchUtil {
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    private static final int extraBall=7;
    private static  Random random=new Random();

    public static boolean validateInputs(String value, ArrayList<String> allowedValue){
        return allowedValue.contains(value);
    }

    public static int getRandomOutcome(PlayerType playerType){
        if(playerType== playerType.BATSMAN || playerType== playerType.ALL_ROUNDER){
           int outcome1=(int)(Math.random()*7);
           int outcome2=(int)(Math.random()*7);
           if(isExtra(outcome1, outcome2)) {
               return 8;
           }else{
               return outcome1;
           }
        }else{
            int outcome1=random.nextInt(5,8);
            int outcome2=random.nextInt(5,8);
            if(isExtra(outcome1, outcome2)){
                return 8; // Extra
            }else{
                if(outcome1==5) {
                    return 0;   // Dot ball
                }else if(outcome1==6){
                    return random.nextInt(1,7); // Runs from (1-6)
                }else {
                    return outcome1; // Wicket
                }

            }
        }
    }

    private static boolean isExtra(int outcome1, int outcome2){
        if(outcome1==outcome2 && outcome1==extraBall){
            return true;
        }else{
            return false;
        }
    }

    public static int simulateToss(Team team1, Team team2){
        int tossOutcome=random.nextInt(2);
        if(tossOutcome==0){
            System.out.println(team1.getTeamName()+" has won the toss ");
            return choiceToBatOrBall(team1)^tossOutcome;
        }else{
            System.out.println(team2.getTeamName()+" has won the toss ");
            return choiceToBatOrBall(team2)^tossOutcome;
        }
    }

    public static int choiceToBatOrBall(Team team){
        int batOrBall=random.nextInt(2);
        if(batOrBall==0){
            System.out.println(team.getTeamName()+ " has chosen to bat first !!");
            return 0;
        } else {
            System.out.println(team.getTeamName()+ " chosen to bowl first !!");
           return 1;
        }
    }

    public static boolean validateOvers(int overs){
        if(overs==10 || overs==20 || overs==50){
            return true;
        } else {
            return false;
        }
    }

    public static void setBatsmanDetails(int playerNumber, String[] playerName, String[] playerType, String[] bowlerType) throws IOException{
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

    public static void setAllRounderDetails(int playerNumber, String[] playerName, String[] playerType, String[] bowlerType) throws IOException{
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

    public static void setBowlerDetails(int playerNumber, String[] playerName, String[] playerType, String[] bowlerType) throws IOException{
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

    public static int getNewMatchId(){
        return random.nextInt((int)Math.pow(2,30));
    }

    private static boolean invalidNumberOfAllrounders(String numberOfAllRounders){
        ArrayList<String> allowedNumberOfAllRounders=new ArrayList<>();
        for(int i=0; i<=2; i++){
            allowedNumberOfAllRounders.add(""+i);
        }
        return !(MatchUtil.validateInputs(numberOfAllRounders, allowedNumberOfAllRounders));
    }

    private static boolean invalidNumberOfBatsman(String numberOfBatsman){
        ArrayList<String> allowedNumberOfBatsman=new ArrayList<>();
        for(int i=1; i<=6; i++){
            allowedNumberOfBatsman.add(""+i);
        }
        return !(MatchUtil.validateInputs(numberOfBatsman, allowedNumberOfBatsman));
    }

    private static boolean invalidBowlerType(String bowlerTypes){
        ArrayList<String> allowedNumberOfBowlerTypes=new ArrayList<>();
        for(int i=1; i<=3; i++){
            allowedNumberOfBowlerTypes.add(""+i);
        }
        return !(MatchUtil.validateInputs(bowlerTypes, allowedNumberOfBowlerTypes));
    }
    public static PlayerType convertToPlayerType(String playerType){
        if(playerType.equals("BATSMAN")){
            return PlayerType.BATSMAN;
        } else if(playerType.equals("BOWLER")){
            return PlayerType.BOWLER;
        }else {
            return PlayerType.ALL_ROUNDER;
        }
    }
    public static BowlerType convertToBowlerType(String bowlerType){
        if(bowlerType.equals("SPIN")){
            return BowlerType.SPIN;
        } else if(bowlerType.equals("FAST")){
            return BowlerType.FAST;
        }else {
            return BowlerType.MEDIUM_FAST;
        }
    }
}
