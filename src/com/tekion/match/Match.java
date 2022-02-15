package com.tekion.match;
import java.io.IOException;
import java.util.Random;

public class Match {
    private final Team team1;
    private final Team team2;
    private int overs;

    Match(CountryName teamOne, String[] teamOnePlayerName, String[] teamOnePlayerType,
          CountryName teamTwo, String[] teamTwoPlayerName, String[] teamTwoPlayerType, int overs) throws IOException {
        team1=new Team(teamOne, teamOnePlayerName, teamOnePlayerType);
        team2=new Team(teamTwo, teamTwoPlayerName, teamTwoPlayerType);
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
