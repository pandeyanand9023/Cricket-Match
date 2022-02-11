package com.tekion.match;
import com.tekion.match.*;
import java.util.ArrayList;
import java.util.Random;

public class Match {
    private Team team1;
    private Team team2;
    private int overs;

    Match(Team team1, Team team2, int overs){
        this.team1=team1;
        this.team2=team2;
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

    public void startInnings(Team team1, int target) {
        int currOvers=1;
        while(currOvers<=overs) {

            if(team1.getScore()>target)
                return;

            startOver(team1,target);
            team1.changeStrike();
        }
    }

    public void startOver(Team team1, int target) {
        Random random=new Random();
        for (int i = 1; i <= 6 && team1.getWickets() < 10; i++) {
            int outcome= random.nextInt(8);
            team1.incrementBalls(team1.getStrike());
            if (outcome== 7) {
                team1.fallOfWicket();
                if (team1.getWickets() == 10) {
                    break;
                }
                team1.setStrike();
            } else {
                team1.incrementRuns(outcome,team1.getStrike());
                if(outcome%2==1)
                    team1.changeStrike();
            }
        }
    }
}
