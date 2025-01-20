import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LeaveDetails } from '../../interface/leave-details';
import { LeaveService } from '../../service/leave.service';
import { catchError, firstValueFrom, of } from 'rxjs';

@Component({
  selector: 'app-leave-details-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './leave-details-list.component.html',
  styleUrls: ['./leave-details-list.component.css']
})
export class LeaveDetailsListComponent implements OnInit {
  errorMessage: string = '';
  leaveDetailsArray: LeaveDetails[] = [];
  loading: boolean = true;

  constructor(
    private leaveService: LeaveService,
    private router: Router
  ) {}

  async ngOnInit(): Promise<void> {
    this.loading = true; // Set loading to true initially
    try {
      this.leaveService.getLeaveDetails().pipe(
        catchError(error => {
          console.error("Error fetching leave details:", error);
          this.showError('Error fetching leave details');
          return of([]); // Return an empty array in case of error
        })
      ).subscribe(data => {
        this.leaveDetailsArray = data;
        this.loading = false; // Set loading to false after data is received
      });
    } catch (error: any) {
      this.showError('Error fetching leave details');
      this.loading = false; // Important: set loading to false even in catch block
    }
  }

  goToDashBoard() {
    this.router.navigate(['/dashboard']);
  }

  goToUpdateStatus() {
    this.router.navigate(['/updateStatus']);
  }

  showError(mess: string) {
    this.errorMessage = mess;
    setTimeout(() => {
      this.errorMessage = '';
    }, 3000);
  }
}