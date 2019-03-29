import { Component, OnInit } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';

@Component({
  selector: 'movie-watchlist',
  template: `
    <movie-container [movies]="movies"></movie-container>
  `,
  styles: []
})
export class WatchlistComponent implements OnInit {

  movies:Array<Movie>;
  constructor(private movieService:MovieService) {
    this.movies=[];
   }

  ngOnInit() {
    this.movieService.getWatchlistedMovies().subscribe((movies)=>{
      this.movies.push(...movies);
    }

    )
  }

}
