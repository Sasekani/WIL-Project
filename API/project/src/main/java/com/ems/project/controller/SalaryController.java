package com.ems.project.controller;

import com.ems.project.entity.Salary;
import com.ems.project.service.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
@CrossOrigin("*")
public class SalaryController {

    private final SalaryService salaryService;

    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping
    public Salary createSalary(@RequestBody Salary salary) {
        return salaryService.saveSalary(salary);
    }

    @GetMapping
    public List<Salary> getAllSalaries() {
        return salaryService.getAllSalaries();
    }

    @GetMapping("/{id}")
    public Salary getSalaryById(@PathVariable Long id) {
        return salaryService.getSalaryById(id);
    }

    @PutMapping("/{id}")
    public Salary updateSalary(@PathVariable Long id, @RequestBody Salary salary) {
        Salary existingSalary = salaryService.getSalaryById(id);
        if (existingSalary != null) {
            existingSalary.setAmount(salary.getAmount());
            return salaryService.saveSalary(existingSalary);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteSalary(@PathVariable Long id) {
        salaryService.deleteSalary(id);
    }

}
