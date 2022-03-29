package com.tekion.cricketMatch.repository;

import com.tekion.cricketMatch.beans.CountryName;
import com.tekion.cricketMatch.beans.MatchDetails;
import com.tekion.cricketMatch.dto.Match;
import com.tekion.cricketMatch.dto.MatchCreationRequest;
import com.tekion.cricketMatch.dto.TeamCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchRepositoryImpl implements MatchRepository {

    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CountryName> getTeams(){
        return jdbcTemplate.query("select * from CountryName",new RowMapper<CountryName>(){
            @Override
            public CountryName mapRow(ResultSet rs, int rownumber) throws SQLException {
                CountryName countryName=new CountryName();
                countryName.setCountryId(rs.getInt(1));
                countryName.setCountryName(rs.getString(2));
                return countryName;
            }
        });
        }

    public void createMatch(MatchCreationRequest matchCreationRequest) {
        try {
            MatchDetails matchDetails = new MatchDetails(matchCreationRequest.getMatchId(), matchCreationRequest.getTeamOneId(),
                    matchCreationRequest.getTeamTwoId(), "Ongoing Match");
            jdbcTemplate.update("insert into matchdetails(MatchId, TeamOneId, TeamTwoId, Result) values (?,?,?,?)",
                    new Object[]{matchDetails.getMatchId(), matchDetails.getTeamOneId(), matchDetails.getTeamTwoId(), matchDetails.getResult()});
        } catch(Exception e){
            new IllegalStateException("Cannot create a duplicate Match");
        }
    }

    @Override
    public List<CountryName> selectTeams(int teamOneId, int teamTwoId) {
        List<CountryName> teams=getTeams();
        System.out.println(teams.size());
        List<CountryName> matchTeams=new ArrayList<>();
        for(int i=0; i<teams.size(); i++) {
            if(teams.get(i).getCountryId()==teamOneId) {
                matchTeams.add(teams.get(i));
            } else if(teams.get(i).getCountryId()==teamTwoId) {
                matchTeams.add(teams.get(i));
            }
        }
        if(matchTeams.size()==2){
            return matchTeams;
            } else {
                throw new IllegalStateException("Enter valid Team Ids");
        }
    }

    @Override
    public void storeMatchResults(Match match) {
        try {
            jdbcTemplate.update("update MatchDetails set result=? where MatchId=? and TeamOneId=? and TeamTwoId=?", new Object[]{match.getResult(), match.getMatchId(), match.getTeam1().getTeamId(), match.getTeam2().getTeamId()});
        } catch (Exception e) {
            throw new IllegalStateException("Some issue with your update method");
        }
    }

    @Override
    public int validateMatchAndTeamIds(TeamCreationRequest teamCreationRequest){
        try{
        return jdbcTemplate.query("select * from matchdetails where MatchId=? and TeamOneId=? and TeamTwoId=?",new RowMapper<MatchDetails>(){
            @Override
            public MatchDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
                MatchDetails matchDetails=new MatchDetails();
                matchDetails.setMatchId(rs.getInt(1));
                matchDetails.setTeamOneId(rs.getInt(2));
                matchDetails.setTeamTwoId(rs.getInt(3));
                matchDetails.setResult(rs.getString(4));
                return matchDetails;
            }
        }, new Object[]{teamCreationRequest.getMatchId(), teamCreationRequest.getTeamOneId(), teamCreationRequest.getTeamTwoId()}).get(0).getMatchId();
    } catch (Exception e){
            throw new IllegalStateException("No duplicate matchId allowed");
        }
    }

    public MatchDetails checkMatchResult(int matchId) {
        return jdbcTemplate.query("select * from matchdetails where MatchId=?",new RowMapper<MatchDetails>(){
            @Override
            public MatchDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
                MatchDetails matchDetails=new MatchDetails();
                matchDetails.setMatchId(rs.getInt(1));
                matchDetails.setTeamOneId(rs.getInt(2));
                matchDetails.setTeamOneId(rs.getInt(3));
                matchDetails.setResult(rs.getString(4));
                return matchDetails;
            }
        }, new Object[]{matchId}).get(0);
    }


    @Override
    public MatchDetails validateMatchId(int matchId) {
        return jdbcTemplate.query("select * from matchdetails where MatchId=?",new RowMapper<MatchDetails>(){
            @Override
            public MatchDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
                MatchDetails matchDetails=new MatchDetails();
                matchDetails.setMatchId(rs.getInt(1));
                matchDetails.setTeamOneId(rs.getInt(2));
                matchDetails.setTeamTwoId(rs.getInt(3));
                matchDetails.setResult(rs.getString(4));
                return matchDetails;
            }
        }, new Object[]{matchId}).get(0);
    }
}
