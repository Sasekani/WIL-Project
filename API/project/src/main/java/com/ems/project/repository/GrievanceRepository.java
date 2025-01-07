package com.ems.project.repository;

import com.ems.project.entity.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    Grievance findByEmail(String email);
}
