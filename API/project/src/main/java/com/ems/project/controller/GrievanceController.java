package com.ems.project.controller;

import com.ems.project.entity.Grievance;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/Grievance")
public class GrievanceController {

    private final GrievanceService grievanceService;

    @PostMapping
    public Grievance saveGrievance (@RequestBody Grievance grievance){
        return grievanceService.saveGrievance(grievance);
    }

    @GetMapping("/{email}")
    public Grievance findByEmail(@PathVariable String email) {
        return grievanceService.getByEmail(email);
    }

    @GetMapping
    public List<Grievance> getAllGrievance() {
        return grievanceService.getGrievance();
    }

    @GetMapping("/{id}")
    public Grievance getById(@PathVariable Long id) {
        Grievance grievance = grievanceService.getById(id);

            return  grievance;


    }

    @PutMapping("/{id}")
    public Grievance updateGrievance(@PathVariable Long id, @RequestBody Grievance grievance) {

        Grievance updatedDetails = grievanceService.updateGrievance(id, grievance);
        return  updatedDetails;
    }
}
