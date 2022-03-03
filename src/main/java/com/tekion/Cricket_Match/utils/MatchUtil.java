package com.tekion.Cricket_Match.utils;
import com.tekion.Cricket_Match.dto.Team;
import com.tekion.Cricket_Match.dto.Player;

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

    public static int getRandomOutcome(Player.Role playerType){
        if(playerType== Player.Role.BATSMAN || playerType== Player.Role.ALL_ROUNDER){
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
            System.out.println(team1.getName()+" has won the toss ");
            return choiceToBatOrBall(team1)^tossOutcome;
        }else{
            System.out.println(team2.getName()+" has won the toss ");
            return choiceToBatOrBall(team2)^tossOutcome;
        }
    }

    public static int choiceToBatOrBall(Team team){
        int batOrBall=random.nextInt(2);
        if(batOrBall==0){
            System.out.println(team.getName()+ " has chosen to bat first !!");
            return 0;
        } else {
            System.out.println(team.getName()+ " chosen to bowl first !!");
           return 1;
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

    public static void clearConsole() throws IOException{
        System.out.println("Not working!!");
    }

    public static int getMatchId(){
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
}
