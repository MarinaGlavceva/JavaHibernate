package com.example.demo.sportsapp.repository.impl;

import com.example.demo.sportsapp.entity.dto.LeagueDTO;
import com.example.demo.sportsapp.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcLeagueRepository implements LeagueRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<LeagueDTO> rowMapper = (rs, rowNum) -> LeagueDTO.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .build();

    @Override
    public List<LeagueDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM league", rowMapper);
    }

    @Override
    public Optional<LeagueDTO> findById(Long id) {
        List<LeagueDTO> result = jdbcTemplate.query("SELECT * FROM league WHERE id = ?", rowMapper, id);
        return result.stream().findFirst();
    }

    @Override
    public LeagueDTO save(LeagueDTO leagueDTO) {
        String sql = "INSERT INTO league (name) VALUES (?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, leagueDTO.getName());
        leagueDTO.setId(id);
        return leagueDTO;
    }

    @Override
    public LeagueDTO update(Long id, LeagueDTO leagueDTO) {
        jdbcTemplate.update("UPDATE league SET name = ? WHERE id = ?", leagueDTO.getName(), id);
        leagueDTO.setId(id);
        return leagueDTO;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM league WHERE id = ?", id);
    }
}
