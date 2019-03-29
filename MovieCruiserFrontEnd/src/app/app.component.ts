import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: ` 
  <mat-toolbar color="primary">
  <span>Arijit's Movie Cruiser Application</span>
  <button mat-button [routerLink]="['/movies/popular']">Popular Movies</button>
  <button mat-button [routerLink]="['/movies/top_rated']">Top Rated Movies</button>
  </mat-toolbar>
     <router-outlet></router-outlet> 
     `,
  styles: []
})
export class AppComponent {
  title = 'app';
}
