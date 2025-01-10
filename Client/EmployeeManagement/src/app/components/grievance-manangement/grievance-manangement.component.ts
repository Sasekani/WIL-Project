import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { GrievanceService } from '../../service/grievance.service';

@Component({
  selector: 'app-grievance-manangement',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './grievance-manangement.component.html',
  styleUrl: './grievance-manangement.component.css'
})
export class GrievanceManangementComponent implements OnInit{

  grievanceForm!: FormGroup;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private grievanceService: GrievanceService
  ) { }

  ngOnInit() {
    this.initForm();
    
    this.route.params.subscribe(params => {
      this.id = +params['id'];
      console.log('Grievance ID:', this.id); 
      this.loadGrievanceDetails(this.id);
    });
  }

  initForm() {
    this.grievanceForm = this.fb.group({
      status: ['', Validators.required]
    });
  }

  loadGrievanceDetails(id: number) {
    this.grievanceService.getGrievanceDetailsById(id).subscribe(
    grievance => {
        this.grievanceForm.patchValue({ status: grievance.status });
      },
      error => {
        console.error('Error loading grievance details', error);
      }
    );
  }

  updateGrievanceDetails() {
    if (this.grievanceForm.valid) {
      const updatedgrievance = {
        id: this.id,
        status: this.grievanceForm.get('status')?.value
      };
      this.grievanceService.updateGrievanceDetails(this.id, updatedgrievance).subscribe(
        response => {
          console.log('Grievance status updated successfully', response);
          this.router.navigate(['/grievanceList']);
        },
        error => {
          console.error('Error updating grievance status', error);
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


