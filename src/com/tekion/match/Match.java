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
    Match(CountryName teamOne, String[] teamOnePlayerName, String[] teamOnePlayerType, String[] teamOneBowlerType,
          CountryName teamTwo, String[] teamTwoPlayerName, String[] teamTwoPlayerType,String[] teamTwoBowlerType,
          int overs,BufferedReader br) throws IOException {
        team1=new Team(br, teamOne, teamOnePlayerName, teamOnePlayerType, teamOneBowlerType, overs);
        team2=new Team(br, teamTwo, teamTwoPlayerName, teamTwoPlayerType, teamTwoBowlerType, overs);
        this.overs=overs;
        this.br=br;
        this.lastSixBalls=new ArrayDeque<>();
    }

    public void playMatch() throws IOException, InterruptedException {
        int tossOutcome=MatchUtil.simulateToss(team1, team2);
        if(tossOutcome==0) {
            startInnings(team1, team2, Integer.MAX_VALUE);
            System.out.println("First Innings Over");
            startInnings(team2, team1, team1.getScore());
            System.out.println("Second Innings Over");
        } else{
            startInnings(team2, team1, Integer.MAX_VALUE);
            System.out.println("First Innings Over");
            startInnings(team1, team2, team2.getScore());
            System.out.println("Second Innings Over");
        }
        showFinalScoreboard(team1, team2);
        team1.topPerformers();
        team2.topPerformers();
        declareWinner();
    }

    public void startInnings(Team battingTeam, Team bowlingTeam, int target) throws IOException, InterruptedException {
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
            printChoices();
            userChoiceEvent(br.readLine(), battingTeam, balls, currOvers);
            int outcomeOfTheBall= MatchUtil.getRandomOutcome(battingTeam.getSquad().get(battingTeam.getStrike()).getPlayerType());
            if (outcomeOfTheBall== 7) {
                fallingOfWicket(battingTeam, bowlingTeam);
                if (battingTeam.getWicketsFallen() == 10) {
                    break;
                }
            } else if(outcomeOfTheBall==8){
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
                scoringOfRuns(battingTeam, bowlingTeam, outcomeOfTheBall);
            }
        }
    }

    private void scoringOfRuns(Team battingTeam, Team bowlingTeam, int outcomeOfTheBall) {
        lastSixBalls.add(""+ outcomeOfTheBall);
        battingTeam.incrementBallsPlayed(battingTeam.getStrike());
        battingTeam.incrementRuns(outcomeOfTheBall, battingTeam.getStrike());
        bowlingTeam.getSquad().get(bowlingTeam.getCurrentBowler()).incrementBallsBowled();
        if(outcomeOfTheBall %2==1) {
            battingTeam.changeStrike();
        }
    }

    private void fallingOfWicket(Team battingTeam, Team bowlingTeam) {
        lastSixBalls.addLast("W");
        battingTeam.incrementBallsPlayed(battingTeam.getStrike());
        battingTeam.fallOfWicket();
        bowlingTeam.wicketsTaken(bowlingTeam.getCurrentBowler());
        bowlingTeam.getSquad().get(bowlingTeam.getCurrentBowler()).incrementBallsBowled();
        battingTeam.setStrike();
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

    private void userChoiceEvent(String userChoice, Team battingTeam, int balls, int currOvers){
     switch(userChoice){
         case "1": System.out.println("Your current score is"+" "+battingTeam.getPlayerScore(battingTeam.getStrike()));
                   break;
         case "2": System.out.println(7-balls+" Balls are remaining in this over: ");
                   break;
         case "3": System.out.println("Team Score: "+battingTeam.getScore());
                   break;
         case "4": System.out.println(10-battingTeam.getWicketsFallen()+" Wickets are remaining ");
                   break;
         case "5": if(balls==1) {
                         System.out.println(overs-currOvers+1+" overs remaining in this innings");
                   } else {
                         System.out.print(overs-currOvers+".");
                         System.out.println(6-balls+1+" overs remaining in this innings");
                   }
                   break;
         case "6": battingTeam.getScoresAtFallOfWicket();
                   break;
         case "7": System.out.println(lastSixBalls);
                   break;
         case "8": showScoreboard(battingTeam, balls, currOvers);
                   break;
         default:  System.out.println("\nIncoming next ball");
                   break;
      }
    }

    private void showFinalScoreboard(Team team1, Team team2){
        System.out.println("Scoreboard of "+team1.getName());
        team1.getPlayerWiseScore();
        System.out.println("Scoreboard of "+team2.getName());
        team2.getPlayerWiseScore();
    }

    private void showScoreboard(Team battingTeam, int balls, int currOvers){
        System.out.println(battingTeam.toString());
    }

    private boolean isAWide(){
        Random random=new Random();
        int event=random.nextInt(10);
        return (event<=6);
    }

    private void printChoices(){
        System.out.println("Before playing the next ball, would you like to see?" +
                "\n1. Your Score" +
                "\n2. Balls left in the over " +
                "\n3. Teams Score " +
                "\n4. Wickets remaining " +
                "\n5. Overs left in the innings" +
                "\n6. Fall of Wicket " +
                "\n7. Want to see the previous six balls ?" +
                "\n8. Show scorecard" +
                "\n9. Press any key to skip this and play next ball");
    }
}
