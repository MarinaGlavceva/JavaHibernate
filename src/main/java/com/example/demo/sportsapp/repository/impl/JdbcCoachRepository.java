package com.example.demo.sportsapp.repository.impl;

import com.example.demo.sportsapp.entity.dto.CoachDTO;
import com.example.demo.sportsapp.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcCoachRepository implements CoachRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<CoachDTO> rowMapper = (rs, rowNum) -> CoachDTO.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .teamId(rs.getObject("team_id", Long.class))
            .build();

    @Override
    public List<CoachDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM coach", rowMapper);
    }

    @Override
    public Optional<CoachDTO> findById(Long id) {
        List<CoachDTO> coaches = jdbcTemplate.query("SELECT * FROM coach WHERE id = ?", rowMapper, id);
        return coaches.stream().findFirst();
    }

    @Override
    public CoachDTO save(CoachDTO dto) {
        String sql = "INSERT INTO coach (name, team_id) VALUES (?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, dto.getName(), dto.getTeamId());
        dto.setId(id);
        return dto;
    }

    @Override
    public CoachDTO update(Long id, CoachDTO dto) {
        jdbcTemplate.update("UPDATE coach SET name = ?, team_id = ? WHERE id = ?",
                dto.getName(), dto.getTeamId(), id);
        dto.setId(id);
        return dto;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM coach WHERE id = ?", id);
    }
}
