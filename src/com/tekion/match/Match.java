package com.tekion.match;
import com.tekion.match.*;
import java.util.ArrayList;
public class Match {
    private Team team1;
    private Team team2;
    private int overs;
    private int target;

    Match(Team t1, Team t2, int overs){
        this.team1=t1;
        this.team2=t2;
        this.overs=overs;
        this.target=Integer.MAX_VALUE;
    }


    public void playMatch() {
        startInnings(team1);
        System.out.println("First Innings Over");
        target=team1.getScore();
        startInnings(team2);

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


    public void startInnings(Team t1) {
        if(t1.getScore()>target)
            return;
        for(int i=1;i<=overs*6 && t1.getWickets()<11;i++)
        {
            int val1=(int)(Math.random()*6);
            int val2=(int)(Math.random()*6);
            t1.getSquad().get(t1.getWickets()).addBalls();
            t1.incrementBalls();
            if(val1==val2)
            {
                t1.addWicket();
                System.out.println("Batsman Out !!");
                if(t1.getWickets()==11)
                    break;
                System.out.println("Next Batsman "+t1.getSquad().get(t1.getWickets()).getName());
            }
            else
            {
                t1.addRuns(val1);
                t1.getSquad().get(t1.getWickets()).addRuns(val1);
                System.out.println("Runs scored on this ball"+val1);
            }
        }
    }
}
