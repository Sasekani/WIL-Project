package com.ems.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payslip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private double grossPay;
    private double deductions;
    private double netPay;
    private String payPeriod;

    @OneToOne(cascade = CascadeType.ALL)
    private Salary salary;

    public void setDeductions(double deductions) {
        this.deductions = deductions;
        calculateNetSalary();
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
        calculateNetSalary();

    }

    public void setSalary(Salary salary) {
        this.salary = salary;
        updateGrossPay();
    }

    public void updateGrossPay() {
        if (this.salary != null) {
            this.grossPay = this.salary.getAmount();
            calculateNetSalary();
        }
    }

    public void calculateNetSalary() {
        this.netPay = this.grossPay - this.deductions;
    }

}
