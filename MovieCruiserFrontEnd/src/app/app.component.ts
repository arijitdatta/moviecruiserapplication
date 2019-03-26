import { Component } from '@angular/core';
import {TopRatedComponent} from './modules/movie/components/top-rated/top-rated.component'

@Component({
  selector: 'app-root',
  template: `
     <movie-top-rated></movie-top-rated> 
     `,
  styles: []
})
export class AppComponent {
  title = 'app';
}
