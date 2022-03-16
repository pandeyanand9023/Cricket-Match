package com.tekion.Cricket_Match.repository;

import com.tekion.Cricket_Match.beans.CountryName;
import com.tekion.Cricket_Match.beans.MatchDetails;
import com.tekion.Cricket_Match.beans.TeamStats;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import static com.tekion.Cricket_Match.database.Connection.getConnection;

@Repository
public class MatchRepositoryImpl implements MatchRepository{
    static Connection connection;
    static PreparedStatement statement=null;
    private static ArrayList<String> teams=new ArrayList<>();

    static {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CountryName> getTeams()throws SQLException, ClassNotFoundException{
        List<CountryName> teams =new ArrayList<>();
        connection = getConnection();
        Statement statement=connection.createStatement();
        ResultSet countryNames = statement.executeQuery("select * from CountryName");
        while(countryNames.next()){
            CountryName currTeam=new CountryName(countryNames.getInt("CountryId"), countryNames.getString("CountryName"));
            teams.add(currTeam);
        }
        return teams;
    }

    @Override
    public List<Integer> selectTeams() throws SQLException, ClassNotFoundException {
        connection = getConnection();
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

    @Override
    public void storeMatchResults(int matchId, int teamOneId, int teamTwoId, String winner) throws SQLException, ClassNotFoundException {
        connection = getConnection();
        connection.setAutoCommit(false);
        try {
            statement = connection.prepareStatement("insert into MatchDetails(MatchId, TeamOneId, TeamTwoId, Result) values (?,?,?,?)");
            statement.setInt(1, matchId);
            statement.setInt(2, teamOneId);
            statement.setInt(3, teamTwoId);
            statement.setString(4, winner);
            statement.execute();
            connection.commit();
        } catch (SQLException sqlException) {
            connection.rollback();
            throw sqlException;
        }
    }

        public MatchDetails checkMatchResult(int matchId) throws ClassNotFoundException, SQLException {
            ResultSet resultSet=getMatchResult(matchId);
            if(resultSet==null) {
                return null;
            }
            return new MatchDetails(matchId, resultSet.getInt("teamOneId"), resultSet.getInt("teamTwoId"), resultSet.getString("result"));
        }

        public ResultSet getMatchResult(int matchId) throws SQLException, ClassNotFoundException {
            connection = getConnection();
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("select * from MatchDetails where matchId=?");
            statement.setInt(1, matchId);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        }
    }
