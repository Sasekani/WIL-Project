package com.ems.project.controller;

import com.ems.project.entity.Grievance;
import com.ems.project.service.GrievanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/grievance")
public class GrievanceController {

    private final GrievanceService grievanceService;

    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @PostMapping
    public ResponseEntity<Grievance> saveGrievance(@RequestBody Grievance grievance) {
        try {
            Grievance savedGrievance = grievanceService.saveGrievance(grievance);
            return new ResponseEntity<>(savedGrievance, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
        }
    }

    @PostMapping("/test")
    public String test(Grievance grievance) {
        return grievance.getEmail();
    }

    @GetMapping("/{email}")
    public Grievance findByEmail(@PathVariable String email) {
        return grievanceService.getGrievanceByEmail(email);
    }

    @GetMapping
    public List<Grievance> getAllGrievances() {
        return grievanceService.getGrievances();
    }

    @GetMapping("/{id}")
    public Grievance getById(@PathVariable Long id) {
        // Updated to handle Optional<Grievance>
        return grievanceService.getGrievanceDetailsById(id)
                .orElseThrow(() -> new NoSuchElementException("Grievance not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Grievance updateGrievance(@PathVariable Long id, @RequestBody Grievance grievance) {

        Grievance updatedDetails = grievanceService.updateGrievanceDetails(id, grievance);
        return  updatedDetails;
    }
}
