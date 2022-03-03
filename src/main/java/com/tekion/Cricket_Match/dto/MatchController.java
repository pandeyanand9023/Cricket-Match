package com.tekion.Cricket_Match.dto;

import com.tekion.Cricket_Match.utils.MatchUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.tekion.Cricket_Match.repository.MatchRepository.getTeamName;
import static com.tekion.Cricket_Match.repository.MatchRepository.selectTeams;

public class MatchController extends Exception{
    BufferedReader br;
    Match match;
    public MatchController(BufferedReader br) throws IOException, SQLException, ClassNotFoundException {
        this.br=br;
        match = initializeMatch();
    }

    public void startMatch() throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        match.playMatch();
    }

    public Match initializeMatch() throws IOException, SQLException, ClassNotFoundException {
        ArrayList<Integer> teamIds= (ArrayList<Integer>) selectTeams();
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
                              overs, br);
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
    }


}
