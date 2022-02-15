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
        startInnings(team1, Integer.MAX_VALUE);
        System.out.println("First Innings Over");
        startInnings(team2, team1.getScore());
        System.out.println("Second Innings Over");
        declareWinner();
    }

    public void startInnings(Team battingTeam, int target) {
        int currOvers=1;
        while(currOvers<=overs) {
            if(battingTeam.getScore()>target) {
                return;
            }
            startOver(battingTeam, target);
            if(battingTeam.getWickets()==10 || currOvers==overs) {
                break;
            }
            battingTeam.changeStrike();
            currOvers++;
        }
    }

    public void startOver(Team battingTeam, int target) {
        Random random=new Random();
        for (int balls = 1; balls<= 6 && battingTeam.getWickets() < 10; balls++) {
            if(battingTeam.getScore()>target) {
                return;
            }
            int outcome= random.nextInt(8);
            battingTeam.incrementBalls(battingTeam.getStrike());
            if (outcome== 7) {
                battingTeam.fallOfWicket();
                if (battingTeam.getWickets() == 10) {
                    break;
                }
                battingTeam.setStrike();
            } else {
                battingTeam.incrementRuns(outcome,battingTeam.getStrike());
                if(outcome%2==1) {
                    battingTeam.changeStrike();
                }
            }
        }
    }
    
    private void declareWinner() {
        if(team1.getScore()> team2.getScore()) {
            System.out.println(team1.getName()+" won the match !!");
        } else if(team1.getScore()< team2.getScore()) {
            System.out.println(team2.getName() + " won the match !!");
        } else {
            System.out.println("It's a tie!!!");
        }
    }
}
