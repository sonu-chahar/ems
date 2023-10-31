import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs/internal/Subject';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {
  private errorMessage$ = new Subject<any>();
  private errorMessage: any;

  public getErrorMessage(){
    return this.errorMessage$.asObservable();
  }


  constructor(private router: Router) { }

  public handleError = (error: HttpErrorResponse) => {
    if (error.status === 500) {
      this.handle500Error(error);
    }
    else if (error.status === 404) {
      this.handle404Error(error)
    }
    else {
      this.handleOtherError(error);
    }
  }

  private handle500Error = (error: HttpErrorResponse) => {
    this.createErrorMessage(error);
    this.router.navigate(['/error']);
  }

  private handle404Error = (error: HttpErrorResponse) => {
    this.createErrorMessage(error);
    this.router.navigate(['/error']);
  }
  private handleOtherError = (error: HttpErrorResponse) => {
    this.createErrorMessage(error); //TODO: this will be fixed later; 
    this.router.navigate(['/error']);
  }

  private createErrorMessage = (error: HttpErrorResponse) => {

    this.errorMessage = error.error ? error.error : error.statusText;

    this.errorMessage$.next(this.errorMessage);

  }
}