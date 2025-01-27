import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { PayslipService } from '../../service/payslip.service';
import { Payslip } from '../../payslip';

@Component({
  selector: 'app-salary',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './salary.component.html',
  styleUrl: './salary.component.css'
})
export class SalaryComponent {

  deductions!:number;
  grossPay!:number ;
  payPeriod!:String;
  email!:String;

  payslip!:Payslip;
  private baseUrl = ["http://localhost:8080"];


  constructor(private http: HttpClient, private router:Router , private payslipService: PayslipService) {

  }

  save(){
    let bodyData={
      "email":this.email,
      "grossPay":this.grossPay,
      "deductions":this.deductions,
      "payPeriod": this.payPeriod,
      "netPay": this.grossPay - this.deductions
    };

    this.payslip.deductions = this.deductions;
    this.payslip.email = this.email;
    this.payslip.grossPay = this.grossPay;
    this.payslip.netPay = this.grossPay - this.deductions;
    this.payslip.payPeriod = this.payPeriod;
    
    try {
      let salaryResponse = this.payslipService.savePayslip(this.payslip);
      console.log(salaryResponse);
      alert("Salary Paid ");
      this.router.navigate(['/Payslips'])
    } catch (error: any) {
  }
  }
}
