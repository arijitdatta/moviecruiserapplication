import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {MovieModule} from './modules/movie/movie.module';
import { AppComponent } from './app.component';

const appRoutes:Routes=[
  {
    path:'',
    redirectTo: 'movies',
    pathMatch: 'full',
  }
]



@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    MovieModule,
    BrowserModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
