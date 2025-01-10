import { CommonModule } from '@angular/common';
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
  styleUrl: './log-grievance.component.css'
})
export class LogGrievanceComponent {

  GrievanceLogForm!: FormGroup;
  alert: boolean=false;
  ;
  // grievanceInterfaces: GrievanceInterface[]=[]; // Initialize your grievance data array

  // constructor(private grievanceService: GrievanceService) {}
  constructor(private GrievanceService: GrievanceService, private fb: FormBuilder, private root:Router, private router:Router ){}

  ngOnInit(): void {
    // Fetch grievance data based this based on your service)
    // this.getGrievance();
    this.GrievanceLogForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      createdDate: ['', [Validators.required]],
      title: ['', [Validators.required,]],
      description: ['', [Validators.required]],
      status: ['', [Validators.required]]
      })
}

logGrievances(){//a function to send all that is type on the form to log grievances

  
  const grievance: GrievanceInterface = {
    ...this.GrievanceLogForm.value,
       status:"Open"}

  console.log(grievance);
  this.GrievanceService.logGrievances(grievance).subscribe(()=>{
    console.log();
    console.log(grievance);
    this.alert=true;
    this.GrievanceLogForm.reset({});

  })
  this.root.navigate(["grievanceList"])

}
goToDashBoard(){
  this.router.navigate(['/dashboard'])
}

}
