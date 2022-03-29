package com.tekion.cricketMatch.repository;
import com.tekion.cricketMatch.beans.TeamStats;
import com.tekion.cricketMatch.dto.Player;
import com.tekion.cricketMatch.dto.PlayerDTO;
import com.tekion.cricketMatch.dto.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TeamRepository {

    void insertTeamsStats(int matchId, Team teamOne, Team teamTwo);

    void insertTeamStat(int matchId, Team teamOne, Team teamTwo);

    void insertPlayerStats(int matchId, int teamId,  ArrayList<Player> players);

    TeamStats checkTeamExistence(int matchId, int teamId) throws ClassNotFoundException, SQLException;

    String getTeamName(int teamId);

    ArrayList<PlayerDTO> getTeamPlayers(int teamId);
}
