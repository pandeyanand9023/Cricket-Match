package com.tekion.repository;

import com.tekion.database.Connection;
import com.tekion.match.Player;
import com.tekion.match.Team;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDetails {
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
    public static void insertTeamData(Team team) throws SQLException, ClassNotFoundException{
        ArrayList<Player> squad = team.getSquad();
        connection = Connection.getConnection();
        connection.setAutoCommit(false);
        try {
            insertPlayersData(team.getTeamId(), team.getName(), squad);
            connection.commit();
        }
        catch (SQLException sqlException){
            connection.rollback();
            throw sqlException;
        }
    }

    private static void insertPlayersData(int teamId, String teamName,  List<Player> players) throws SQLException {
        statement = connection.prepareStatement("insert into PlayerDetails(PlayerId, TeamId, PlayerName, TeamName, Playertype, BowlingType) values (?,?,?,?,?,?)");
        int playerId=0;
        for(Player player:players){
            statement.setInt(1, playerId);
            statement.setInt(2, teamId);
            statement.setString(3, player.getName());
            statement.setString(4, teamName);
            statement.setString(5,""+player.getPlayerType());
            statement.setString(6,""+player.getBowlerType());
            playerId++;
            statement.addBatch();
        }
        statement.executeBatch();
    }
}
