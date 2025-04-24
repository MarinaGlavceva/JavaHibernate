package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.dto.CoachDTO;
import com.example.demo.sportsapp.repository.CoachRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CoachService {

    private final CoachRepository coachRepository;

    public List<CoachDTO> getAllCoaches() {
        return coachRepository.findAll();
    }

    public CoachDTO getCoachById(Long id) {
        return coachRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Coach with ID " + id + " not found"));
    }

    public CoachDTO createCoach(CoachDTO dto) {
        return coachRepository.save(dto);
    }

    public CoachDTO updateCoach(Long id, CoachDTO dto) {
        if (coachRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Coach with ID " + id + " not found");
        }
        return coachRepository.update(id, dto);
    }

    public void deleteCoach(Long id) {
        coachRepository.delete(id);
    }
}
