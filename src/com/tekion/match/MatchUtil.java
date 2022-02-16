package com.tekion.match;
import java.io.IOException;
import java.util.*;
public class MatchUtil {
    private static final int extraBall=7;
    public static boolean validateInputs(String value, ArrayList<String> allowedValue){
        return allowedValue.contains(value);
    }

    public static int getRandomOutcome(Player.Role playerType){
        Random random=new Random();
        if(playerType== Player.Role.BATSMAN){
           int outcome1=(int)(Math.random()*7);
           int outcome2=(int)(Math.random()*7);
           if(isExtra(outcome1, outcome2)) {
               return 8;
           }else{
               return outcome1;
           }
        }else{
            int outcome1=random.nextInt(8);
            int outcome2=random.nextInt(8);
            if(isExtra(outcome1, outcome2)){
                return 8;
            }else{
                return outcome1;
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

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else {
                System.out.print("\033\143");
            }
        } catch (IOException | InterruptedException ex) {}
    }
}
