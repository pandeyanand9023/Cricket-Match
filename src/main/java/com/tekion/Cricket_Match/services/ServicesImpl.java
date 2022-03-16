package com.tekion.Cricket_Match.services;
import com.tekion.Cricket_Match.beans.CountryName;
import com.tekion.Cricket_Match.dto.MatchController;
import com.tekion.Cricket_Match.repository.MatchRepository;
import com.tekion.Cricket_Match.repository.PlayerDetailsRepository;
import com.tekion.Cricket_Match.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

@Service
public class ServicesImpl implements Services{
    MatchRepository matchRepository;
    PlayerDetailsRepository playerDetailsRepository;
    TeamRepository teamRepository;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Autowired
    public void setRepository(MatchRepository matchRepository, PlayerDetailsRepository playerDetailsRepository, TeamRepository teamRepository){
        this.teamRepository=teamRepository;
        this.matchRepository=matchRepository;
        this.playerDetailsRepository=playerDetailsRepository;
    }

    public void playMatch() throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        MatchController matchController = new MatchController(br, matchRepository, teamRepository, playerDetailsRepository);
        matchController.startMatch();
    }

    public List<CountryName> getTeamsService() throws SQLException, ClassNotFoundException {
        return matchRepository.getTeams();
    }
}

