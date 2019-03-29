import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';
import { MovieService } from './movie.service';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';


import { ContainerComponent } from './components/container/container.component';
import {MovieRouterModule } from './movie-router.module';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';

@NgModule({
  imports: [
    MatSnackBarModule,
    MatButtonModule,
    MatCardModule,  
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
  ],
  declarations: [ ThumbnailComponent, ContainerComponent, WatchlistComponent, TmdbContainerComponent],
  exports: [ ThumbnailComponent, ContainerComponent,MovieRouterModule],
  providers: [MovieService]
})
export class MovieModule { }
