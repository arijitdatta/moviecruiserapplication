import { Component, OnInit, Input } from '@angular/core';
import{HttpClient} from '@angular/common/http';
import {Movie} from '../../movie';
import {MovieService} from '../../movie.service';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: `./thumbnail.component.html`,
  styles: []
})
export class ThumbnailComponent implements OnInit {
  @Input()
  movie: Movie;
  
  constructor(private movieService:MovieService) { 
    
  }

  ngOnInit() {
 
  }
}
