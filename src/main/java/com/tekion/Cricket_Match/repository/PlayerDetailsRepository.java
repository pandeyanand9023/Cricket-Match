package com.tekion.Cricket_Match.repository;
import com.tekion.Cricket_Match.beans.PlayerStats;
import com.tekion.Cricket_Match.dto.Player;
import com.tekion.Cricket_Match.dto.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlayerDetailsRepository {

    void insertTeamData(Team team) throws SQLException, ClassNotFoundException;

    void insertPlayersData(int teamId, String teamName,  List<Player> players) throws SQLException;

    PlayerStats checkPlayerExistence(int matchId, int teamId, int playerId)throws SQLException, ClassNotFoundException;

    ResultSet getPlayerScoreFromRepo(int matchId, int teamId, int playerId) throws SQLException, ClassNotFoundException;

}
