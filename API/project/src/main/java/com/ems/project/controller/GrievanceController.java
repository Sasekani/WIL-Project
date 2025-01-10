package com.ems.project.controller;

import com.ems.project.entity.Grievance;
import com.ems.project.service.GrievanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("*")
@RequestMapping("/grievance")
public class GrievanceController {

    private final GrievanceService grievanceService;

    public GrievanceController(GrievanceService grievanceService) {
        this.grievanceService = grievanceService;
    }

    @PostMapping
    public Grievance saveGrievance (@RequestBody Grievance grievance){
        return grievanceService.saveGrievance(grievance);
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
