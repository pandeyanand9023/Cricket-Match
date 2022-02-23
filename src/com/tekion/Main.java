package com.tekion;
import com.tekion.match.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
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

Minutes of the meeting (Monday):
1. setPlayerDetails: Pass info to a void method and then setDetails there (Refactor) (Done)
2. Put remainingRuns inside printChoices use and If there (Use loop or something to avoid numbering disturbance) (Done)
3. Optimize the changeBowler (Make changeBowler pick in constant time and keep updating the list after each over)(Done)
4. Toss should be done inside MatchController // No teams should be mentioned inside matchController
5. Think of the condition about overs exhausted and make effective changes


DataBase:
1. Decide tables
2. Models
3. Relationship

 */