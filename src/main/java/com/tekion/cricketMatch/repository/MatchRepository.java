package com.tekion.cricketMatch.repository;

import com.tekion.cricketMatch.beans.CountryName;
import com.tekion.cricketMatch.beans.MatchDetails;
import com.tekion.cricketMatch.dto.Match;
import com.tekion.cricketMatch.dto.MatchCreationRequest;
import com.tekion.cricketMatch.dto.TeamCreationRequest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MatchRepository {

     List<CountryName> getTeams() throws SQLException, ClassNotFoundException;

     List<CountryName> selectTeams(int teamOneId, int teamTwoId);

     void storeMatchResults(Match match);

     int validateMatchAndTeamIds(TeamCreationRequest matchId);

     MatchDetails checkMatchResult(int matchId);

     void createMatch(MatchCreationRequest matchCreationRequest);

     MatchDetails validateMatchId(int matchId);
}
