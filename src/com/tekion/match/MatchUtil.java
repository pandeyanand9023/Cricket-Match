package com.tekion.match;
import java.io.IOException;
import java.util.*;
public class MatchUtil {
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
                return 8;
            }else{
                if(outcome1==5) {
                    return 0;
                }else if(outcome1==6){
                    return random.nextInt(1,7); // Runs from (1-6)
                }else {
                    return outcome1;
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
    /*
    0 0 => team1, team2
    0,1=>  team2, team1
    1,0 => team2, team1
    1,1 => team1, team2
    */

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

    public static void clearConsole() throws IOException{
        System.out.println("Not working!!");
    }
}
