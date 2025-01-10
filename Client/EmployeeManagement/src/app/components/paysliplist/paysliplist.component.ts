import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Payslip } from '../../payslip';
import { PayslipService } from '../../service/payslip.service';
import { UsersService } from '../../service/users.service';
import jsPDF from 'jspdf';
import autoTable from 'jspdf-autotable';

@Component({
  selector: 'app-paysliplist',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './paysliplist.component.html',
  styleUrl: './paysliplist.component.css'
})
export class PaysliplistComponent implements OnInit {

  
  payslips: Payslip[] = [];
  profileInfo: any;
  errorMessage: string = '';

  constructor(
    private payslipService: PayslipService,
    private router: Router,
    private usersService: UsersService
  ) {}

  async ngOnInit(): Promise<void> {
    try {
      const token = localStorage.getItem('token');
      if (!token) {
        throw new Error("No Token Found");
      }

      // Get the user's profile information
      this.profileInfo = await this.usersService.getYourProfile(token);

      // Get payslips for the logged-in user
      this.getPayslips();
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  private getPayslips() {
    this.payslipService.getPayslipList().subscribe(
      (data: Payslip[]) => {
        this.payslips = data.filter(slip => slip.email === this.profileInfo.ourUsers.email);
      },
      error => {
        this.showError('Error fetching payslips');
      }
    );
  }

  public downloadFile() {
    const doc = new jsPDF();
    autoTable(doc, { html: '#payslips' });
    doc.save("payslips.pdf");
  }

  goToDashBoard() {
    this.router.navigate(['/dashboard']);
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  }

}
