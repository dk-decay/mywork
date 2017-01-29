import { Component, OnInit, OnDestroy } from '@angular/core';

import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'Rxjs/rx';

@Component({
  selector: 'app-home-component',
  template: `
    <p>
      home-component Works!
    </p>
    {{analytics}}
    <hr>
    <div id="section1">Section1</div>
  `,
  styles: []
})
export class HomeComponentComponent implements OnInit, OnDestroy {
  subscription: Subscription;
  analytics: string;
  constructor(private activatedRoute: ActivatedRoute) {
    this.subscription = activatedRoute.queryParams.subscribe((queryParams: any) =>
      this.analytics = queryParams['analytics']);
  }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

}
