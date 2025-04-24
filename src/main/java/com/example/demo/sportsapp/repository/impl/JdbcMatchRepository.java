package com.example.demo.sportsapp.repository.impl;

import com.example.demo.sportsapp.entity.dto.MatchDTO;
import com.example.demo.sportsapp.repository.MatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcMatchRepository implements MatchRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<MatchDTO> matchMapper = (rs, rowNum) -> MatchDTO.builder()
            .id(rs.getLong("id"))
            .date(rs.getObject("date", LocalDate.class))
            .homeTeamId(rs.getLong("home_team_id"))
            .awayTeamId(rs.getLong("away_team_id"))
            .build();

    @Override
    public List<MatchDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM match", matchMapper);
    }

    @Override
    public Optional<MatchDTO> findById(Long id) {
        List<MatchDTO> matches = jdbcTemplate.query("SELECT * FROM match WHERE id = ?", matchMapper, id);
        return matches.stream().findFirst();
    }

    @Override
    public MatchDTO save(MatchDTO dto) {
        String sql = "INSERT INTO match (date, home_team_id, away_team_id) VALUES (?, ?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class,
                dto.getDate(), dto.getHomeTeamId(), dto.getAwayTeamId());
        dto.setId(id);
        return dto;
    }

    @Override
    public MatchDTO update(Long id, MatchDTO dto) {
        jdbcTemplate.update("UPDATE match SET date = ?, home_team_id = ?, away_team_id = ? WHERE id = ?",
                dto.getDate(), dto.getHomeTeamId(), dto.getAwayTeamId(), id);
        dto.setId(id);
        return dto;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM match WHERE id = ?", id);
    }
}
