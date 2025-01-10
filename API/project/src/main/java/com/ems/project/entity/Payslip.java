package com.ems.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
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

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public double getGrossPay() {
        return grossPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetPay() {
        return netPay;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public Salary getSalary() {
        return salary;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setGrossPay(double grossPay) {
        this.grossPay = grossPay;
        calculateNetSalary();
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
        calculateNetSalary();
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public void setPayPeriod(String payPeriod) {
        this.payPeriod = payPeriod;
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
