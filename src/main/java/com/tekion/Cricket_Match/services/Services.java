package com.tekion.Cricket_Match.services;
import com.tekion.Cricket_Match.dto.MatchController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.tekion.Cricket_Match.repository.PlayerDetailsRepository.getPlayerScoreFromRepo;
import static com.tekion.Cricket_Match.repository.TeamRepository.getTeamScore;

public class Services {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void playMatch() throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        MatchController matchController = new MatchController(br);
        matchController.startMatch();
    }

    public static ArrayList<String> getPlayerScoreService(int teamId, int playerId) throws SQLException, ClassNotFoundException {
    return getPlayerScoreFromRepo(teamId, playerId);
    }

    public static ArrayList<String> getTeamScoreService(int matchId, int teamId) throws SQLException, ClassNotFoundException {
        return getTeamScore(matchId, teamId);
    }
}
