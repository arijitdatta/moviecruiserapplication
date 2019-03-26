import { Component, OnInit } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';

@Component({
  selector: 'movie-popular',
  templateUrl: './popular.component.html',
  
})
export class PopularComponent implements OnInit {
  movies:Array<Movie>;
  constructor(private movieService:MovieService) { 
    this.movies=[];
  }

  ngOnInit() {
    this.movieService.getMovies('popular').subscribe((movies)=> {
      this.movies.push(...movies);
    });
  }

}
