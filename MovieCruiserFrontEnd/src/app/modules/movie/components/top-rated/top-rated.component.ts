import { Component, OnInit } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';

@Component({
  selector: 'movie-top-rated',
  templateUrl: './top-rated.component.html',
})
export class TopRatedComponent implements OnInit {
  movies:Array<Movie>;
  constructor(private movieService:MovieService) { 
    this.movies=[];
  }

  ngOnInit() {
    this.movieService.getMovies('top_rated').subscribe((movies)=> {
      this.movies.push(...movies);
    });
  }

}
