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
  
  @Input()
  movies:Array<Movie>;

  constructor() { 
   
  }

  ngOnInit() {
  }

}
