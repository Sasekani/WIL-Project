import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LeaveDetails } from '../../interface/leave-details';
import { LeaveService } from '../../service/leave.service';
import { UsersService } from '../../service/users.service';

@Component({
  selector: 'app-leave-details-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './leave-details-list.component.html',
  styleUrl: './leave-details-list.component.css'
})
export class LeaveDetailsListComponent implements OnInit{

  profileInfo: any;
  errorMessage: string = '';
  leaveDetailsArray: LeaveDetails[] = [];

  constructor(
    private leaveService: LeaveService,
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

      // Get all leave details
      this.leaveService.getLeaveDetails().subscribe(
        (data: LeaveDetails[]) => {
          if (this.profileInfo?.ourUsers?.role === 'ADMIN') {
            // If the user is HR, show all leave details
            this.leaveDetailsArray = data;
          } else {
            // For other roles, filter leave details to show only those matching the logged-in user's email
            this.leaveDetailsArray = data.filter(leave => 
              leave.email === this.profileInfo?.ourUsers?.email
            );
          }
        },
        error => {
          this.showError('Error fetching leave details');
        }
      );
    } catch (error: any) {
      this.showError(error.message);
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
