package com.ems.project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Salary {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private double amount;

    public double getAmount() {
        return amount;
    }

    // Explicitly add the setter method for amount (if needed)
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
