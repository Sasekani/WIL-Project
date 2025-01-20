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

    public List<Grievance> getGrievances() {
        return grievanceRepository.findAll();
    }

    public Grievance saveGrievance(Grievance grievance) {
        if (grievance == null) {
            throw new IllegalArgumentException("Grievance cannot be null."); // Very important null check
        }
        try {
            return grievanceRepository.save(grievance);
        } catch (Exception e) {
            // Log the exception details for debugging (use a logger, not printStackTrace in production)
            e.printStackTrace(); // For now, this is okay for local testing
            throw new GrievanceSaveException("Error saving grievance: " + e.getMessage(), e); // More specific exception
        }
    }

    public Grievance getGrievanceByEmail(String email) {
        return grievanceRepository.findByEmail(email); // Potential NullPointerException if no grievance found
    }

    public Optional<Grievance> getGrievanceDetailsById(long id) {
        return grievanceRepository.findById(id);
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
                .orElseThrow(() -> new EntityNotFoundException("Grievance not found with id: " + id));
    }
}

// Custom Exception (Good Practice)
class GrievanceSaveException extends RuntimeException {
    public GrievanceSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}