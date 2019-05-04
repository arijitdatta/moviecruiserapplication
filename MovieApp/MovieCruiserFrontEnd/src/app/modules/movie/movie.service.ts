import { Injectable } from '@angular/core';
import{HttpClient} from '@angular/common/http';

import {Movie} from './movie';

import {retry} from 'rxjs/operators';
import { Observable } from 'rxjs';
import { map } from 'rxjs/internal/operators/map';

@Injectable()
export class MovieService {

  tmdbEndpoint: string;
  imgPrefix: string;
  apiKey:string;
  watchlistEndpoint: string;
  springEndpoint: string;
  tmdbSearchEndpoint: string;

  constructor(private http:HttpClient) { 
    this.apiKey='api_key=e3df5c4aa71828545d1d14c5a1f1868d'
    this.tmdbEndpoint='https://api.themoviedb.org/3/movie';
    this.imgPrefix='https://image.tmdb.org/t/p/w500';
    //local json server testing this.watchlistEndpoint='http://localhost:3000/watchlist';
    this.springEndpoint='http://localhost:8082/api/v1/movieservice';
    this.tmdbSearchEndpoint='https://api.themoviedb.org/3/search/movie?';


  }

  

  getMovies(type:string, page: number=1 ): Observable<Array<Movie>>{
    const moviesEndPoint=`${this.tmdbEndpoint}/${type}?${this.apiKey}&page=${page}`;
    return this.http.get(moviesEndPoint).pipe(
      retry(3),
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this))
    );
   
    
  }

  getPopularMovies(page: number=1): Observable<Array<Movie>>{
    const popularMoviesEndPoint=`${this.tmdbEndpoint}/popular?${this.apiKey}&page=${page}`;
    return this.http.get(popularMoviesEndPoint).pipe(
      retry(3),
      map(this.pickMovieResults),
      map(this.transformPosterPath.bind(this))
    );
   
    
  }

  getTopRatedMovies(page: number=1): Observable<Array<Movie>>{
    const topRatedMoviesEndPoint=`${this.tmdbEndpoint}/top_rated?${this.apiKey}&page=${page}`;
    return this.http.get(topRatedMoviesEndPoint).pipe(
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
    return response['results'];
  }

  addMovieToWatchlist(movie){
   // local json server testing -  return this.http.post(this.watchlistEndpoint, movie);
   // spring actual backend 
   return this.http.post(this.springEndpoint, movie);

  }

  deleteMovieFromWatchlist(movie:Movie){
    const url=`${this.springEndpoint}/${movie.id}`;
    return this.http.delete(url, {responseType:'text'});
  }

  getWatchlistedMovies(): Observable<Array<Movie>>{
    // local jison server testing - return this.http.get<Array<Movie>>(this.watchlistEndpoint);
    // spring actual backend
    return this.http.get<Array<Movie>>(this.springEndpoint);
  }

  updateWatchlistedItem(movie){
    const url=`${this.springEndpoint}/${movie.id}`;
    return this.http.put(url, movie);
  }

  searchMovies(searchKey:string): Observable<Array<Movie>>{
    if(searchKey.length>0){
      const url=`${this.tmdbSearchEndpoint}${this.apiKey}&language=en-US&page=1&include_adult=false&query=${searchKey}`;
      return this.http.get(url).pipe(
        retry(3),
        map(this.pickMovieResults),
        map(this.transformPosterPath.bind(this)));
  }
  }

}
