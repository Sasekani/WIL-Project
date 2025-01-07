package com.ems.project.service;

import com.ems.project.entity.Grievance;
import com.ems.project.repository.GrievanceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrievanceService {
    private final GrievanceRepository grievanceRepository;

    public GrievanceService(GrievanceRepository grievanceRepository) {
        this.grievanceRepository = grievanceRepository;
    }

    public List<Grievance> getGrievance() {
        return grievanceRepository.findAll();
    }

    public Grievance saveGrievance(Grievance grievance){
        return grievanceRepository.save(grievance);
    }

    public Grievance getGrievanceByEmail(String email) {
        return grievanceRepository.findByEmail(email);
    }

    public Grievance getGrievanceDetailsById(long id) {
        return grievanceRepository.findById(id).orElse(null);
    }

    public Grievance updateGrievanceDetails(long id, Grievance grievance) {
        return grievanceRepository.findById(id)
                .map(existingGrievanceDetails -> {
                    existingGrievanceDetails.setEmail(grievance.getEmail());
                    existingGrievanceDetails.setTitle(grievance.getTitle());
                    existingGrievanceDetails.setDescription(grievance.getDescription());
                    existingGrievanceDetails.setStatus(grievance.getStatus());
                    return grievanceRepository.save(existingGrievanceDetails);
                })
                .orElseThrow(() -> new EntityNotFoundException("LeaveDetails not found with id: " + id));
    }
}
