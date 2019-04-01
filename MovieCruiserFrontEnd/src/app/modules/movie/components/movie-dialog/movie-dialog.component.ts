import { Component, OnInit, Input,Inject } from '@angular/core';
import {Movie} from '../../movie';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {MovieService} from '../../movie.service';

@Component({
  selector: 'movie-movie-dialog',
  templateUrl: `./movie-dialog.component.html`,
  styles: []
})
export class MovieDialogComponent implements OnInit {

  
  movie: Movie;
  comments:string;
  actionType:string;

  constructor(private snackBar:MatSnackBar, private dialogRef:MatDialogRef<MovieDialogComponent>, @Inject(MAT_DIALOG_DATA) public data:any, private movieService:MovieService) {
    this.movie=data.obj;
    this.comments=data.obj.overview;
    this.actionType=data.actionType;
   }

  ngOnInit() {
  }

  obNoClick(){
    this.dialogRef.close();
  }
  updateComments(){
    console.log("Comment 1 ", this.comments);
    this.movie.overview=this.comments;
    console.log("Overview 2 ", this.movie.overview);
    this.dialogRef.close();
    this.movieService.updateWatchlistedItem(this.movie).subscribe((movie)=>{
      this.snackBar.open("Movie comments updated", "", {duration:2000,})
    });
  }

}
