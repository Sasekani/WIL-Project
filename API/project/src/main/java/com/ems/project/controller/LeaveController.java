package com.ems.project.controller;

import com.ems.project.entity.LeaveDetails;
import com.ems.project.service.LeaveService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leave")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    private LeaveDetails applyLeave(@RequestBody LeaveDetails leaveDetails) {
        return  leaveService.applyLeave(leaveDetails);
    }

    @GetMapping
    public List<LeaveDetails> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @PutMapping("/details/{id}")
    public ResponseEntity<LeaveDetails> updateLeaveDetails(@PathVariable long id, @RequestBody LeaveDetails leaveDetails) {
        try {
            LeaveDetails updatedLeaveDetails = leaveService.updateLeaveDetails(id, leaveDetails);
            return ResponseEntity.ok(updatedLeaveDetails);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<LeaveDetails> getLeaveDetailsById(@PathVariable long id) {
        LeaveDetails leaveDetails = leaveService.getLeaveById(id);
        if (leaveDetails != null) {
            return ResponseEntity.ok(leaveDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<LeaveDetails> updateLeaveRequestStatus(@PathVariable Long id, @RequestParam String status) {
        LeaveDetails updatedLeaveDetail = leaveService.updateLeaveRequestStatus(id, status);
        return ResponseEntity.ok(updatedLeaveDetail);
    }
}
