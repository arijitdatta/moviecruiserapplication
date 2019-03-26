import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HelloWorldComponent } from './components/hello-world/hello-world.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';
import { MovieService } from './movie.service';
import { PopularComponent } from './components/popular/popular.component';
import { TopRatedComponent } from './components/top-rated/top-rated.component';
import { ContainerComponent } from './components/container/container.component';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule
  ],
  declarations: [HelloWorldComponent, ThumbnailComponent, PopularComponent, TopRatedComponent, ContainerComponent],
  exports: [HelloWorldComponent, ThumbnailComponent, PopularComponent, TopRatedComponent,ContainerComponent],
  providers: [MovieService]
})
export class MovieModule { }
