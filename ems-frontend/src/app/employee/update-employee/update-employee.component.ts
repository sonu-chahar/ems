import { Component, OnInit } from '@angular/core';
import { IEmployee } from '../employee.model';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DepartmentService } from '../department.service';

@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  id: number = 0;
  employee: IEmployee = {
    id: 0,
    name: '',
    phoneNumber: '',
    emailId: '',
    dateOfJoining: '',
    dateOfBirth: ''
  };
  departmentList :Array<{id: number, name: string}> = [];

  reportingUserList :Array<{id: number, name: string}>=[];


  userTypeList: string[] = [];

  constructor(private employeeService: EmployeeService,private departmentService: DepartmentService,
    private route: ActivatedRoute,
    private router: Router) { 
      
    }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.employeeService.getEmployeeById(this.id).subscribe(data => {
      this.employee = data;
      // let reportingUser = this.employee.reportingUserId;
      if(this.employee.userType){
        this.employeeService.getReportigUserList(this.employee.userType).subscribe(data => {
          this.reportingUserList = data;
        });
      }
      
      this.departmentService.getDepartmentList().subscribe(data => {
        this.departmentList = data;
      });

      this.employeeService.getUserTypes().subscribe(data => {
        this.userTypeList = data;
      });
      
    }, error => console.log(error));
  }

  onSubmit(){
    this.employeeService.updateEmployee(this.id, this.employee).subscribe( data =>{
      this.goToEmployeeList();
    }
    , error => console.log(error));
  }

  goToEmployeeList(){
    this.router.navigate(['/ems']);
  }

  onUserTypeChange(usrType:string) {
    this.employeeService.getReportigUserList(usrType).subscribe(data => {
      this.reportingUserList = data;
    });
  }

}
