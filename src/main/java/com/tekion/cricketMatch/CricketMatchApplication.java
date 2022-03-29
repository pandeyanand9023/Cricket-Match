package com.tekion.cricketMatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CricketMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricketMatchApplication.class, args);
	}

}

/*
1. Read about component ()
2. How do Spring knows about which bean to create if we use annotations (@Service, @repository, @component) ()
3. Read about @Data Annotation(Lombok Library) (Done)
4. Daily Updates on Reading Material (HeadFirst and Effective Java) 11 AM



New Flow: -> Create Match : Creates a new Match Object in DTO (Automatically creates a new MatchId)-> SelectSquad->
             SimulateToss -> playMatch -> GetScoreBoard-> getAvailableBowlersList -> selectBowler ->

             After the match ends: Fill MatchDetails Table,

             Store matchId after createMatch in db, use that for validation

*/

