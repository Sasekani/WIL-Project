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

    @PostMapping("/grievance/{grievanceId}")
    public ResponseEntity<Status> createStatus(@PathVariable Long grievanceId, @RequestBody Status status) {
        try {
            Grievance grievance = grievanceService.getGrievanceDetailsById(grievanceId)
                    .orElseThrow(() -> new NoSuchElementException("Grievance not found"));
            status.setGrievance(grievance);
            Status createdStatus = statusService.saveStatus(status);
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
            Grievance grievance = grievanceService.getGrievanceDetailsById(grievanceId)
                    .orElseThrow(() -> new NoSuchElementException("Grievance not found"));
            List<Status> statuses = statusService.getStatusesByGrievance(grievance);
            return ResponseEntity.ok(statuses);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Status> updateStatus(@PathVariable Long id, @RequestBody Status status) {
        try {
            Status existingStatus = statusService.getStatusById(id)
                    .orElseThrow(() -> new NoSuchElementException("Status not found"));
            existingStatus.setOpen(status.getOpen());
            existingStatus.setIN_PROGRESS(status.getIN_PROGRESS());
            existingStatus.setRESOLVED(status.getRESOLVED());
            existingStatus.setCLOSED(status.getCLOSED());
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