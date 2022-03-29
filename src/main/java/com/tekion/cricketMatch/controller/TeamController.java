package com.tekion.cricketMatch.controller;

import com.tekion.cricketMatch.beans.PlayerStats;
import com.tekion.cricketMatch.beans.TeamStats;
import com.tekion.cricketMatch.validators.MatchValidation;
import com.tekion.cricketMatch.validators.TeamValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class TeamController {
        private MatchValidation matchValidation;
        private TeamValidation teamValidation;

        @Autowired
        public void setService(MatchValidation matchValidation, TeamValidation teamValidation){
            this.teamValidation=teamValidation;
            this.matchValidation=matchValidation;
        }

        @RequestMapping(path = "/getPlayersScore/{matchId}/{teamId}/{playerId}",method = RequestMethod.GET)
        public PlayerStats getPlayersScore(@PathVariable int matchId, @PathVariable int teamId, @PathVariable int playerId) throws SQLException, ClassNotFoundException {
            return teamValidation.validatePlayer(matchId, teamId, playerId);
        }

        @RequestMapping(path = "/getTeamScore/{matchId}/{teamId}", method = RequestMethod.GET)
        public TeamStats getTeamScore(@PathVariable int matchId, @PathVariable int teamId) throws SQLException, ClassNotFoundException {
            return teamValidation.validateTeam(matchId, teamId);
        }
}