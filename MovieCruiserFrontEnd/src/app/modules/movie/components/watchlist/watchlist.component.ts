import { Component, OnInit } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'movie-watchlist',
  template: `
    <movie-container [movies]="movies" [useWatchlistApi]='useWatchlistApi'></movie-container>
  `,
  styles: []
})
export class WatchlistComponent implements OnInit {

  movies:Array<Movie>;
  useWatchlistApi=true;
  constructor(private movieService:MovieService, private matSnackBar:MatSnackBar) {
    this.movies=[];
   }


  ngOnInit() {
    let message='Watch list is empty';
    
    this.movieService.getWatchlistedMovies().subscribe((movies)=>{
      if(movies.length==0){
        this.matSnackBar.open(message, '', {
          duration:1000
        });
      }








      this.movies.push(...movies);
    }

    )
  }

}
