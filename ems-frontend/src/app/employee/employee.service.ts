import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http'
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators'
import { IEmployee } from './employee.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
 
  private baseURL = "http://localhost:8080/api/v1/employees";

  constructor(private httpClient: HttpClient) { }
  
  getEmployeesList(): Observable<IEmployee[]>{
    return this.httpClient.get<IEmployee[]>(`${this.baseURL}`);
  }

  getReportigUserList(userType: string): Observable<Array<{id: number, name: string}>>{
    let queryParams = new HttpParams();
    queryParams = queryParams.append("userType",userType);
    return this.httpClient.get<Array<{id: number, name: string}>>(`${this.baseURL}`,{params:queryParams});
  }

  createEmployee(employee: IEmployee): Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`, employee);
    ;
  }

  getEmployeeById(id: number): Observable<any>{
    return this.httpClient.get<any>(`${this.baseURL}/${id}`);
  }

  getEmployeeDetailsById(id: number): Observable<IEmployee>{
    return this.httpClient.get<IEmployee>(`${this.baseURL}/${id}`);
  }

  updateEmployee(id: number, employee: IEmployee): Observable<Object>{
    return this.httpClient.put(`${this.baseURL}/${id}`, employee);
  }

  deleteEmployee(id: number): Observable<Object>{
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }

  getUserTypes() {
    return this.httpClient.get<string[]>(`${this.baseURL}/types`);
  }
}
