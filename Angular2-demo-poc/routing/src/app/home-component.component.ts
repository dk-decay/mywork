import { Component, OnInit } from '@angular/core';


import { Router } from '@angular/router';

@Component({
  selector: 'app-home-component',
  template: `
    <p>
      home-component Works!
    </p>
    
    <button (click)="navigate()">Click Me</button>
   
  `,
  styles: []
})
export class HomeComponentComponent implements OnInit {

  constructor(private _router: Router) { }

  ngOnInit() {
  }

  navigate() {
    this._router.navigate(['/user/10'], { queryParams: { 'analytics': '100' }, fragment : 'section1'})
  }

}
