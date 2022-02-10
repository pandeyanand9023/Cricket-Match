package com.tekion.match;
import com.tekion.match.*;
import java.util.ArrayList;
import java.util.Random;

public class Match {
    private Team team1;
    private Team team2;
    private int overs;

    Match(Team t1, Team t2, int overs){
        this.team1=t1;
        this.team2=t2;
        this.overs=overs;
    }

    public void playMatch() {
        startInnings(team1,Integer.MAX_VALUE);
        System.out.println("First Innings Over");
        startInnings(team2, team1.getScore());

        if(team1.getScore()> team2.getScore()) {
            System.out.println(team1.getName()+" won the match !!");
        }
        else if(team1.getScore()< team2.getScore()) {
            System.out.println(team2.getName() + " won the match !!");
        }
        else {
            System.out.println("It's a tie!!!");
        }
    }

    public void startInnings(Team t1,int target) {
        int j=1;
        while(j<=overs) {

            if(t1.getScore()>target)
                return;

            startOver(t1,target);
            t1.changeStrike();
        }
    }

    public void startOver(Team t1,int target) {
        Random r=new Random();

        for (int i = 1; i <= 6 && t1.getWickets() < 11; i++) {
            int num = r.nextInt(8);
            t1.incrementBalls(t1.getStrike());
            if (num == 7) {
                t1.fallOfWicket();
                if (t1.getWickets() == 10)
                    break;
                t1.setStrike();
            } else {
                t1.incrementRuns(num,t1.getStrike());
                if(num%2==1)
                    t1.changeStrike();
            }
        }
    }
}
