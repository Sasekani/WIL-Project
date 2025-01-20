package com.ems.project.service;

import com.ems.project.entity.Payslip;
import com.ems.project.repository.PayslipRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayslipService {

    private final PayslipRepository payslipRepository;

    public PayslipService(PayslipRepository payslipRepository) {
        this.payslipRepository = payslipRepository;
    }

    public List<Payslip> getAllPayslips() {
        return payslipRepository.findAll();
    }

    public Payslip getPayslipById(Long id) {
        return payslipRepository.findById(id).orElse(null);
    }

    public Payslip savePayslip(Payslip payslip) {
        return payslipRepository.save(payslip);
    }

    public void deletePayslip(Long id) {
        payslipRepository.deleteById(id);
    }
}

