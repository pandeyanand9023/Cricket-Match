package com.tekion.Cricket_Match.validators;

import com.tekion.Cricket_Match.beans.PlayerStats;
import com.tekion.Cricket_Match.beans.TeamStats;
import com.tekion.Cricket_Match.repository.PlayerDetailsRepository;
import com.tekion.Cricket_Match.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class PlayerValidation {
    private PlayerDetailsRepository playerDetailsRepository;
    private TeamRepository teamRepository;

    @Autowired
    public void setRepository(PlayerDetailsRepository playerDetailsRepository){
        this.playerDetailsRepository = playerDetailsRepository;
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

}
