package com.tekion.cricketMatch.controller;
import com.tekion.cricketMatch.beans.CountryName;
import com.tekion.cricketMatch.beans.MatchDetails;
import com.tekion.cricketMatch.dto.MatchCreationRequest;
import com.tekion.cricketMatch.dto.MatchCreationResponse;
import com.tekion.cricketMatch.dto.TeamCreationRequest;
import com.tekion.cricketMatch.services.MatchService;
import com.tekion.cricketMatch.validators.MatchValidation;
import com.tekion.cricketMatch.validators.TeamValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
public class MatchController {
    private TeamController teamController;
    private MatchService matchService;
    private MatchValidation matchValidation;
    private TeamValidation teamValidation;

   @Autowired
   public void setService( MatchService matchService, TeamValidation teamValidation, MatchValidation matchValidation, TeamController teamController){
       this.teamController=teamController;
       this.matchService=matchService;
       this.teamValidation =teamValidation;
       this.matchValidation=matchValidation;
   }

    @RequestMapping(path = "/getTeams",method = RequestMethod.GET)
    public List<CountryName> getTeams() throws SQLException, ClassNotFoundException {
        return matchService.getTeamsService();
    }

    @RequestMapping(path = "/createMatch", method = RequestMethod.POST)
    public MatchCreationResponse createMatch(@RequestBody MatchCreationRequest matchCreationRequest) {
       List<CountryName> response=matchValidation.validateMatchRequest(matchCreationRequest);
       return matchService.createMatch(matchCreationRequest, response);
    }

    @RequestMapping(path = "/createSquad", method = RequestMethod.POST)
    public TeamCreationRequest selectSquad(@RequestBody TeamCreationRequest teamCreationRequest) throws SQLException, ClassNotFoundException {
       matchValidation.validateIds(teamCreationRequest);
       teamValidation.validateTeamCreation(teamCreationRequest);
       matchService.createTeamForMatch(teamCreationRequest);
       return teamCreationRequest;
    }

    @RequestMapping(path = "/startMatch/{matchId}/{overs}", method = RequestMethod.GET)
    public String startMatch(@PathVariable int matchId, @PathVariable int overs){
        MatchDetails matchDetails=matchValidation.validateMatch(matchId);
        matchService.startMatch(matchDetails, overs);
        return "Match Ended";
    }

    @RequestMapping(path = "/matchResults/{matchId}",method = RequestMethod.GET)
    public MatchDetails getTeamScore(@PathVariable int matchId) {
        return matchValidation.validateMatch(matchId);
    }

}

/*
1. Do the testing using postman(Stress testing)



 */