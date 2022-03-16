package com.tekion.Cricket_Match.controller;
import com.tekion.Cricket_Match.beans.CountryName;
import com.tekion.Cricket_Match.beans.MatchDetails;
import com.tekion.Cricket_Match.beans.PlayerStats;
import com.tekion.Cricket_Match.beans.TeamStats;
import com.tekion.Cricket_Match.services.Services;
import com.tekion.Cricket_Match.validators.MatchValidation;
import com.tekion.Cricket_Match.validators.PlayerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@RestController
public class Controller {
   private Services service;
   private MatchValidation matchValidation;
   private PlayerValidation playerValidation;

   @Autowired
   public void setService(Services service){
   this.service=service;
   this.playerValidation=playerValidation;
   this.matchValidation=matchValidation;
   }

    @RequestMapping("/getTeams")
    public List<CountryName> getTeams() throws SQLException, ClassNotFoundException {
        return service.getTeamsService();
    }

    @RequestMapping("/startMatch")
    public String startMatch() throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        service.playMatch();
        return "Match Ended";
    }

    @RequestMapping("/getPlayersScore/{matchId}/{teamId}/{playerId}")
    public PlayerStats getPlayersScore(@PathVariable int matchId, @PathVariable int teamId, @PathVariable int playerId) throws SQLException, ClassNotFoundException {
        return playerValidation.validatePlayer(matchId, teamId, playerId);
    }

    @RequestMapping("/getTeamScore/{matchId}/{teamId}")
    public TeamStats getTeamScore(@PathVariable int matchId, @PathVariable int teamId) throws SQLException, ClassNotFoundException {
        return playerValidation.validateTeam(matchId, teamId);
    }


    @RequestMapping("/matchResults/{matchId}")
    public MatchDetails getTeamScore(@PathVariable int matchId) throws SQLException, ClassNotFoundException {
        return matchValidation.validateMatch(matchId);
    }


}