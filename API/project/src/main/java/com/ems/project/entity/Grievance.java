package com.ems.project.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data

@NoArgsConstructor

public class Grievance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @CreatedDate
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private Timestamp createdDate;

    @Transient // This field is not persisted in the database
    private String createdDateString;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false, length = 30)
    private String status;


    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    // Setter methods (if needed)
    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreatedDate(Timestamp createdate) {
        this.createdDate = createdate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDateString() {
        return createdDateString;
    }

    public void setCreatedDateString(String createdDateString) {
        this.createdDateString = createdDateString;
    }
}
