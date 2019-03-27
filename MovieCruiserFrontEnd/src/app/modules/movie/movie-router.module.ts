import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { ContainerComponent } from './components/container/container.component';


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
            component:ContainerComponent,
            data:{
                movieType:'popular'
            }
        },
        {
            path: 'top_rated',
            component: ContainerComponent,
            data:{
                movieType:'top_rated'
            }
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