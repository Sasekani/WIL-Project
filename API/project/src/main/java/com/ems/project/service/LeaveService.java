package com.ems.project.service;

import com.ems.project.entity.LeaveDetails;
import com.ems.project.repository.LeaveRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public LeaveService(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    public LeaveDetails updateLeaveRequestStatus(Long id, String status) {


        LeaveDetails leaveDetails = leaveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("LeaveDetails not found with id: " + id));

        leaveDetails.setStatus(status);
        return leaveRepository.save(leaveDetails);
    }

    public LeaveDetails applyLeave(LeaveDetails leaveDetails)
    {
        return leaveRepository.save(leaveDetails);
    }

    public List<LeaveDetails> getAllLeaves()
    {
        return leaveRepository.findAll();
    }

    public LeaveDetails getLeaveById(long id) {

        return leaveRepository.findById(id).orElse(null);
    }

    public LeaveDetails updateLeaveDetails(long id, LeaveDetails leaveDetails) {
        return leaveRepository.findById(id)
                .map(existingLeaveDetails -> {
                    existingLeaveDetails.setName(leaveDetails.getName());
                    existingLeaveDetails.setLeaveType(leaveDetails.getLeaveType());
                    existingLeaveDetails.setStartDate(leaveDetails.getStartDate());
                    existingLeaveDetails.setEndDate(leaveDetails.getEndDate());
                    existingLeaveDetails.setReason(leaveDetails.getReason());
                    existingLeaveDetails.setStatus(leaveDetails.getStatus());
                    return leaveRepository.save(existingLeaveDetails);
                })
                .orElseThrow(() -> new EntityNotFoundException("LeaveDetails not found with id: " + id));
    }


}
