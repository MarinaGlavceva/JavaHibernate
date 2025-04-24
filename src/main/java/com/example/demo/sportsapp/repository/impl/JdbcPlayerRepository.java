package com.example.demo.sportsapp.repository.impl;

import com.example.demo.sportsapp.entity.dto.PlayerDTO;
import com.example.demo.sportsapp.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcPlayerRepository implements PlayerRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<PlayerDTO> rowMapper = (rs, rowNum) -> PlayerDTO.builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .age(rs.getInt("age"))
            .teamId(rs.getObject("team_id", Long.class))
            .build();

    @Override
    public List<PlayerDTO> findAll() {
        return jdbcTemplate.query("SELECT * FROM player", rowMapper);
    }

    @Override
    public Optional<PlayerDTO> findById(Long id) {
        List<PlayerDTO> players = jdbcTemplate.query("SELECT * FROM player WHERE id = ?", rowMapper, id);
        return players.stream().findFirst();
    }

    @Override
    public PlayerDTO save(PlayerDTO dto) {
        String sql = "INSERT INTO player (name, age, team_id) VALUES (?, ?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class, dto.getName(), dto.getAge(), dto.getTeamId());
        dto.setId(id);
        return dto;
    }

    @Override
    public PlayerDTO update(Long id, PlayerDTO dto) {
        jdbcTemplate.update("UPDATE player SET name = ?, age = ?, team_id = ? WHERE id = ?",
                dto.getName(), dto.getAge(), dto.getTeamId(), id);
        dto.setId(id);
        return dto;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update("DELETE FROM player WHERE id = ?", id);
    }
}
