package com.tekion.Cricket_Match.controller;
import com.tekion.Cricket_Match.repository.MatchRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.tekion.Cricket_Match.services.Services.*;

@RestController
public class Controller {

    @RequestMapping("/getTeams")
    public List<String> getTeams() throws SQLException, ClassNotFoundException {
        return MatchRepository.getTeams();
    }

    @RequestMapping("/startMatch")
    public String startMatch() throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        playMatch();
        return "Match Ended";
    }

    @RequestMapping("/getPlayersScore/teamId/playerId")
    public List<String> getPlayersScore(int teamId, int playerId) throws SQLException, ClassNotFoundException {
        return getPlayerScoreService(teamId, playerId);
    }

    @RequestMapping("/getTeamScore/matchId/teamId")
    public List<String> getTeamScore(int matchId, int teamId) throws SQLException, ClassNotFoundException {
        return getTeamScoreService(matchId, teamId);
    }

}