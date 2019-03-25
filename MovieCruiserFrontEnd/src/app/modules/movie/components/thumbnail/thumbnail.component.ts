import { Component, OnInit } from '@angular/core';
import{HttpClient} from '@angular/common/http';
import {Movie} from '../../movie';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: `./thumbnail.component.html`,
  styles: []
})
export class ThumbnailComponent implements OnInit {
  movies: Array<Movie>;
  tmdbEndpoint: string;
  imgPrefix: string;
  constructor(private http:HttpClient) { 
    this.movies=[];
    this.tmdbEndpoint='https://api.themoviedb.org/3/movie/popular?api_key=e3df5c4aa71828545d1d14c5a1f1868d&page=1';
    this.imgPrefix='https://image.tmdb.org/t/p/w500';

  }

  ngOnInit() {
    this.http.get(this.tmdbEndpoint).subscribe(
      (res)=>{
      
      const movies=res['results'].map(movie=>
        {
          movie.poster_path=`${this.imgPrefix}${movie.poster_path}`;
          return movie;
        });
      this.movies.push(...movies)
    });
  }

}
