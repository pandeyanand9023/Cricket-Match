package com.tekion;
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

(Filled at the end of the match)
Match :  Primary Key: MatchId  (Integer)
         Foreign Key :Team1_Id (Integer)
         Foreign Key :Team2_Id (Integer)
                      Results  (Varchar)

(Filled the end only before filling match)
Team :                MatchId (Integer)        Primary Key : (MatchId+TeamId)
                      TeamID (Integer)
                      Runs (Integer)
                      WicketsTaken (Integer)

(Fill at the start) (Done and working fine)
PlayerDetails: Primary Key:  PlayerId        (Integer)   (Primary Key: PlayerID+TeamID)
                             TeamId          (Integer)
                             PlayerName      (Varchar)
                             PlayerType      (Varchar)
                             BowlingType     (Varchar)


(Filled at the end)
Player:       Foreign Key : MatchId         (Integer)    (MatchId+PlayerId+TeamId) Primary Key
              ForeignKey  : PlayerId        (Integer)
                            TeamId          (Integer)
                            IndividualScore (Integer)
                            OversBowled     (Float)
                            WicketsTaken    (Integer)


// Update your batsman probability of getting out

(Pre filled, used only for getting two random teams out of it) (Done and Working fine)
Country: Primary Key:  CountryId  (Integer)
                       CountryName (Varchar)
*/
/*
1. Search Caching (Basic): Types of caching
                         1. Centralized
                         2. Local
 */


