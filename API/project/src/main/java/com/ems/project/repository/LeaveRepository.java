package com.ems.project.repository;

import com.ems.project.entity.LeaveDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveDetails, Long> {
}
