package com.tekion.Cricket_Match.repository;

import com.tekion.Cricket_Match.beans.CountryName;
import com.tekion.Cricket_Match.beans.MatchDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface MatchRepository {

     List<CountryName> getTeams() throws SQLException, ClassNotFoundException;

     List<Integer> selectTeams() throws SQLException, ClassNotFoundException;

     void storeMatchResults(int matchId, int teamOneId, int teamTwoId, String winner)throws SQLException, ClassNotFoundException;

      MatchDetails checkMatchResult(int matchId) throws ClassNotFoundException, SQLException;

      ResultSet getMatchResult(int matchId) throws SQLException, ClassNotFoundException ;
}
