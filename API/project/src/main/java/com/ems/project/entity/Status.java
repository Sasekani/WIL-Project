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
    @JoinColumn(name = "grievanceId")
    private  Grievance grievance;

    public Long getId() {
        return id;
    }

    public String getOpen() {
        return open;
    }

    public String getIN_PROGRESS() {
        return IN_PROGRESS;
    }

    public String getRESOLVED() {
        return RESOLVED;
    }

    public String getCLOSED() {
        return CLOSED;
    }

    public Grievance getGrievance() {
        return grievance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public void setIN_PROGRESS(String IN_PROGRESS) {
        this.IN_PROGRESS = IN_PROGRESS;
    }

    public void setRESOLVED(String RESOLVED) {
        this.RESOLVED = RESOLVED;
    }

    public void setCLOSED(String CLOSED) {
        this.CLOSED = CLOSED;
    }

    public void setGrievance(Grievance grievance) {
        this.grievance = grievance;
    }
}
