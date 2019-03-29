import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';
import { MovieService } from './movie.service';
import {MatCardModule} from '@angular/material/card';


import { ContainerComponent } from './components/container/container.component';
import {MovieRouterModule } from './movie-router.module';

@NgModule({
  imports: [
    MatCardModule,  
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
  ],
  declarations: [ ThumbnailComponent, ContainerComponent],
  exports: [ ThumbnailComponent, ContainerComponent,MovieRouterModule],
  providers: [MovieService]
})
export class MovieModule { }
