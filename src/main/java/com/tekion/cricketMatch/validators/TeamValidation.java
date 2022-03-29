package com.tekion.cricketMatch.validators;

import com.tekion.cricketMatch.beans.PlayerStats;
import com.tekion.cricketMatch.beans.TeamStats;
import com.tekion.cricketMatch.controller.TeamController;
import com.tekion.cricketMatch.dto.PlayerDTO;
import com.tekion.cricketMatch.dto.TeamCreationRequest;
import com.tekion.cricketMatch.enums.BowlerType;
import com.tekion.cricketMatch.enums.PlayerType;
import com.tekion.cricketMatch.repository.PlayerDetailsRepository;
import com.tekion.cricketMatch.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class TeamValidation {
    private PlayerDetailsRepository playerDetailsRepository;
    private TeamRepository teamRepository;

    @Autowired
    public void setRepository(PlayerDetailsRepository playerDetailsRepository, TeamRepository teamRepository){
        this.playerDetailsRepository = playerDetailsRepository;
        this.teamRepository=teamRepository;
    }

    public PlayerStats validatePlayer(int matchId, int teamId, int playerId) throws SQLException, ClassNotFoundException {
        PlayerStats playerStats=playerDetailsRepository.checkPlayerExistence(matchId, teamId, playerId);
        if(playerStats==null){
           throw new IllegalStateException("No such player found");
        }
        return playerStats;
    }

    public TeamStats validateTeam(int matchId, int teamId)throws SQLException, ClassNotFoundException {
        TeamStats teamStats=teamRepository.checkTeamExistence(matchId, teamId);
        if(teamStats==null){
            throw new IllegalStateException("No such team found");
        }
        return teamStats;
    }

    public boolean validateTeamCreation(TeamCreationRequest teamCreationRequest){
        if(teamCreationRequest.getTeamOne().size()!=11 || teamCreationRequest.getTeamTwo().size()!=11){
            throw new IllegalStateException("Team size must be 11 only");
        }else if(validatePlayerCreation(teamCreationRequest.getTeamOne(), teamCreationRequest.getTeamTwo())){
            return true;
        } else { throw new IllegalStateException("Enter the correct player details");
        }
    }

    private boolean validatePlayerCreation(ArrayList<PlayerDTO> teamOne, ArrayList<PlayerDTO> teamTwo){
        for(int i=0;i<11;i++){
            if(!isValidPlayer(teamOne.get(i), teamTwo.get(i))){
                return false;
            }
        }
        return true;
    }

    private boolean isValidPlayer(PlayerDTO playerOne, PlayerDTO playerTwo){
        return validPlayerType(playerOne.getPlayerType(), playerTwo.getPlayerType()) && validBowlerType(playerOne.getBowlerType(), playerTwo.getBowlerType());
    }

    private boolean validPlayerType(PlayerType playerOneType, PlayerType playerTwoType){
        return playerTypeCheck(playerOneType) && playerTypeCheck(playerTwoType);
    }

    private boolean playerTypeCheck(PlayerType playerType){
        return (playerType.equals(PlayerType.BATSMAN) || playerType.equals(PlayerType.BOWLER) || playerType.equals(PlayerType.ALL_ROUNDER));
    }

    private boolean validBowlerType(BowlerType bowlerOneType, BowlerType bowlerTwoType){
      return bowlerTypeCheck(bowlerOneType) && bowlerTypeCheck(bowlerTwoType);
    }

    private boolean bowlerTypeCheck(BowlerType bowlerType){
        return (bowlerType.equals(BowlerType.FAST) || bowlerType.equals(BowlerType.SPIN) ||  bowlerType.equals(BowlerType.MEDIUM_FAST));
    }



}
