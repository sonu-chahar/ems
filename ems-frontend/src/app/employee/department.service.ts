import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {

  private baseURL = "http://localhost:8080/api/v1/departments";

  constructor(private httpClient: HttpClient) { }

  getDepartmentList(): Observable<Array<{id: number, name: string}>>{
    return this.httpClient.get<Array<{id: number, name: string}>>(`${this.baseURL}`);
  }
}
