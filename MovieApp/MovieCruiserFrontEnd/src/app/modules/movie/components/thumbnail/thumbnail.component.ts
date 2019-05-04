import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import{HttpClient} from '@angular/common/http';
import {Movie} from '../../movie';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { MovieDialogComponent } from '../movie-dialog/movie-dialog.component';


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

  @Input()
  useWatchlistApi:boolean;

  @Output()
  deleteMovie=new EventEmitter();

  @Output()
  addMovie=new EventEmitter();

  
  constructor(private snackBar:MatSnackBar, public dialog:MatDialog) { 
    
  }

  ngOnInit() {
 
  }
  addToWatchlist(){
    
    this.addMovie.emit(this.movie);
  }

  deleteFromWatchlist(){
    this.deleteMovie.emit(this.movie);
  }

  updateWatchlistedItem(actionType){
    let dialogRef=this.dialog.open(MovieDialogComponent, {
      width: '400px',
      data:{obj:this.movie, actionType:actionType}      
    });
    dialogRef.afterClosed().subscribe(result=>{

    });
  }
}
