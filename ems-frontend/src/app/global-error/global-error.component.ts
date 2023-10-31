import { Component, OnInit } from '@angular/core';
import { ErrorHandlerService } from '../errorhandler.service';

@Component({
  selector: 'app-global-error',
  templateUrl: './global-error.component.html',
  styleUrls: ['./global-error.component.css']
})
export class GlobalErrorComponent implements OnInit{

  public errorMessage : any;
  constructor(private errorHandlerService:ErrorHandlerService){}

  ngOnInit(): void {
    this.errorHandlerService.getErrorMessage()
    .subscribe(err => this.errorMessage = err)
  }


}
