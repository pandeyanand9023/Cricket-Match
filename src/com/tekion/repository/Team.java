package com.tekion.repository;
import com.tekion.database.*;
import com.tekion.match.Player;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Team {
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
    public static void insertTeamsStats(int matchId, com.tekion.match.Team teamOne, com.tekion.match.Team teamTwo) throws SQLException, ClassNotFoundException{
        insertPlayerStats(matchId, teamOne.getSquad());
        insertPlayerStats(matchId, teamTwo.getSquad());
        try {
            insertTeamStat(matchId, teamOne, teamTwo);
            insertTeamStat(matchId, teamTwo, teamOne);
        }
        catch (SQLException sqlException){
            connection.rollback();
            throw sqlException;
        }
    }

    private static void insertTeamStat(int matchId, com.tekion.match.Team teamOne, com.tekion.match.Team teamTwo) throws SQLException, ClassNotFoundException {
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

    public static void insertPlayerStats(int matchId, ArrayList<Player> players) throws SQLException, ClassNotFoundException {
        connection = Connection.getConnection();
        connection.setAutoCommit(false);
        try {
            statement = connection.prepareStatement("insert into Player(MatchId, PlayerId, RunsScored, OversBowled, WicketsTaken) values (?,?,?,?,?)");
            int playerId = 0;
            for (Player player : players) {
                statement.setInt(1, matchId);
                statement.setInt(2, playerId);
                statement.setInt(3, player.getRunsScored());
                statement.setString(4, "" + player.getOversBowled() + "." + player.getBowlsBowled());
                statement.setInt(5, player.getNumberOfWickets());
                playerId++;
                statement.addBatch();
            }
            statement.executeBatch();

        } catch (SQLException sqlException) {
            connection.rollback();
            throw sqlException;
        }
    }
}
