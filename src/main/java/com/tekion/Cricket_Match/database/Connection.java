package com.tekion.Cricket_Match.database;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Connection {
    private static  String DB_URL = "jdbc:mysql://localhost:3306/CricketMatch";
    private static  String USERNAME = "root";
    private static  String PASSWORD = "AnandPandey";
    private static java.sql.Connection connection=null;
    private static PreparedStatement statement=null;

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

    public static java.sql.Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            setupConnection();
        }
        return connection;
    }
}
