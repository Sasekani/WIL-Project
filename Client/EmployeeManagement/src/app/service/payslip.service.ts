import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payslip } from '../payslip';

@Injectable({
  providedIn: 'root'
})
export class PayslipService {
  private baseUrl = "http://localhost:8081/payslip";

  constructor(private httpClient: HttpClient) { }

  // Method to get the list of payslips
  getPayslipList(): Observable<any> {
    return this.httpClient.get(this.baseUrl);
  }

  savePayslip(payslip: Payslip) {
    return this.httpClient.post<Payslip>(`this.baseUrl/save/`, payslip);
  }

  // Method to download a payslip file by ID
  downloadFile(payslipId: number): Observable<Blob> {
    const url = `${this.baseUrl}/${payslipId}`;
    return this.httpClient.get(url, { responseType: 'blob' });
  }
}
