package com.example.demo.sportsapp.repository.impl;

import com.example.demo.sportsapp.entity.dto.*;
import com.example.demo.sportsapp.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcTeamRepository implements TeamRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<TeamDTO> teamMapper = (rs, rowNum) -> TeamDTO.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .league(LeagueDTO.builder()
                    .id(rs.getLong("league_id"))
                    .name(rs.getString("league_name"))
                    .build())
            .build();

    @Override
    public Optional<TeamDTO> findById(Long id) {
        String sql = """
            SELECT t.id, t.name, t.league_id, l.name AS league_name
            FROM team t
            JOIN league l ON t.league_id = l.id
            WHERE t.id = ?
            """;
        List<TeamDTO> teams = jdbcTemplate.query(sql, teamMapper, id);
        if (teams.isEmpty()) return Optional.empty();

        TeamDTO team = teams.get(0);
        team.setCoach(getCoach(id));
        team.setPlayers(getPlayers(id));
        return Optional.of(team);
    }

    private CoachDTO getCoach(Long teamId) {
        return jdbcTemplate.queryForObject("SELECT id, name FROM coach WHERE team_id = ?",
                (rs, rowNum) -> CoachDTO.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build(),
                teamId);
    }

    private List<PlayerDTO> getPlayers(Long teamId) {
        return jdbcTemplate.query("SELECT id, name FROM player WHERE team_id = ?",
                (rs, rowNum) -> PlayerDTO.builder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .build(),
                teamId);
    }

    @Override
    public List<TeamDTO> findAll() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void save(TeamDTO teamDTO, Long leagueId, Long coachId, List<Long> playerIds) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void update(TeamDTO teamDTO, Long leagueId, Long coachId, List<Long> playerIds) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

}
