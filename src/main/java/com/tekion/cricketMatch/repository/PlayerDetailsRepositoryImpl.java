package com.tekion.cricketMatch.repository;
import com.tekion.cricketMatch.beans.PlayerDetails;
import com.tekion.cricketMatch.beans.PlayerStats;
import com.tekion.cricketMatch.beans.TeamStats;
import com.tekion.cricketMatch.dto.PlayerDTO;
import com.tekion.cricketMatch.dto.TeamCreationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.List;

@Repository
public class PlayerDetailsRepositoryImpl implements PlayerDetailsRepository{

    JdbcTemplate jdbcTemplate;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insertTeamData(TeamCreationRequest teamCreationRequest){
            insertPlayersData(teamCreationRequest.getTeamOneId(), teamCreationRequest.getTeamOne());
            insertPlayersData(teamCreationRequest.getTeamTwoId(), teamCreationRequest.getTeamTwo());
    }

    @Override
    public void insertPlayersData(int teamId, List<PlayerDTO> players){
           int playerId=0;
           for (PlayerDTO player : players) {
               jdbcTemplate.update("insert into PlayerDetails(PlayerId, TeamId, PlayerName, Playertype, BowlingType) values (?,?,?,?,?)", new Object[]{
                  playerId, teamId, player.getName(), ""+player.getPlayerType(), ""+player.getBowlerType()});
               playerId++;
           }
    }

    public PlayerStats checkPlayerExistence(int matchId, int teamId, int playerId) {
       return jdbcTemplate.query("select * from Player where matchId=? and teamId=? and playerId=?", new RowMapper<PlayerStats>(){
                   @Override
                   public PlayerStats mapRow(ResultSet rs, int rownumber) throws SQLException {
                       PlayerStats playerStats=new PlayerStats();
                       playerStats.setMatchId(rs.getInt(1));
                       playerStats.setPlayerId(rs.getInt(2));
                       playerStats.setRunsScored(rs.getInt(3));
                       playerStats.setOversBowled(rs.getString(4));
                       playerStats.setWicketsTaken(rs.getInt(5));
                       playerStats.setTeamId(rs.getInt(6));
                       return  playerStats;
                   }},new Object[]{matchId, teamId, playerId}).get(0);
       }
}

