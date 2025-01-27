import { CommonModule, DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GrievanceInterface  } from '../../interface/grievanceinterface';
import { GrievanceService } from '../../service/grievance.service';

@Component({
  selector: 'app-log-grievance',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './log-grievance.component.html',
  styleUrls: ['./log-grievance.component.css'],
  providers: [DatePipe]
})
export class LogGrievanceComponent implements OnInit {

  grievanceForm!: FormGroup;

  constructor(
    private grievanceService: GrievanceService,
    private fb: FormBuilder,
    private datePipe: DatePipe
  ) { }

  ngOnInit(): void {
    this.grievanceForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      createdDate: [this.datePipe.transform(new Date(), 'yyyy-MM-dd'), Validators.required], // Format date
      status: ['Open', Validators.required],
      title: ['', Validators.required],
      description: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.grievanceForm.valid) {
        const grievance: GrievanceInterface = {
            email: this.grievanceForm.get('email')?.value,
            createdDate: this.grievanceForm.get('createdDate')?.value, 
            status: this.grievanceForm.get('status')?.value,
            title: this.grievanceForm.get('title')?.value,
            description: this.grievanceForm.get('description')?.value,
        };
        // ... rest of the code
    


      this.grievanceService.saveGrievance(grievance)
        .subscribe({
          next: (data) => {
            console.log('Grievance saved:', data);
            this.grievanceForm.reset();
            alert("Grievance submitted successfully");
          },
          error: (error) => {
            console.error('Error saving grievance:', error);
            alert("Error submitting grievance");
          }
        });
    } else {
      console.log("form is invalid");
      this.markFormGroupTouched(this.grievanceForm);
    }
  }

  markFormGroupTouched(formGroup: FormGroup) {
    (<any>Object).values(formGroup.controls).forEach((control: any) => {
      control.markAsTouched();
      if (control.controls) {
        this.markFormGroupTouched(control);
      }
    });
  }
}