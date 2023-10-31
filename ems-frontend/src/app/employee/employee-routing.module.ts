import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { EmployeeDetailsComponent } from './employee-details/employee-details.component';

const routes: Routes = [
  {path: '', component: EmployeeListComponent},
  {path: 'employees', component: EmployeeListComponent},
  {path: 'create-employee', component: AddEmployeeComponent},
  {path: 'update-employee/:id', component: UpdateEmployeeComponent},
  {path: 'employee-details/:id', component: EmployeeDetailsComponent}
   
];

@NgModule({       
  imports: [RouterModule.forChild(routes)],                                                                                                                                                                                                                                                                                                     
  exports: [RouterModule]
})
export class EmployeeRoutingModule { }