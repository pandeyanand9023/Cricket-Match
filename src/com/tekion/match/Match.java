package com.tekion.match;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Match {
    private final Team team1;
    private final Team team2;
    private int overs;
    private BufferedReader br;
    Match(CountryName teamOne, String[] teamOnePlayerName, String[] teamOnePlayerType,
          CountryName teamTwo, String[] teamTwoPlayerName, String[] teamTwoPlayerType, int overs,BufferedReader br) throws IOException {
        team1=new Team(teamOne, teamOnePlayerName, teamOnePlayerType);
        team2=new Team(teamTwo, teamTwoPlayerName, teamTwoPlayerType);
        this.overs=overs;
        this.br=br;
    }

    public void playMatch() throws IOException{
        startInnings(team1, Integer.MAX_VALUE);
        System.out.println("First Innings Over");
        startInnings(team2, team1.getScore());
        System.out.println("Second Innings Over");
        showScoreboard(team1, team2);
        declareWinner();
    }

    public void startInnings(Team battingTeam, int target) throws IOException{
        int currOvers=1;
        while(currOvers<=overs) {
            if(battingTeam.getScore()>target) {
                return;
            }
            startOver(battingTeam, target, currOvers);
            if(battingTeam.getWickets()==10 || currOvers==overs) {
                break;
            }
            battingTeam.changeStrike();
            currOvers++;
        }
    }

    public void startOver(Team battingTeam, int target, int currOvers) throws IOException{
        Random random=new Random();
        for (int balls = 1; balls<= 6 && battingTeam.getWickets() < 10; balls++) {
            if(battingTeam.getScore()>target) {
                return;
            }
            System.out.println("Before playing the next ball, would you like to see?\n1. Your Score" +
                    "\n2. Balls left in the over \n3. Teams Score \n4. Wickets remaining \n5. Remaining runs to win the game" +
                    "\n6. Overs left in the innings" +
                    "\n7. Press any key to skip this and play next ball ");
            userChoiceEvent(br.readLine(), battingTeam, balls, target, currOvers);
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

    private void userChoiceEvent(String userChoice, Team battingTeam, int balls, int target, int currOvers){
     if(userChoice.equals("1")){
         System.out.println("Your current score is"+" "+battingTeam.getPlayerScore(battingTeam.getStrike()));
     } else if(userChoice.equals("2")){
         System.out.println(7-balls+" Balls are remaining in this over: ");
     } else if(userChoice.equals("3")){
         System.out.println("Team Score: "+battingTeam.getScore());
     } else if(userChoice.equals("4")){
         System.out.println(10-battingTeam.getWickets()+" Wickets are remaining ");
     } else if(userChoice.equals("5")){
         if(target==Integer.MAX_VALUE){
             System.out.println("You are batting first, there is no target to be chased.");
         } else {
             System.out.print("Remaining runs: ");
             System.out.println(target-battingTeam.getScore());
         }
     } else if(userChoice.equals("6")){
         if(balls==1){
             System.out.println(overs-currOvers+1+" overs remaining in this innings");
         } else {
             System.out.print(overs-currOvers+".");
             System.out.println(6-balls+1+" overs remaining in this innings");
         }
     } else {
         System.out.println("\nIncoming next ball");
     }
    }

    private void showScoreboard(Team team1, Team team2){
        System.out.println("Scoreboard of "+team1.getName());
        team1.getPlayerWiseScore();
        System.out.println("Scoreboard of "+team2.getName());
        team2.getPlayerWiseScore();
    }
}
