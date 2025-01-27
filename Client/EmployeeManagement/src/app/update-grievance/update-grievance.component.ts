import { Component, OnInit } from '@angular/core';
import { GrievanceService } from '../service/grievance.service';
import { FormGroup, FormBuilder, Validators, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { GrievanceInterface } from '../interface/grievanceinterface';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-update-grievance',
  standalone: true,
  imports: [FormsModule, CommonModule, ReactiveFormsModule],
  templateUrl: './update-grievance.component.html',
  styleUrls: ['./update-grievance.component.css']
})
export class UpdateGrievanceComponent implements OnInit {

  updateGrievanceForm!: FormGroup;
  grievance!: GrievanceInterface;

  constructor(
    private grievanceService: GrievanceService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = +this.route.snapshot.paramMap.get('id')!;

    this.grievanceService.getGrievanceById(id).subscribe(
      (data: GrievanceInterface) => {
        this.grievance = data;

        this.updateGrievanceForm = this.formBuilder.group({
          email: [this.grievance.email, [Validators.required, Validators.email]],
          createdDate: [this.grievance.createdDate, Validators.required],
          title: [this.grievance.title, Validators.required],
          description: [this.grievance.description, Validators.required]
        });
      },
      (error) => {
        console.error('Error fetching grievance:', error);
      }
    );
  }

  onSubmit(): void {
    if (this.updateGrievanceForm.valid) {
      const updatedGrievance: GrievanceInterface = {
        ...this.grievance,
        ...this.updateGrievanceForm.value
      };

      this.grievanceService.updateGrievance(this.grievance.id!, updatedGrievance).subscribe(
        (response) => {
          console.log('Grievance updated successfully:', response);
          this.router.navigate(['/grievances']); 
        },
        (error) => {
          console.error('Error updating grievance:', error);
        }
      );
    }
  }
}
