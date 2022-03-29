package com.tekion.cricketMatch.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection {
    private static  String DB_URL = "jdbc:mysql://localhost:3306/CricketMatch";
    private static String MYSQL_CLASS = "com.mysql.cj.jdbc.Driver";
    private static  String USERNAME = "root";
    private static  String PASSWORD = "AnandPandey";
    private static Connection conn = null;
    private static PreparedStatement statement=null;

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn == null){
            initializeConnection();
        }
        return conn;
    }

    private static void initializeConnection() throws SQLException, ClassNotFoundException{
        Class.forName(MYSQL_CLASS);
        conn = (Connection) DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

}
