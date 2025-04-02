package com.example.demo.sportsapp.service;

import com.example.demo.sportsapp.entity.Coach;
import com.example.demo.sportsapp.entity.dto.CoachDTO;
import com.example.demo.sportsapp.repository.CoachRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoachService {

    private final CoachRepository coachRepository;

    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public List<CoachDTO> getAllCoaches() {

        List<Coach> coaches = coachRepository.findAll();
        List<CoachDTO> resultList = new ArrayList<>();
        for (Coach coach : coaches) {
            resultList.add(CoachDTO.builder()
                    .id(coach.getId())
                    .name(coach.getName())
                    .teamId(coach.getTeam().getId())
                    .build());
        }
        return resultList;
    }

    public CoachDTO getCoachById(Long id) {
        Optional<Coach> coach = coachRepository.findById(id);

        return CoachDTO.builder()
                .id(coach.get().getId())
                .name(coach.get().getName())
                .teamId(coach.get().getTeam().getId())
                .build();
    }

    public CoachDTO createCoach(CoachDTO coachDTO) {
        Coach coach = new Coach();
        coach.setName(coachDTO.getName());
        coachRepository.save(coach);
        return convertToDTO(coach);
    }

    private CoachDTO convertToDTO(Coach coach) {

        return CoachDTO.builder()
                .id(coach.getId())
                .name(coach.getName())
                .build();
    }

    public CoachDTO updateCoach(Long id, CoachDTO coachDTO) {
        Coach coach = new Coach();
        if (coachRepository.findById(id).isPresent()) {
            coach = coachRepository.findById(id).get();
        }
        coach.setName(coachDTO.getName());
        coachRepository.save(coach);
        return convertToDTO(coach);
    }

    public void deleteCoach(Long id) {
        if (coachRepository.findById(id).isPresent()) {
            coachRepository.deleteById(id);
        }
    }
}

