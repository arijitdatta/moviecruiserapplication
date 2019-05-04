import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';

import * as jwt_decode from 'jwt-decode';
import { HttpClient } from '@angular/common/http';
export const TOKEN_NAME:string='jwt_token'

@Injectable({
  providedIn: 'root'
})

@Injectable()
export class AuthenticationService {
  springEndPoint: string;
  token: string;


  constructor(private httpClient:HttpClient) {
    this.springEndPoint='http://localhost:8089/api/v1/userservice';
   }

   registerUser(newuser){
     const url=this.springEndPoint+"/register";
     console.log('newuser', newuser);
     return this.httpClient.post(url,newuser,{responseType: 'text'});
   }

   loginUser(newuser){
    const url=this.springEndPoint+"/login";
    return this.httpClient.post(url,newuser);
   }

   setToken(token:string){
     return localStorage.setItem(TOKEN_NAME, token);
   }

   getToken(token:string){
    return localStorage.gettem(TOKEN_NAME);
  }

  deleteToken(token:string){
    return localStorage.deleteItem(TOKEN_NAME);
  }
}
