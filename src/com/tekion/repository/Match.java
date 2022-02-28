package com.tekion.repository;

import com.tekion.database.Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Match {
    static java.sql.Connection connection;
    static PreparedStatement statement=null;
    private static ArrayList<String> teams=new ArrayList<>();

    static {
        try {
            connection = Connection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Integer> selectTeams() throws SQLException, ClassNotFoundException {
        connection = Connection.getConnection();
        Statement statement=connection.createStatement();
        ResultSet countryNames = statement.executeQuery("select * from CountryName");
        HashMap<Integer, String> countryName=new HashMap<>();
        ArrayList<Integer> teamIds = new ArrayList<>();
        int countryNumber=0;
        while(countryNames.next()){
            countryName.put(countryNumber,countryNames.getString("CountryName"));
            teamIds.add(countryNames.getInt("CountryId"));
            countryNumber++;
        }
        int team1, team2;
        do{
            Random random=new Random();
            team1 = random.nextInt(teamIds.size());
            team2 = random.nextInt(teamIds.size());
        }while(team1 == team2);
        teams.add(countryName.get(team1));
        teams.add(countryName.get(team2));
        ArrayList<Integer> matchTeams=new ArrayList<Integer>();
        matchTeams.add(team1);
        matchTeams.add(team2);
        return matchTeams;
    }

    public static ArrayList<String> getTeamName(){
        return teams;
    }

    public static void storeMatchResults(int matchId, int teamOneId, int teamTwoId, String winner) throws SQLException, ClassNotFoundException{
        connection = Connection.getConnection();
        connection.setAutoCommit(false);
        try {
            statement = connection.prepareStatement("insert into MatchDetails(MatchId, TeamOneId, TeamTwoId, Result) values (?,?,?,?)");
            statement.setInt(1, matchId);
            statement.setInt(2, teamOneId);
            statement.setInt(3, teamTwoId);
            statement.setString(4, winner);
            statement.execute();
            connection.commit();
        }
        catch (SQLException sqlException){
            connection.rollback();
            throw sqlException;
        }
    }
}
