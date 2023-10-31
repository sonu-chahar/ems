import { HttpInterceptor, HttpEvent, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable ({
  providedIn: 'root'
})
export class AuthHeaderInterceptor  implements HttpInterceptor {

    intercept (req: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
      console.log('Intercepted');
      
      return next.handle (req);
  }
}

