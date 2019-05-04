import { Injectable } from '@angular/core';
import {AuthenticationService} from '../authentication/authentication.service';
import { HttpRequest } from '@angular/common/http';
import { HttpHandler } from '@angular/common/http';

import { HttpEvent } from '@angular/common/http';
import {Observable} from 'rxjs';
import { HttpInterceptor } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class TokenInterceptorService implements HttpInterceptor {

  constructor(private auth:AuthenticationService) { }

  intercept(request: HttpRequest<any>, next:HttpHandler): Observable<HttpEvent<any>>{
    console.log('In intercept');
    console.log(`Bearer ${this.auth.getToken()}`);
   
    
    request=request.clone({
      setHeaders: {
        Authorization: `Bearer ${this.auth.getToken()}`
      }
    });
    return next.handle(request);
  }
}
