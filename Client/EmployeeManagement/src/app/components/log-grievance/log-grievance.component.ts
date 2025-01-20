import { CommonModule, DatePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { GrievanceInterface } from '../../interface/grievanceinterface';
import { GrievanceService } from '../../service/grievance.service';

@Component({
  selector: 'app-log-grievance',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './log-grievance.component.html',
  styleUrls: ['./log-grievance.component.css'],
  providers: [DatePipe]
})
export class LogGrievanceComponent {

  GrievanceLogForm!: FormGroup;
  alert: boolean = false;
  errorMessage: string | null = null; // For displaying backend errors

  constructor(private grievanceService: GrievanceService, private fb: FormBuilder, private router: Router, private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.GrievanceLogForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      createdDate: ['', [Validators.required]],
      title: ['', [Validators.required, Validators.minLength(2)]],
      description: ['', [Validators.required, Validators.minLength(5)]],
      status: ['Open'] // No need for validators here, it's a default value
    });
  }

  logGrievances() {
    this.errorMessage = null; // Clear any previous error messages
    if (this.GrievanceLogForm.invalid) {
      // Improved error handling: Display specific error messages
      Object.keys(this.GrievanceLogForm.controls).forEach(key => {
        const controlErrors = this.GrievanceLogForm.get(key)?.errors;
        if (controlErrors) {
          console.log(`Error in field ${key}:`, controlErrors);
        }
      });
      alert("Please fill out all required fields correctly.");
      return;
    }

    const createdDateValue = this.GrievanceLogForm.get('createdDate')?.value;

    // Check if createdDateValue is valid before transforming it
    if (createdDateValue) {
      const formattedDate = this.datePipe.transform(createdDateValue, 'yyyy-MM-dd');

      const grievance: GrievanceInterface = {
        ...this.GrievanceLogForm.value,
        createdDate: formattedDate! // Use the formatted date
      };

      this.grievanceService.logGrievances(grievance).subscribe({
        next: () => {
          this.alert = true;
          this.GrievanceLogForm.reset();
          this.router.navigate(["grievanceList"]);
        },
        error: (error) => {
          console.error("Error logging grievance:", error);
          if (error.error && error.error.message) {
            this.errorMessage = error.error.message; // Display backend error message
          } else if (error.message) {
            this.errorMessage = error.message
          } else {
            this.errorMessage = "An unexpected error occurred. Please try again."; // Generic error message
          }
          alert(this.errorMessage); // Display the error to the user. Consider a better way to display errors, like in the template.
        }
      });
    } else {
      // Handle the case where the date is null or invalid.
      this.errorMessage = "Please select a valid date.";
      alert(this.errorMessage)
      console.error("Created Date is invalid.");
    }

  }

  goToDashBoard() {
    this.router.navigate(['/dashboard']);
  }
}