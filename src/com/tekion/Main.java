package com.tekion;
import com.tekion.database.DB;
import com.tekion.match.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, SQLException, ClassNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String activeMatch = "1";
        do {
            MatchController matchController = new MatchController(br);
            matchController.startMatch();
            System.out.println("Want to play another match?\n1. Press 1 for Yes\n2. Press 2 for No");
            activeMatch = br.readLine();
        }
        while (activeMatch.equals("1"));
    }
}

/*
Table Structure

Match :  Primary Key: MatchId  (Integer)
         Foreign Key :Team1_Id (Integer)
         Foreign Key :Team2_Id (Integer)
                      Results  (Varchar)

Team :   Foreign Key: MatchId (Integer)
         Foreign Key: Team_ID (Integer)
                      Runs (Integer)
                      OversPlayed (Float)
                      WicketsTaken (Integer)

Player: Primary Key:  PlayerId        (Integer)
        Foreign Key:  Team_Id         (Integer)
                      PlayerName      (Varchar)
                      PlayerType      (Varchar)
                      BowlingType     (Varchar)


PlayerStats:  Foreign Key : MatchId         (Integer)
              ForeignKey  : PlayerId        (Integer)
                            IndividualScore (Integer)
                            OversBowled     (Float)
                            WicketsTaken    (Integer)
                            BowlsPlayed     (Integer)

Country: Primary Key:  CountryId  (Integer)
                       CountryName (Varchar)


 */