package com.tekion.match;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Random;

public class Match {
    private final Team team1;
    private final Team team2;
    private int overs;
    private BufferedReader br;
    private ArrayDeque<String> lastSixBalls;
    Match(CountryName teamOne, String[] teamOnePlayerName, String[] teamOnePlayerType,
          CountryName teamTwo, String[] teamTwoPlayerName, String[] teamTwoPlayerType, int overs,BufferedReader br) throws IOException {
        team1=new Team(br, teamOne, teamOnePlayerName, teamOnePlayerType);
        team2=new Team(br, teamTwo, teamTwoPlayerName, teamTwoPlayerType);
        this.overs=overs;
        this.br=br;
        this.lastSixBalls=new ArrayDeque<>();
    }

    public void playMatch() throws IOException{
        startInnings(team1, team2, Integer.MAX_VALUE);
        System.out.println("First Innings Over");
        startInnings(team2, team1, team1.getScore());
        System.out.println("Second Innings Over");
        showScoreboard(team1, team2);
        declareWinner();
    }

    public void startInnings(Team battingTeam, Team bowlingTeam, int target) throws IOException{
        int currOvers=1;
        while(currOvers<=overs) {
            MatchUtil.clearConsole();
            if(battingTeam.getScore()>target) {
                return;
            }
            startOver(battingTeam, bowlingTeam, target, currOvers);
            if(battingTeam.getWicketsFallen()==10 || currOvers==overs) {
                break;
            }
            bowlingTeam.changeBowler();
            battingTeam.changeStrike();
            currOvers++;
        }
    }

    public void startOver(Team battingTeam, Team bowlingTeam, int target, int currOvers) throws IOException{
        for (int balls = 1; balls<= 6 && battingTeam.getWicketsFallen() < 10; balls++) {
            if(battingTeam.getScore()>target) {
                return;
            }
            if(lastSixBalls.size()==7) {
                lastSixBalls.removeFirst();
            }
            System.out.println("Before playing the next ball, would you like to see?\n1. Your Score" +
                    "\n2. Balls left in the over \n3. Teams Score \n4. Wickets remaining \n5. Remaining runs to win the game" +
                    "\n6. Overs left in the innings" +
                    "\n7. Fall of Wicket \n8. Want to see the previous six balls ?" +
                    "\n9. Press any key to skip this and play next ball");
            userChoiceEvent(br.readLine(), battingTeam, balls, target, currOvers);
            int outcome= MatchUtil.getRandomOutcome(battingTeam.getSquad().get(battingTeam.getStrike()).getPlayerType());
            if (outcome== 7) {
                lastSixBalls.addLast("W");
                battingTeam.incrementBalls(battingTeam.getStrike());
                battingTeam.fallOfWicket();
                bowlingTeam.wicketsTaken(bowlingTeam.getCurrentBowler());
                if (battingTeam.getWicketsFallen() == 10) {
                    break;
                }
                battingTeam.setStrike();
            } else if(outcome==8){
                if(isAWide()){
                    System.out.println("It's a wide ball");
                    lastSixBalls.add("Wd");
                } else {
                    System.out.println("It's a no- ball");
                    lastSixBalls.add("NB");
                }
                balls--;
                battingTeam.incrementRuns();
            } else {
                lastSixBalls.add(""+outcome);
                battingTeam.incrementBalls(battingTeam.getStrike());
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

    private void userChoiceEvent(String userChoice, Team battingTeam, int balls, int target, int currOvers){
     if(userChoice.equals("1")) {
         System.out.println("Your current score is"+" "+battingTeam.getPlayerScore(battingTeam.getStrike()));
     } else if(userChoice.equals("2")) {
         System.out.println(7-balls+" Balls are remaining in this over: ");
     } else if(userChoice.equals("3")) {
         System.out.println("Team Score: "+battingTeam.getScore());
     } else if(userChoice.equals("4")) {
         System.out.println(10-battingTeam.getWicketsFallen()+" Wickets are remaining ");
     } else if(userChoice.equals("5")) {
         if(target==Integer.MAX_VALUE) {
             System.out.println("You are batting first, there is no target to be chased.");
         } else {
             System.out.print("Remaining runs: ");
             System.out.println(target-battingTeam.getScore());
         }
     } else if(userChoice.equals("6")) {
         if(balls==1) {
             System.out.println(overs-currOvers+1+" overs remaining in this innings");
         } else {
             System.out.print(overs-currOvers+".");
             System.out.println(6-balls+1+" overs remaining in this innings");
         }
     } else if(userChoice.equals("7")) {
         battingTeam.getFallOfWicket();
     } else if(userChoice.equals("8")) {
         System.out.println(lastSixBalls);
     } else{
         System.out.println("\nIncoming next ball");
     }
    }

    private void showScoreboard(Team team1, Team team2){
        System.out.println("Scoreboard of "+team1.getName());
        team1.getPlayerWiseScore();
        System.out.println("Scoreboard of "+team2.getName());
        team2.getPlayerWiseScore();
    }

    private boolean isAWide(){
        Random random=new Random();
        int event=random.nextInt(10);
        return (event<=6);
    }
}
