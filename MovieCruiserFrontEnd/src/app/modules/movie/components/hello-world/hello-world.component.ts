import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'movie-hello-world',
  template: `
    <p>
      hello-world works!
    </p>
  `,
  styles: []
})
export class HelloWorldComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
