package com.ems.project.controller;

import com.ems.project.entity.Payslip;
import com.ems.project.service.PayslipService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payslip")
public class PayslipController {

    private final PayslipService payslipService;

    public PayslipController(PayslipService payslipService) {
        this.payslipService = payslipService;
    }

    @PostMapping
    public Payslip save(@RequestBody Payslip payslip) {
        return payslipService.savePayslip(payslip);
    }

    @GetMapping
    public List<Payslip> getAllPayslips() {
        return payslipService.getAllPayslips();
    }

    @GetMapping("/{id}")
    public Payslip getPayslipById(@PathVariable Long id){
        return payslipService.getPayslipById(id);
    }

    @PutMapping("/{id}")
    public Payslip updatePayslip(@PathVariable Long id, @RequestBody Payslip payslip) {
        Payslip existingPayslip = payslipService.getPayslipById(id);
        if (existingPayslip != null) {
            existingPayslip.setGrossPay(payslip.getGrossPay());
            existingPayslip.setDeductions(payslip.getDeductions());
            return payslipService.savePayslip(existingPayslip);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deletePayslip(@PathVariable Long id) {

        payslipService.deletePayslip(id);
    }
}
