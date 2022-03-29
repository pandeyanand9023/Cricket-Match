package com.tekion.cricketMatch.services;

import com.tekion.cricketMatch.beans.CountryName;
import com.tekion.cricketMatch.beans.MatchDetails;
import com.tekion.cricketMatch.dto.*;
import com.tekion.cricketMatch.repository.MatchRepository;
import com.tekion.cricketMatch.repository.PlayerDetailsRepository;
import com.tekion.cricketMatch.repository.TeamRepository;
import com.tekion.cricketMatch.utils.MatchUtil;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface MatchService {

    void setRepository(MatchRepository matchRepository, PlayerDetailsRepository playerDetailsRepository, TeamRepository teamRepository);

    MatchCreationResponse createMatch(MatchCreationRequest request, List<CountryName> countryName);

    void createTeamForMatch(TeamCreationRequest teamCreationRequest);

    List<CountryName> getTeamsService() throws SQLException, ClassNotFoundException;

    void startMatch(MatchDetails matchDetails, int overs);

/*
    int getMatchId();
    void playMatch();

    public void startInnings(Team battingTeam, Team bowlingTeam, int target) throws IOException, InterruptedException {
        int currOvers = 1;
        while (currOvers <= overs) {
            if (battingTeam.getScore() > target) {
                return;
            }
            startOver(battingTeam, bowlingTeam, target, currOvers);
            if (battingTeam.getWicketsFallen() == 10 || currOvers == overs) {
                break;
            }
            bowlingTeam.maintainsAvailableBowlers();
            battingTeam.changeStrike();
            currOvers++;
        }
    }

    public void startOver(Team battingTeam, Team bowlingTeam, int target, int currOvers) throws IOException {
        for (int balls = 1; balls <= 6 && battingTeam.getWicketsFallen() < 10; balls++) {
            if (battingTeam.getScore() > target) {
                return;
            }
            if (lastSixBalls.size() == 7) {
                lastSixBalls.removeFirst();
            }
            printChoices(inningsNumber);
            userChoiceEvent(br.readLine(), battingTeam, balls, currOvers, target);
            int outcomeOfTheBall = MatchUtil.getRandomOutcome(battingTeam.getSquad().get(battingTeam.getStrike()).getPlayerType());
            if (outcomeOfTheBall == 7) {
                fallingOfWicket(battingTeam, bowlingTeam);
                if (battingTeam.getWicketsFallen() == 10) {
                    break;
                }
            } else if (outcomeOfTheBall == 8) {
                if (isAWide()) {
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
        lastSixBalls.add("" + outcomeOfTheBall);
        battingTeam.incrementBallsPlayed(battingTeam.getStrike());
        battingTeam.incrementRuns(outcomeOfTheBall, battingTeam.getStrike());
        bowlingTeam.getSquad().get(bowlingTeam.getCurrentBowler()).incrementBallsBowled();
        if (outcomeOfTheBall % 2 == 1) {
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

    public void declareWinner() throws SQLException, ClassNotFoundException {
        if (team1.getScore() > team2.getScore()) {
            String result=team1.getName() + " won the match !!";
            matchRepository.storeMatchResults(matchId, team1.getTeamId(), team2.getTeamId(), result);
            System.out.println(result);
        } else if (team1.getScore() < team2.getScore()) {
            String result=team2.getName() + " won the match !!";
            matchRepository.storeMatchResults(matchId, team1.getTeamId(), team2.getTeamId(), result);
            System.out.println(result);
        } else {
            String result="It's a tie!!!";
            matchRepository.storeMatchResults(matchId, team1.getTeamId(), team2.getTeamId(), result);
            System.out.println(result);
        }
    }

    private void userChoiceEvent(String userChoice, Team battingTeam, int balls, int currOvers, int target) {
        switch (userChoice) {
            case "1":
                System.out.println("Your current score is" + " " + battingTeam.getPlayerScore(battingTeam.getStrike()));
                break;
            case "2":
                System.out.println(7 - balls + " Balls are remaining in this over: ");
                break;
            case "3":
                System.out.println("Team Score: " + battingTeam.getScore());
                break;
            case "4":
                System.out.println(10 - battingTeam.getWicketsFallen() + " Wickets are remaining ");
                break;
            case "5":
                if (balls == 1) {
                    System.out.println(overs - currOvers + 1 + " overs remaining in this innings");
                } else {
                    System.out.print(overs - currOvers + ".");
                    System.out.println(6 - balls + 1 + " overs remaining in this innings");
                }
                break;
            case "6":
                battingTeam.getScoresAtFallOfWicket();
                break;
            case "7":
                System.out.println(lastSixBalls);
                break;
            case "8":
                showScoreboard(battingTeam);
                break;
            case "10":
                showRemainingRuns(battingTeam.getScore(), target);
            default:
                System.out.println("\nIncoming next ball");
                break;
        }
    }

    private void showRemainingRuns(int currentScore, int target){
        System.out.print("Remaining Runs to win the match: ");
        System.out.println(target-currentScore);
    }

    private void showFinalScoreboard(Team team1, Team team2) {
        System.out.println("Scoreboard of " + team1.getName());
        team1.getPlayerWiseScore();
        System.out.println("Scoreboard of " + team2.getName());
        team2.getPlayerWiseScore();
    }

    private void showScoreboard(Team battingTeam) {
        System.out.println(battingTeam.toString());
    }


    private void printChoices(int inningsNumber) {
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
        if(inningsNumber==1) {
            System.out.println("10. Remaining runs to win the match");
        }
    }

    private boolean isAWide() {
        Random random = new Random();
        int event = random.nextInt(10);
        return (event <= 6);
    }

    public Match initializeMatch() throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Integer> teamIds= (ArrayList<Integer>) matchRepository.selectTeams();
        int teamOneId=teamIds.get(0);
        int teamTwoId=teamIds.get(1);
        System.out.println("Welcome to today's match!!\n"+ getTeamName().get(0)+" vs "+ getTeamName().get(1));
        System.out.println("Select the number of overs ?\n10 Overs \n20 Overs \n50 Overs");
        String checkOvers=br.readLine();
        while(invalidOvers(checkOvers)){
            System.out.println("Enter valid number of overs (10-20-30) ");
            checkOvers=br.readLine();
        }
        int overs=Integer.parseInt(checkOvers);
        String[] teamOnePlayerName=new String[11];
        String[] teamOnePlayerType=new String[11];
        String[] teamOneBowlerType=new String[11];
        setPlayerDetails(getTeamName().get(0), teamOnePlayerName, teamOnePlayerType, teamOneBowlerType);
        String[] teamTwoPlayerName=new String[11];
        String[] teamTwoPlayerType=new String[11];
        String[] teamTwoBowlerType=new String[11];
        setPlayerDetails(getTeamName().get(1), teamTwoPlayerName, teamTwoPlayerType, teamTwoBowlerType);
        Match match=new Match(teamOneId,  getTeamName().get(0), teamOnePlayerName, teamOnePlayerType, teamOneBowlerType,
                teamTwoId,  getTeamName().get(1), teamTwoPlayerName, teamTwoPlayerType, teamTwoBowlerType,
                overs, br, matchRepository, teamRepository, playerDetailsRepository);
        return match;
    }


    private void setPlayerDetails(String countryName, String[] playerName, String[] playerType, String[] bowlerType) throws IOException{
        System.out.println("Time to select players of " + countryName);
        MatchUtil.setBatsmanDetails(getPlayerNumber(playerType), playerName, playerType, bowlerType);
        MatchUtil.setAllRounderDetails(getPlayerNumber(playerType), playerName, playerType, bowlerType);
        MatchUtil.setBowlerDetails(getPlayerNumber(playerType), playerName, playerType, bowlerType);
    }

    private int getPlayerNumber(String[] playerType){
        int playerNumber=0;
        for(int i=0; i<11; i++){
            if(playerType[i]!=null){
                playerNumber++;
            }
        }
        return playerNumber;
    }

    private boolean invalidOvers(String checkOvers) {
        ArrayList<String> allowedOvers=new ArrayList<>();
        allowedOvers.add("10");
        allowedOvers.add("20");
        allowedOvers.add("50");
        if(MatchUtil.validateInputs(checkOvers, allowedOvers)) {
            return false;
        } else {
            return true;
        }
*/
}