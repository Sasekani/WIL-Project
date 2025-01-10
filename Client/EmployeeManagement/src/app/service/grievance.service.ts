import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GrievanceInterface } from '../interface/grievanceinterface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GrievanceService {

  private baseUrl = "http://localhost:8080/grievance";

  constructor(private httpClient: HttpClient) { }

  // Logging a grievance
  logGrievances(grievance: GrievanceInterface): Observable<any> {
    return this.httpClient.post(this.baseUrl, grievance);
  }

  // Getting all grievances
  getAllGrievance(): Observable<GrievanceInterface[]> {
    return this.httpClient.get<GrievanceInterface[]>(this.baseUrl);
  }

  // Getting grievances by email
  grievanceByEmail(userEmailAddress: string): Observable<GrievanceInterface[]> {
    return this.httpClient.get<GrievanceInterface[]>(`${this.baseUrl}/${userEmailAddress}`);
  }

  // Updating status by ID
  updateGrievanceStatus(grievanceId: number, newStatus: string): Observable<object> {
    const url = `${this.baseUrl}/${grievanceId}?status=${newStatus}`;
    return this.httpClient.put(url, {});
  }

  // Getting grievance details by ID
  getGrievanceDetailsById(id: number): Observable<GrievanceInterface> {
    return this.httpClient.get<GrievanceInterface>(`${this.baseUrl}/${id}`);
  }

  // Updating grievance details
  updateGrievanceDetails(id: number, grievance: Partial<GrievanceInterface>): Observable<GrievanceInterface> {
    return this.httpClient.put<GrievanceInterface>(`${this.baseUrl}/${id}`, grievance);
  }
}