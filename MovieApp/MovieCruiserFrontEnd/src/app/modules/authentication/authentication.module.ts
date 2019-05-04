import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';

import { AuthenticationRouterModule } from './authentication-router.module';
import { AuthenticationService } from './authentication.service';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
//import {MatFormField} from '@angular/material/form-field';

@NgModule({
  declarations: [RegisterComponent, LoginComponent],
  imports: [
    CommonModule,
    AuthenticationRouterModule,
    MatButtonModule,
    HttpClientModule,
    MatInputModule,
    FormsModule,
    //MatFormField,


  ],
  exports: [ LoginComponent, RegisterComponent,AuthenticationRouterModule],
  providers: [AuthenticationService]
})
export class AuthenticationModule { }
