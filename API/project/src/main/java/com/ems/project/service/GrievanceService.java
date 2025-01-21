package com.ems.project.service;

import com.ems.project.entity.Grievance;
import com.ems.project.repository.GrievanceRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GrievanceService {

    private final GrievanceRepository grievanceRepository;

    public GrievanceService(GrievanceRepository grievanceRepository) {
        this.grievanceRepository = grievanceRepository;
    }

    public Grievance saveGrievance(Grievance grievance) {
        return grievanceRepository.save(grievance);
    }

    public List<Grievance> getAllGrievances() {
        return grievanceRepository.findAll();
    }

    public Grievance getGrievanceByEmail(String email) {
        return grievanceRepository.findByEmail(email);


    }

    public Optional<Grievance> getGrievanceById(Long id) {
        return grievanceRepository.findById(id);
    }

    public void deleteGrievance(Long id) {
        grievanceRepository.deleteById(id);
    }

    public Grievance updateGrievance(Long id, Grievance updatedGrievance) {
        return grievanceRepository.findById(id)
                .map(grievance -> {
                    grievance.setEmail(updatedGrievance.getEmail());
                    grievance.setTitle(updatedGrievance.getTitle());
                    grievance.setDescription(updatedGrievance.getDescription());
                    grievance.setStatus(updatedGrievance.getStatus());

                    return grievanceRepository.save(grievance);
                })
                .orElse(null);
    }
}