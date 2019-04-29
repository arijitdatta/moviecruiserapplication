import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';
import { MovieService } from './movie.service';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatSnackBarModule} from '@angular/material/snack-bar';


import { ContainerComponent } from './components/container/container.component';
import { MovieRouterModule } from './movie-router.module';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbContainerComponent } from './components/tmdb-container/tmdb-container.component';
import { MovieDialogComponent } from './components/movie-dialog/movie-dialog.component';
import {MatDialogModule} from '@angular/material/dialog';
import {MatInputModule} from '@angular/material/input';
import {FormsModule} from '@angular/forms';
import { SearchComponent } from './components/search/search.component';


@NgModule({
  imports: [
    MatSnackBarModule,
    MatButtonModule,
    MatCardModule,  
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
  ],
  declarations: [ ThumbnailComponent, ContainerComponent, WatchlistComponent, TmdbContainerComponent, MovieDialogComponent, SearchComponent],
  entryComponents:[MovieDialogComponent],
  exports: [ ThumbnailComponent, ContainerComponent,MovieRouterModule,MovieDialogComponent,SearchComponent],
  providers: [MovieService]
})
export class MovieModule { }
