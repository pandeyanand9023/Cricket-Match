package com.tekion.cricketMatch.repository;
import com.tekion.cricketMatch.beans.PlayerStats;
import com.tekion.cricketMatch.dto.PlayerDTO;
import com.tekion.cricketMatch.dto.TeamCreationRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PlayerDetailsRepository {

    void insertTeamData(TeamCreationRequest teamCreationRequest);

    void insertPlayersData(int teamId, List<PlayerDTO> players);

    PlayerStats checkPlayerExistence(int matchId, int teamId, int playerId)throws SQLException, ClassNotFoundException;

}
