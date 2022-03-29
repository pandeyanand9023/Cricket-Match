package com.tekion.cricketMatch.validators;

import com.tekion.cricketMatch.beans.CountryName;
import com.tekion.cricketMatch.beans.MatchDetails;
import com.tekion.cricketMatch.dto.MatchCreationRequest;
import com.tekion.cricketMatch.dto.TeamCreationRequest;
import com.tekion.cricketMatch.repository.MatchRepository;
import com.tekion.cricketMatch.utils.MatchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MatchValidation {
    private MatchRepository matchRepository;

    @Autowired
    public void setRepository(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    public List<CountryName> validateMatchRequest(MatchCreationRequest matchCreationRequest) {
        if(matchCreationRequest.getTeamOneId()== matchCreationRequest.getTeamTwoId()){
            throw new IllegalStateException("Both team Ids can never be the same");
        } else if(!MatchUtil.validateOvers(matchCreationRequest.getOvers())){
            throw new IllegalStateException("Only  10, 20 or 50 overs are allowed");
        } else{
            return checkIfTeamExists(matchCreationRequest.getTeamOneId(), matchCreationRequest.getTeamTwoId());
        }
    }

    public List<CountryName> checkIfTeamExists(int teamOneId, int teamTwoId) {
        return matchRepository.selectTeams(teamOneId, teamTwoId);
    }

    public int validateIds(TeamCreationRequest teamCreationRequest) throws SQLException, ClassNotFoundException {
        if(matchRepository.validateMatchAndTeamIds(teamCreationRequest)==-1){
            throw new IllegalStateException("Match Id not found");
        }else{
            return matchRepository.validateMatchAndTeamIds(teamCreationRequest);
        }
    }

    public MatchDetails validateMatch(int matchId){
        return matchRepository.validateMatchId(matchId);
    }
}
