import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SalaryComponent } from './components/salary/salary.component';
import { PaysliplistComponent } from './components/paysliplist/paysliplist.component';
import { GrievanceListComponent } from './components/grievance-list/grievance-list.component';
import { HomeComponent } from './components/home/home.component';
import { LeaveDetailsListComponent } from './components/leave-details-list/leave-details-list.component';
import { LeaveDetailsComponent } from './components/leave-details/leave-details.component';
import { LogGrievanceComponent } from './components/log-grievance/log-grievance.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegisterComponent } from './components/register/register.component';
import { UpdateuserComponent } from './components/updateuser/updateuser.component';
import { UserlistComponent } from './components/userlist/userlist.component';
import { UpdateLeaveStatusComponent } from './components/update-leave-status/update-leave-status.component';
import { adminGuard, usersGuard } from './users.guard';

export const routes: Routes = [
  
  {path: '', component: LoginComponent},
  {path: 'login', component: LoginComponent},
  {path:'dashboard',component:DashboardComponent},
  {path:'salary',component:SalaryComponent},
  {path:'payslip',component:PaysliplistComponent},
  {path:'applyleave',component:LeaveDetailsComponent},
  {path:'updateStatus',component:UpdateLeaveStatusComponent},
  {path:'leavelistdetails',component:LeaveDetailsListComponent},
  {path: 'loggrievance', component:LogGrievanceComponent},
  {path: 'grievanceList', component:GrievanceListComponent},
  {path: 'home', component:HomeComponent},
  {path: 'register', component: RegisterComponent, canActivate: [adminGuard]},
  {path: 'profile', component: ProfileComponent, canActivate: [usersGuard]},
  {path: 'update/:id', component: UpdateuserComponent, canActivate: [adminGuard]},
  {path: 'users', component: UserlistComponent, canActivate:[adminGuard]},


  {path: '**', component: LoginComponent},
  
  {path: '', redirectTo: '/login', pathMatch: 'full'},
];
