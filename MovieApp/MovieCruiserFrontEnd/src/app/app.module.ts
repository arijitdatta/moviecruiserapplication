import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {FormsModule} from '@angular/forms';

import {MovieModule} from './modules/movie/movie.module';
import {AuthenticationModule} from './modules/authentication/authentication.module';
import { AppComponent } from './app.component';
import {AuthguardService} from './authguard.service';


const appRoutes:Routes=[
  //{
  //  path:'',
  //  redirectTo: 'movies',
  //  pathMatch: 'full',
  // }
  {
    path:'',
    redirectTo: '/user/login',
    pathMatch: 'full',
  }
]



@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    FormsModule,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    MovieModule,
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    AuthenticationModule,
  ],
  providers: [AuthguardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
