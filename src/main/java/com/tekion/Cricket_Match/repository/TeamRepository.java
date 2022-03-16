package com.tekion.Cricket_Match.repository;
import com.tekion.Cricket_Match.beans.TeamStats;
import com.tekion.Cricket_Match.dto.Player;
import com.tekion.Cricket_Match.dto.Team;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TeamRepository {

    void insertTeamsStats(int matchId, Team teamOne, Team teamTwo) throws SQLException, ClassNotFoundException;

    void insertTeamStat(int matchId, Team teamOne, Team teamTwo) throws SQLException, ClassNotFoundException;

    void insertPlayerStats(int matchId, int teamId,  ArrayList<Player> players) throws SQLException, ClassNotFoundException;

    TeamStats checkTeamExistence(int matchId, int teamId) throws ClassNotFoundException, SQLException;
    ResultSet getTeamScore(int matchId, int teamId) throws SQLException, ClassNotFoundException;

}
