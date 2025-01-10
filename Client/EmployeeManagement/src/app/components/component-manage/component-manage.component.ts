import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { LeaveService } from '../../service/leave.service';
import { LeaveDetails } from '../../interface/leave-details';

@Component({
  selector: 'app-component-manage',
  standalone: true,
  imports: [RouterLink, CommonModule],
  templateUrl: './component-manage.component.html',
  styleUrl: './component-manage.component.css'
})
export class ComponentManageComponent implements OnInit{

  leaveDetails!: LeaveDetails[];

   isManager = true;

  constructor(private leaveService: LeaveService, private router: Router) {}

  ngOnInit(): void 
  {
    // Initialize leaveDetails here

    this.leaveService.getLeaveDetails().subscribe((data: LeaveDetails[]) => {
      this.leaveDetails = data;
    }, error => {
      console.error('Error fetching leave requests:', error);
    });
  }

 
  updateStatus(index: number, newStatus: string): void {
    // Directly update the status in the local copy of leaveDetails
    this.leaveDetails[index].status = newStatus;

    // Create a copy of the current LeaveDetails object to avoid direct mutation
    const updatedLeaveDetail = {...this.leaveDetails[index], status: newStatus};

    // Call the updateLeaveRequestStatus method with the updated LeaveDetails object
    this.leaveService.updateLeaveRequestStatus(this.leaveDetails[index].id, updatedLeaveDetail).subscribe(() => {
      // Refresh the list or notify the user of success
      console.log('Leave status updated successfully');
    }, error => {
      console.error('Error updating leave status:', error);
    });
  }

}
