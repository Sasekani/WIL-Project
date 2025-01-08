package com.ems.project.service;

import com.ems.project.entity.Grievance;
import com.ems.project.entity.Status;
import com.ems.project.repository.StatusRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Optional<Status> getStatusById(Long id) {
        return statusRepository.findById(id);
    }

    public List<Status> getAllStatuses() {
        return statusRepository.findAll();
    }

    public Status saveStatus(Status status) {
        return statusRepository.save(status);
    }

    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }

    public List<Status> getStatusesByGrievance(Grievance grievance) {
        return statusRepository.findByGrievance(grievance);
    }


}