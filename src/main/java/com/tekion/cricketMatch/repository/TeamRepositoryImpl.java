package com.tekion.cricketMatch.repository;
import com.tekion.cricketMatch.beans.CountryName;
import com.tekion.cricketMatch.beans.PlayerDetails;
import com.tekion.cricketMatch.beans.TeamStats;
import com.tekion.cricketMatch.dto.Player;
import com.tekion.cricketMatch.dto.PlayerDTO;
import com.tekion.cricketMatch.dto.Team;
import com.tekion.cricketMatch.utils.MatchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TeamRepositoryImpl implements  TeamRepository {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertTeamsStats(int matchId, Team teamOne, Team teamTwo) {
        insertPlayerStats(matchId, teamOne.getTeamId(), teamOne.getSquad());
        insertPlayerStats(matchId, teamTwo.getTeamId(), teamTwo.getSquad());
        insertTeamStat(matchId, teamOne, teamTwo);
        insertTeamStat(matchId, teamTwo, teamOne);
    }

    public void insertTeamStat(int matchId, Team teamOne, Team teamTwo) {
            jdbcTemplate.update("insert into Team(MatchId, TeamId, Runs, WicketsTaken) values (?,?,?,?)", new Object[]{
                    matchId,  teamOne.getTeamId(), teamOne.getScore(), teamTwo.getWicketsFallen()});
    }

    public void insertPlayerStats(int matchId, int teamId, ArrayList<Player> players) {
        for (Player player : players) {
            jdbcTemplate.update("insert into Player(MatchId, TeamId, PlayerId, RunsScored, OversBowled, WicketsTaken) values (?,?,?,?,?,?)",
                    new Object[]{ matchId, teamId, player.getPlayerId(), player.getRunsScored(), player.getOversBowled() + "." + player.getBowlsBowled(), player.getNumberOfWickets()});
        }
    }

    public TeamStats checkTeamExistence(int matchId, int teamId) {
        return jdbcTemplate.query("select * from Team where matchId=? and teamId=?", new RowMapper<TeamStats>(){
            @Override
            public TeamStats mapRow(ResultSet rs, int rownumber) throws SQLException {
                TeamStats teamStats=new TeamStats();
                teamStats.setMatchId(rs.getInt(1));
                teamStats.setTeamId(rs.getInt(2));
                teamStats.setRuns(rs.getInt(3));
                teamStats.setWicketsTaken(rs.getInt(4));
                return  teamStats;
            }}, new Object[]{matchId, teamId}).get(0);
    }

    public String getTeamName(int teamId) {
        return jdbcTemplate.query("select * from CountryName where CountryId=?",new RowMapper<CountryName>(){
            @Override
            public CountryName mapRow(ResultSet rs, int rownumber) throws SQLException {
                CountryName countryName=new CountryName();
                countryName.setCountryId(rs.getInt(1));
                countryName.setCountryName(rs.getString(2));
                return countryName;
            }
        }, teamId).get(0).getCountryName();
    }

    public ArrayList<PlayerDTO> getTeamPlayers(int teamId) {
        ArrayList<PlayerDTO> players = new ArrayList<>();
        List<PlayerDetails> playerDetail=jdbcTemplate.query("select * from PlayerDetails where teamId=?",new RowMapper<PlayerDetails>(){
            @Override
            public PlayerDetails mapRow(ResultSet rs, int rownumber) throws SQLException {
                PlayerDetails playerDetails=new PlayerDetails();
                playerDetails.setPlayerId(rs.getInt(1));
                playerDetails.setTeamId(rs.getInt(2));
                playerDetails.setPlayerName(rs.getString(3));
                playerDetails.setPlayerType(rs.getString(4));
                playerDetails.setBowlingType(rs.getString(5));
                return playerDetails;
            }
        }, teamId);
            for(int player=0; player<playerDetail.size(); player++) {
                PlayerDTO currPlayer = new PlayerDTO(playerDetail.get(player).getPlayerId(), playerDetail.get(player).getPlayerName(),
                        MatchUtil.convertToPlayerType(playerDetail.get(player).getPlayerType()), MatchUtil.convertToBowlerType(playerDetail.get(player).getBowlingType()));
                players.add(currPlayer);
            }
            return players;
        }
}
