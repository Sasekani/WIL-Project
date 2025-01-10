import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  constructor(private router:Router){

  }
  goToProfile(){
    this.router.navigate(['/profile'])
  }

  goToPayslip(){
    this.router.navigate(['/payslip'])
  }

   goToApplyleave(){
    this.router.navigate(['/applyleave'])
  }

  goTologGrievance(){
    this.router.navigate(['/loggrievance'])
  }

  grievanceList(){
    this.router.navigate(['/grievanceList'])
  }
  leavelistdetails(){
    this.router.navigate(['/leavelistdetails'])
  }

}
