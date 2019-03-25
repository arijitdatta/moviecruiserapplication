import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HelloWorldComponent } from './components/hello-world/hello-world.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule
  ],
  declarations: [HelloWorldComponent, ThumbnailComponent],
  exports: [HelloWorldComponent, ThumbnailComponent]
})
export class MovieModule { }
