package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.Match;
import com.example.demo.sportsapp.entity.dto.MatchDTO;
import com.example.demo.sportsapp.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class MatchService {

    private final MatchRepository matchRepository;

    public List<MatchDTO> getAllMatches() {
        return matchRepository.findAll();
    }

    public MatchDTO getMatchById(Long id) {
        return matchRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Match with ID " + id + " not found"));
    }

    public MatchDTO createMatch(MatchDTO dto) {
        return matchRepository.save(dto);
    }

    public MatchDTO updateMatch(Long id, MatchDTO dto) {
        if (matchRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Match with ID " + id + " not found");
        }
        return matchRepository.update(id, dto);
    }

    public void deleteMatch(Long id) {
        matchRepository.delete(id);
    }
}


