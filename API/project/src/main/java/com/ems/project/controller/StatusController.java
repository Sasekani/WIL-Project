package com.ems.project.controller;

import com.ems.project.entity.Grievance;
import com.ems.project.entity.Status;
import com.ems.project.service.GrievanceService; // Import GrievanceService
import com.ems.project.service.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/statuses") // More appropriate plural name
public class StatusController {

    private final StatusService statusService;
    private final GrievanceService grievanceService; // Inject GrievanceService

    public StatusController(StatusService statusService, GrievanceService grievanceService) {
        this.statusService = statusService;
        this.grievanceService = grievanceService;
    }

    @PostMapping("/grievance/{grievanceId}") // Create status for a specific grievance
    public ResponseEntity<Status> createStatus(@PathVariable Long grievanceId, @RequestBody Status status) {
        try {
            Grievance grievance = grievanceService.getGrievanceDetailsById(grievanceId).orElseThrow(() -> new NoSuchElementException("Grievance not found"));
            Status createdStatus = statusService.createStatus(grievance, status.getStatus()); // Use the correct createStatus method
            return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Status> getStatusById(@PathVariable Long id) {
        return statusService.getStatusById(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Status>> getAllStatuses() {
        List<Status> statuses = statusService.getAllStatuses();
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/grievance/{grievanceId}")
    public ResponseEntity<List<Status>> getStatusesByGrievance(@PathVariable Long grievanceId) {
        try {
            Grievance grievance = grievanceService.getGrievanceDetailsById(grievanceId).orElseThrow(() -> new NoSuchElementException("Grievance not found"));
            List<Status> statuses = statusService.getStatusesByGrievance(grievance);
            return ResponseEntity.ok(statuses);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{id}") // Update by ID
    public ResponseEntity<Status> updateStatus(@PathVariable Long id, @RequestBody Status status) {
        try {
            Status existingStatus = statusService.getStatusById(id).orElseThrow(() -> new NoSuchElementException("Status not found"));
            existingStatus.setStatus(status.getStatus()); // Update the necessary fields
            Status updatedStatus = statusService.saveStatus(existingStatus);
            return ResponseEntity.ok(updatedStatus);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        try {
            statusService.deleteStatus(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) { // Catching a broader exception is not ideal, but sufficient for this example
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}