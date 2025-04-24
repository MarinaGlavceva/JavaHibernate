package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.dto.LeagueDTO;
import com.example.demo.sportsapp.repository.LeagueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public List<LeagueDTO> getAllLeagues() {
        return leagueRepository.findAll();
    }

    public LeagueDTO getLeagueById(Long id) {
        return leagueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("League with ID " + id + " not found"));
    }

    public LeagueDTO createLeague(LeagueDTO dto) {
        return leagueRepository.save(dto);
    }

    public LeagueDTO updateLeague(Long id, LeagueDTO dto) {
        if (leagueRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("League with ID " + id + " not found");
        }
        return leagueRepository.update(id, dto);
    }

    public void deleteLeague(Long id) {
        leagueRepository.delete(id);
    }
}
