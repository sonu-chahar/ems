import { Component, OnInit } from '@angular/core';
import { IEmployee } from '../employee.model';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { DepartmentService } from '../department.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ErrorHandlerService } from 'src/app/errorhandler.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employee: IEmployee = {
    id: 0,
    name: '',
    phoneNumber: '',
    emailId: '',
    dateOfJoining: '',
    dateOfBirth: ''
  };

  addEmployeeError: boolean= false;

  departmentList :Array<{id: number, name: string}> = [];

  reportingUserList :Array<{id: number, name: string}>=[];


  userTypeList: string[] = [];
  
  
  constructor(private employeeService: EmployeeService,
    private departmentService: DepartmentService, 
    private errorHandlerService:ErrorHandlerService,
    private router: Router) { }

  ngOnInit(): void {
    this.departmentService.getDepartmentList().subscribe({
      next: (data: Array<{id: number, name: string}>) => this.departmentList = data,
      error: (err: HttpErrorResponse) => {
          this.errorHandlerService.handleError(err);
      }
    });
    
    this.employeeService.getUserTypes().subscribe(data => {
      this.userTypeList = data;
    });
  }

  goToEmployeeList(){
    this.router.navigate(['/ems']);
  }

  onUserTypeChange(usrType:string) {
      this.employeeService.getReportigUserList(usrType).subscribe(data => {
        this.reportingUserList = data;
      }); 
  }
  
  addEmployee(){
    console.log(this.employee);
    this.addEmployeeError = false;
    this.saveEmployee();
  }

  saveEmployee(){
    this.addEmployeeError=false;
    this.employeeService.createEmployee(this.employee).subscribe({
      next: (data) => {
        console.log(data);
        this.goToEmployeeList();},
      error: () => this.addEmployeeError=true
    }) ;
  }

}
