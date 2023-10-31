import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GlobalErrorComponent } from './global-error/global-error.component';

const routes: Routes = [
  { path: 'error', component: GlobalErrorComponent, pathMatch: 'full' },
  {
    path: 'ems',
    loadChildren: () => import('./employee/employee.module').then(m => m.EmployeeModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],                                                                                                                                                                                                                                                                                                          
  exports: [RouterModule]
})
export class AppRoutingModule { }