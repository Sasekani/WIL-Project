import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LeaveDetails } from '../interface/leave-details';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LeaveService {

  constructor(private http: HttpClient) { }

  private baseUrl = "http://localhost:8080/leave";

  // Method to apply leave
  applyLeave(leaveDetails: LeaveDetails): Observable<object> {
    return this.http.post<object>(`${this.baseUrl}`, leaveDetails);
  }

  // Method to get all leave details
  getLeaveDetails(): Observable<LeaveDetails[]> {
    return this.http.get<LeaveDetails[]>(`${this.baseUrl}`);
  }
  
  
  

  // Method to get leave details by ID
  getLeaveDetailsById(id: number): Observable<LeaveDetails> {
    return this.http.get<LeaveDetails>(`${this.baseUrl}/${id}`);
  }

  // Method to update leave details
  updateLeaveDetails(id: number, leaveDetails: Partial<LeaveDetails>): Observable<LeaveDetails> {
    return this.http.put<LeaveDetails>(`${this.baseUrl}/${id}`, leaveDetails);
  }

  // Method to update the status of a leave request
  updateLeaveRequestStatus(id: number, status: string): Observable<LeaveDetails> {
    return this.http.put<LeaveDetails>(`${this.baseUrl}/${id}?status=${status}`, {});
  }

  // Method to get user by ID
  getUserById(id: number): Observable<LeaveDetails> {
    return this.http.get<LeaveDetails>(`${this.baseUrl}/${id}`);
  }
}