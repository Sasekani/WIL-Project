import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { LeaveService } from '../../service/leave.service';
import { LeaveDetails } from '../../interface/leave-details';

@Component({
  selector: 'app-update-status',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './update-status.component.html',
  styleUrl: './update-status.component.css'
})
export class UpdateStatusComponent implements OnInit{
  id!: number;

  leaveDetails!: LeaveDetails[];
  isManager = true;

  constructor(private leaveService: LeaveService) {}

  ngOnInit(): void {
    this.leaveService.getLeaveDetails().subscribe((data: LeaveDetails[]) => {
      this.leaveDetails = data;
    }, error => {
      console.error('Error fetching leave requests:', error);
    });
  }


}
