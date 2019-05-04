import { Injectable } from '@angular/core';
import {AuthenticationService} from './modules/authentication/authentication.service';
import {Router } from '@angular/router';
import {CanActivate} from '@angular/router/src/interfaces';

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class AuthguardService implements CanActivate {

 constructor(private route:Router, private authService:AuthenticationService){

 }

  canActivate() {
    if(!this.authService.isTokenExpired()){
      console.log('in canActivate');
      return true;
    }
    this.route.navigate(['/user/login']);
    return false;
  }

  
}
