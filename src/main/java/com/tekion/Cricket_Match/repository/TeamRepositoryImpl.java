package com.tekion.Cricket_Match.repository;
import com.tekion.Cricket_Match.beans.TeamStats;
import com.tekion.Cricket_Match.database.*;
import com.tekion.Cricket_Match.dto.Player;
import com.tekion.Cricket_Match.dto.Team;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.tekion.Cricket_Match.database.Connection.getConnection;

@Repository
public class TeamRepositoryImpl implements  TeamRepository{
    static java.sql.Connection connection;
    static {
        try {
            connection = Connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    static PreparedStatement statement=null;
    public void insertTeamsStats(int matchId, Team teamOne, Team teamTwo) throws SQLException, ClassNotFoundException{
        insertPlayerStats(matchId, teamOne.getTeamId(), teamOne.getSquad());
        insertPlayerStats(matchId, teamTwo.getTeamId(), teamTwo.getSquad());
        try {
            insertTeamStat(matchId, teamOne, teamTwo);
            insertTeamStat(matchId, teamTwo, teamOne);
        }
        catch (SQLException sqlException){
            connection.rollback();
            throw sqlException;
        }
    }

    public void insertTeamStat(int matchId, Team teamOne, Team teamTwo) throws SQLException, ClassNotFoundException {
        connection = Connection.getConnection();
        connection.setAutoCommit(false);
        statement = connection.prepareStatement("insert into Team(MatchId, TeamId, Runs, WicketsTaken) values (?,?,?,?)");
        statement.setInt(1, matchId);
        statement.setInt(2, teamOne.getTeamId());
        statement.setInt(3, teamOne.getScore());
        statement.setInt(4,teamTwo.getWicketsFallen());
        statement.execute();
        connection.commit();
    }

    public void insertPlayerStats(int matchId, int teamId,  ArrayList<Player> players) throws SQLException, ClassNotFoundException {
        connection = Connection.getConnection();
        connection.setAutoCommit(false);
        try {
            statement = connection.prepareStatement("insert into Player(MatchId, TeamId, PlayerId, RunsScored, OversBowled, WicketsTaken) values (?,?,?,?,?,?)");
            int playerId = 0;
            for (Player player : players) {
                statement.setInt(1, matchId);
                statement.setInt(2,teamId);
                statement.setInt(3, playerId);
                statement.setInt(4, player.getRunsScored());
                statement.setString(5, "" + player.getOversBowled() + "." + player.getBowlsBowled());
                statement.setInt(6, player.getNumberOfWickets());
                playerId++;
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException sqlException) {
            connection.rollback();
            throw sqlException;
        }
    }

    public TeamStats checkTeamExistence(int matchId, int teamId) throws ClassNotFoundException, SQLException {
        ResultSet resultSet=getTeamScore(matchId, teamId);
        if(resultSet==null) {
            return null;
        }
        return new TeamStats(matchId, teamId, resultSet.getInt("Runs"), resultSet.getInt("WicketsTaken"));
    }

    public ResultSet getTeamScore(int matchId, int teamId) throws SQLException, ClassNotFoundException {
        connection = getConnection();
        connection.setAutoCommit(false);
        statement = connection.prepareStatement("select * from Team where matchId=? and teamId=?");
        statement.setInt(1, matchId);
        statement.setInt(2,teamId);
        ResultSet teamStats = statement.executeQuery();
        return teamStats;
    }

}
