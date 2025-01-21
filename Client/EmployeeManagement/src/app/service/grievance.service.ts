import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GrievanceInterface } from '../interface/grievanceinterface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GrievanceService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/grievance';

  saveGrievance(grievance: GrievanceInterface) {
    return this.http.post<GrievanceInterface>(this.baseUrl, grievance);
  }

  getAllGrievances() {
    return this.http.get<GrievanceInterface[]>(this.baseUrl);
  }

  getGrievanceByEmail(email: string) {
    return this.http.get<GrievanceInterface>(`${this.baseUrl}/${email}`);
  }

  getGrievanceById(id: number) {
    return this.http.get<GrievanceInterface>(`${this.baseUrl}/id/${id}`);
  }

  updateGrievance(id: number, grievance: GrievanceInterface) {
    return this.http.put<GrievanceInterface>(`${this.baseUrl}/${id}`, grievance);
  }

  deleteGrievance(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}