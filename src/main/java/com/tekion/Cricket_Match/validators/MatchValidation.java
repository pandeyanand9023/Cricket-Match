package com.tekion.Cricket_Match.validators;

import com.tekion.Cricket_Match.beans.MatchDetails;
import com.tekion.Cricket_Match.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MatchValidation {
    private MatchRepository matchRepository;

    @Autowired
    public void setRepository(MatchRepository matchRepository){
        this.matchRepository = matchRepository;
    }

    public MatchDetails validateMatch(int matchId) throws SQLException, ClassNotFoundException {
        MatchDetails matchDetails=matchRepository.checkMatchResult(matchId);
        if(matchDetails==null){
            throw new IllegalStateException("No such match found");
        }
        return matchDetails;
    }
}
