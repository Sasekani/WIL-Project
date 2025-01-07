package com.ems.project.controller;

import com.ems.project.entity.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Status")
public class StatusController {

    private final StatusService statusService;

    @PostMapping()
    public ResponseEntity<Status> createStatus(@RequestBody Status status){
        Status createStatus = statusService.createStatus(status);
        return ResponseEntity.status(HttpStatus.CREATED).body(createStatus);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Status>> getStatusByGrievanceId(@PathVariable Long id) {
        Optional<Status> status = statusService.getStatusByGrievanceId(id);
        return ResponseEntity.ok(status);
    }

    @GetMapping()
    public ResponseEntity<List<Status>> getAllStatusByGrievance() {
        List<Status> statuses = statusService.getAllStatusByGrievance();
        return ResponseEntity.ok(statuses);
    }


    @PutMapping()
    public ResponseEntity<Status> updateStatus(@RequestBody Status status) {


        Status updatedStatus = statusService.updateStatus(status);
        return ResponseEntity.ok(updatedStatus);
    }


}
