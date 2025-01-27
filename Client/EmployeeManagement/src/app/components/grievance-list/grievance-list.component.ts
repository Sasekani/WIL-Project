import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GrievanceInterface } from '../../interface/grievanceinterface';
import { GrievanceService } from '../../service/grievance.service';
import { UsersService } from '../../service/users.service'; // This import can be removed if not used elsewhere

@Component({
  selector: 'app-grievance-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './grievance-list.component.html',
  styleUrl: './grievance-list.component.css'
})
export class GrievanceListComponent implements OnInit {
  errorMessage: string = '';
  grievanceArray: GrievanceInterface[] = [];

  constructor(
    private grievanceService: GrievanceService,
    private router: Router
  ) {}

  async ngOnInit(): Promise<void> {
    try {
      // Get all grievances without filtering based on user role
      this.grievanceService.getAllGrievances().subscribe(
        (data: GrievanceInterface[]) => {
          this.grievanceArray = data;
        },
        error => {
          this.showError('Error fetching grievances');
        }
      );
    } catch (error: any) {
      this.showError(error.message);
    }
  }

  goToDashBoard() {
    this.router.navigate(['/dashboard']);
  }

  update(id: number | undefined) {
    if (id !== undefined) {
      this.router.navigate([`/updateGrievance/${id}`]);
    } else {
      // Handle case when id is undefined, e.g., log an error or show a message
      console.error("Grievance ID is undefined.");
    }
  }
  

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  }
}