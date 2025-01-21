package com.ems.project.controller;

import com.ems.project.entity.Grievance;
import com.ems.project.service.GrievanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/grievance")
public class GrievanceController {

    private final GrievanceService grievanceService;

    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @PostMapping
    public Grievance saveGrievance(@RequestBody Grievance grievance) {
        return grievanceService.saveGrievance(grievance);
    }

    @GetMapping
    public List<Grievance> getAllGrievances() {
        return grievanceService.getAllGrievances();
    }

    @GetMapping("/{email}")
    public Grievance getGrievanceByEmail(@PathVariable String email) {
        return grievanceService.getGrievanceByEmail(email);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Grievance> getGrievanceById(@PathVariable Long id) {
        Optional<Grievance> grievance = grievanceService.getGrievanceById(id);
        return grievance.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrievance(@PathVariable Long id) {
        grievanceService.deleteGrievance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grievance> updateGrievance(@PathVariable Long id, @RequestBody Grievance updatedGrievance) {
        Grievance grievance = grievanceService.updateGrievance(id, updatedGrievance);
        if (grievance != null) {
            return new ResponseEntity<>(grievance, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
