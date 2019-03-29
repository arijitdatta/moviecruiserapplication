import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { ContainerComponent } from './components/container/container.component';
import {TmdbContainerComponent} from './components/tmdb-container/tmdb-container.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';


const movieRoutes: Routes=[{
    path: 'movies',
    children: [
        {
            path:'',
            redirectTo:'/movies/popular',
            pathMatch: 'full'
        },
        { 
            path:'popular',
            component:TmdbContainerComponent,
            data:{
                movieType:'popular'
            }
        },
        {
            path: 'top_rated',
            component: TmdbContainerComponent,
            data:{
                movieType:'top_rated'
            }
           
        },
        {
            path:'watchlist',
            component: WatchlistComponent,
            
        }
    ]
}
];

@NgModule({
    imports:[
        RouterModule.forChild(movieRoutes),
    ],
    exports: [
        RouterModule,
    ]
   
  })
  export class MovieRouterModule { }