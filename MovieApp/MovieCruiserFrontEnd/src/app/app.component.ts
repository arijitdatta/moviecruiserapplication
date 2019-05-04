import { Component } from '@angular/core';
import {AuthenticationService} from './modules/authentication/authentication.service';
import {Router } from '@angular/router';

@Component({
  selector: 'app-root',
  template: ` 
  <mat-toolbar color="primary">
  <span>Arijit's Movie Cruiser Application</span>
  <button mat-button [routerLink]="['/movies/popular']">Popular Movies</button>
  <button mat-button [routerLink]="['/movies/top_rated']">Top Rated Movies</button>
  <button mat-button [routerLink]="['/movies/watchlist']">My Watchlist</button>
  <button mat-button [routerLink]="['/movies/search']">Search</button>
  <!-- <button mat-button [routerLink]="['/user/login']">User Login</button>
  <button mat-button [routerLink]="['/user/register']">User Register</button> -->
  <button mat-button (click)="logout()">Logout</button>
  </mat-toolbar>
     <router-outlet></router-outlet> 
     `,
  styles: []
})
export class AppComponent {
  title = 'app';
  constructor(private auth:AuthenticationService, private routes:Router){

  }
 logout(){
   this.auth.deleteToken();
   this.routes.navigate(['/user/login']);
 }


}
