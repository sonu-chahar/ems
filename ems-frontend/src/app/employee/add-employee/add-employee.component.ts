import { Component, OnInit } from '@angular/core';
import { EmployeeRequest } from '../employee-request';
import { Router } from '@angular/router';
import { EmployeeService } from '../employee.service';
import { DepartmentService } from '../department.service';

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  employee: EmployeeRequest = {
    id: 0,
    name: '',
    phoneNumber: '',
    emailId: '',
    departmentId: 0,
    userType: '',
    dateOfJoining: '',
    dateOfBirth: '',
    reportingUserId: 0
  };


  departmentList :Array<{id: number, name: string}> = [];

  reportingUserList :Array<{id: number, name: string}>=[];


  userTypeList = ['DEVELOPER','SENIOR_DEVELOPER'];
  
  
  constructor(private employeeService: EmployeeService,private departmentService: DepartmentService,
    private router: Router) { }

  ngOnInit(): void {
    this.departmentService.getDepartmentList().subscribe(data => {
      this.departmentList = data;
    });
      
  }

  saveEmployee(){
    this.employeeService.createEmployee(this.employee).subscribe( data =>{
      console.log(data);
      this.goToEmployeeList();
    },
    error => console.log(error));
  }

  goToEmployeeList(){
    this.router.navigate(['/ems']);
  }

  onUserTypeChange() {
    this.employeeService.getReportigUserList(this.employee.userType).subscribe(data => {
      this.reportingUserList = data;
    });
  }
  
  onSubmit(){
    console.log(this.employee);
    this.saveEmployee();
  }

}
