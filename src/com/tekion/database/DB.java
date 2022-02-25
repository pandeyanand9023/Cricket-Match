package com.tekion.database;
import java.sql.*;
import java.util.Random;
import java.util.*;

public class DB {
    private static  String DB_URL = "jdbc:mysql://localhost:3306/CricketMatch";
    private static  String USERNAME = "root";
    private static  String PASSWORD = "AnandPandey";
    private static Connection connection;
    private static ArrayList<String> teams=new ArrayList<>();

    public static void setupConnection() throws SQLException, ClassNotFoundException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e);
        }
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            setupConnection();
        }
        return connection;
    }

    public static ArrayList<String> getTeamName(){
        return teams;
    }

    public static List<Integer> selectTeams() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
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
}

