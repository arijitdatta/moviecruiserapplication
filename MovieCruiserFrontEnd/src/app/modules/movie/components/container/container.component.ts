import { Component, OnInit, Input } from '@angular/core';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})
export class ContainerComponent implements OnInit {
  
  movies:Array<Movie>;
  movieType: string;
  constructor(private movieService:MovieService, private route:ActivatedRoute) { 
    this.movies=[];
    this.route.data.subscribe((data)=>{
      this.movieType=data.movieType;
    });
  }

  ngOnInit() {
   
    this.movieService.getMovies(this.movieType).subscribe((movies)=> {
      this.movies.push(...movies);
    });
  }

}
