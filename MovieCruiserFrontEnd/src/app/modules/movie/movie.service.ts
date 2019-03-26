import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators/map';
import {Movie} from './movie';
import {Observable} from 'rxjs/Observable';
import {retry} from 'rxjs/operators';

@Injectable()
export class MovieService {

  tmdbEndpoint: string;
  imgPrefix: string;

  constructor(private http:HttpClient) { 
    this.tmdbEndpoint='https://api.themoviedb.org/3/movie/popular?api_key=e3df5c4aa71828545d1d14c5a1f1868d&page=1';
    this.imgPrefix='https://image.tmdb.org/t/p/w500';

  }

  getPopularMovies(): Observable<Array<Movie>>{
    return this.http.get(this.tmdbEndpoint).pipe(
      retry(3),
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this))
    );
   
    
  }

  transformPosterPath(movies): Array<Movie>{
    return movies.map(movie =>{
      movie.poster_path=`${this.imgPrefix}${movie.poster_path}`;
      return movie;
    });
  }

  pickMovieResults(response){
    return response['results';]
  }


}
