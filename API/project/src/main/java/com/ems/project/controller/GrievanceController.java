package com.ems.project.controller;

import com.ems.project.entity.Grievance;
import com.ems.project.service.GrievanceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        Grievance grievance = grievanceService.getGrievanceDetailsById(id);

            return  grievance;


    }

    @PutMapping("/{id}")
    public Grievance updateGrievance(@PathVariable Long id, @RequestBody Grievance grievance) {

        Grievance updatedDetails = grievanceService.updateGrievanceDetails(id, grievance);
        return  updatedDetails;
    }
}
