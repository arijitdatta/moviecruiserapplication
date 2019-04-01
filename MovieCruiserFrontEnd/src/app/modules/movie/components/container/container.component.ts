import { Component, OnInit, Input } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';
import {ActivatedRoute} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  
  @Input()
  movies:Array<Movie>;

  @Input()
  useWatchlistApi:boolean;


  constructor(private movieService:MovieService, private snackBar:MatSnackBar) { 
   
  }

  ngOnInit() {
  }

  addToWatchlist(movie){
   
   this.movieService.addMovieToWatchlist(movie).subscribe((movie)=>{
     this.snackBar.open('Movie added to watchlist.', '', {
        duration:2000
      } );
    } );
  }

  deleteFromWatchlist(movie){

    for(var i=0; i<this.movies.length;i++){
      if(this.movies[i].title===movie.title){
        this.movies.splice(i,1);
      }
    }
   
    this.movieService.deleteMovieFromWatchlist(movie).subscribe((movie)=>{
      this.snackBar.open('Movie removed from watchlist.', '', {
         duration:2000
       } );
     } );
   }
  
  

}
