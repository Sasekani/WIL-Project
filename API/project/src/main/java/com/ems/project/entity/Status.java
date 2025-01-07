package com.ems.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String open;
    private String IN_PROGRESS;
    private String RESOLVED;
    private String CLOSED;

    @ManyToOne
    @JoinColumn(name = "id")
    private  Grievance grievance;
}
