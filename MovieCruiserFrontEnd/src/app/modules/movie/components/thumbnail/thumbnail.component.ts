import { Component, OnInit, Input } from '@angular/core';
import{HttpClient} from '@angular/common/http';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';
import {MatSnackBar} from '@angular/material/snack-bar';


@Component({
  selector: 'movie-thumbnail',
  templateUrl: `./thumbnail.component.html`,
  styleUrls: [
    './thumbnail.component.css'
  ]
})
export class ThumbnailComponent implements OnInit {
  @Input()
  movie: Movie;
  
  constructor(private movieService:MovieService, private snackBar:MatSnackBar) { 
    
  }

  ngOnInit() {
 
  }
  addToWatchlist(){
    console.log("Testing2");
    console.log(this.movie.title);
    console.log(this.movie.release_date);
    this.movieService.addMovieToWatchlist(this.movie).subscribe((movie)=>{
      this.snackBar.open('Movie added to watchlist.', '', {
        duration:2000
      } );
    } );
  }
}
