import { Component, OnInit } from '@angular/core';
import{HttpClient} from '@angular/common/http';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: `./thumbnail.component.html`,
  styles: []
})
export class ThumbnailComponent implements OnInit {
  movies: Array<Movie>;
  
  constructor(private movieService:MovieService) { 
    this.movies=[];
  }

  ngOnInit() {
    this.movieService.getPopularMovies().subscribe((movies)=> {
      this.movies.push(...movies);
    });
  }
}
