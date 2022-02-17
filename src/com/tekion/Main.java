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
Feedbacks from Wednesday :
1. Class Diagram (Making changes so will do at last (Probably on Friday's Repo))
2. Batsman scoring more runs than bowler (Done)
3. Show scoreboard in between too. (Done)
4. rename UtilClass to MatchUtil (Done)
5. All validation methods should be moved down (Done)
6. Refactor the class later on if it exceeds above 200 lines (helper class) (Leave for now)
7. Bowler func
      1. Max overs allowed : Number of overs/5    (Done)
      2. Current bowler: Same functionality as of Strike (Done)
      3. Set some validation in userInput such that it has min number of 5 Bowlers (show description on why only bowlers are allowed)(Done)
8. Storing which runs are scored on which ball (Done)(Only last 6 balls)
9. FallOfWicket (At what runs) (Done)
10. Clear screen after every ball such that it doesn't get too clumsy (Done)
11. Read Squad from File.(Will do if this program runs okay)
12. No balls and wide balls -> Probability should be lesser than normal balls (Done)

 Read about clear screen in Java in morning
 Your clear screen is not working
 */