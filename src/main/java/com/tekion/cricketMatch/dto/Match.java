package com.tekion.cricketMatch.dto;
import com.tekion.cricketMatch.repository.MatchRepository;
import com.tekion.cricketMatch.repository.PlayerDetailsRepository;
import com.tekion.cricketMatch.repository.TeamRepository;
import com.tekion.cricketMatch.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;

public class Match {
    private int matchId;
    private int overs;
    private Team team1;
    private Team team2;
    String result;
    private int innings;
    MatchRepository matchRepository;
    PlayerDetailsRepository playerDetailsRepository;
    TeamRepository teamRepository;


    @Autowired
    public Match(int matchId, int overs, MatchRepository matchRepository, PlayerDetailsRepository playerDetailsRepository, TeamRepository teamRepository){
        this.matchRepository=matchRepository;
        this.playerDetailsRepository=playerDetailsRepository;
        this.teamRepository=teamRepository;
        this.matchId= matchId;
        this.overs=overs;
    }


    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public int getOvers() {
        return overs;
    }

    public void setInnings(int innings) {
        this.innings = innings;
    }

    public void initializeTeam(int teamOneId, int teamTwoId, int overs){
        team1=new Team(teamOneId, teamRepository.getTeamName(teamOneId), teamRepository.getTeamPlayers(teamOneId), overs);
        team2=new Team(teamTwoId, teamRepository.getTeamName(teamTwoId), teamRepository.getTeamPlayers(teamTwoId), overs);
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getInnings() {
        return innings;
    }
}
