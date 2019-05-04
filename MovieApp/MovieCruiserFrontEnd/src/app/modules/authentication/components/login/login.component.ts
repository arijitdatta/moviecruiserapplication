import { Component, OnInit } from '@angular/core';
import {User} from '../../user';
import {AuthenticationService} from '../../authentication.service';
import {Router} from '@angular/router';

@Component({
  selector: 'authentication-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  newUser: User;
  constructor(private authService:AuthenticationService, private router:Router) { 
    this.newUser=new User();
  }

  ngOnInit() {
  }

  loginUser(){
    this.authService.loginUser(this.newUser).subscribe(data=>{
      if(data['token']){
        this.authService.setToken(data['token']);
        this.router.navigate(['/movies/popular']);
      }
    })

  }

}
