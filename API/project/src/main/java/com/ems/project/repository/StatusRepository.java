package com.ems.project.repository;

import com.ems.project.entity.Grievance;
import com.ems.project.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    List<Status> findByGrievance(Grievance grievance);
}
