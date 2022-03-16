package com.tekion.Cricket_Match.repository;

import com.tekion.Cricket_Match.beans.PlayerStats;
import com.tekion.Cricket_Match.database.Connection;
import com.tekion.Cricket_Match.dto.Player;
import com.tekion.Cricket_Match.dto.Team;

import org.springframework.stereotype.Repository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerDetailsRepositoryImpl implements PlayerDetailsRepository{
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
    public void insertTeamData(Team team) throws SQLException, ClassNotFoundException{
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

    public void insertPlayersData(int teamId, String teamName,  List<Player> players) throws SQLException {
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

    public PlayerStats checkPlayerExistence(int matchId, int teamId, int playerId)throws SQLException, ClassNotFoundException{
        ResultSet resultSet=getPlayerScoreFromRepo(matchId, teamId, playerId);
        if(resultSet==null){
            return null;
        }
        return new PlayerStats(matchId, playerId, teamId, resultSet.getInt("RunsScored"),resultSet.getString("OversBowled"),resultSet.getInt("WicketsTaken"));
    }

    public ResultSet getPlayerScoreFromRepo(int matchId, int teamId, int playerId) throws SQLException, ClassNotFoundException {
        connection = Connection.getConnection();
        connection.setAutoCommit(false);
        statement = connection.prepareStatement("select * from Player where matchId=? and teamId=? and playerId=?");
        statement.setInt(1, matchId);
        statement.setInt(2,teamId);
        statement.setInt(3,playerId);
        ResultSet playerStats = statement.executeQuery();
        return playerStats;
    }
}
