import { Component, OnInit } from '@angular/core';
import { EmployeeRequest } from '../employee-request';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css']
})
export class EmployeeDetailsComponent implements OnInit {

  id: number =0;
  employee: any;

  constructor(private route: ActivatedRoute, private employeService: EmployeeService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.employeService.getEmployeeDetailsById(this.id).subscribe( data => {
      this.employee = data;
    });
  }
}
