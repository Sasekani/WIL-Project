import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { LeaveService } from '../../service/leave.service';

@Component({
  selector: 'app-update-leave-status',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './update-leave-status.component.html',
  styleUrl: './update-leave-status.component.css'
})
export class UpdateLeaveStatusComponent implements OnInit{

  leaveForm!: FormGroup;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private leaveService: LeaveService
  ) { }

  ngOnInit() {
    this.initForm();
    
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      console.log('Leave ID:', this.id); 
      this.loadLeaveDetails(this.id);
    });
  }

  initForm() {
    this.leaveForm = this.fb.group({
      status: ['', Validators.required]
    });
  }

  loadLeaveDetails(id: number) {
    this.leaveService.getLeaveDetailsById(id).subscribe(
      leave => {
        this.leaveForm.patchValue({ status: leave.status });
      },
      error => {
        console.error('Error loading leave details', error);
      }
    );
  }

  updateLeaveStatus() {
    if (this.leaveForm.valid) {
      const updatedLeave = {
        id: this.id,
        status: this.leaveForm.get('status')?.value
      };
      this.leaveService.updateLeaveDetails(this.id, updatedLeave).subscribe(
        response => {
          console.log('Leave status updated successfully', response);
          this.router.navigate(['/leavelistdetails']);
        },
        error => {
          console.error('Error updating leave status', error);
        }
      );
    } else {
      console.log('Form is invalid');
    }
  }

  goToDashBoard() {
    this.router.navigate(['/dashboard']);
  }
}


