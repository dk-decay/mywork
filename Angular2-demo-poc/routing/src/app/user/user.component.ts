import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { Subscription } from 'rxjs/Rx';
@Component({
  selector: 'app-user',
  template: `
    <p>
      user Works!
    </p>
    <h1>{{id}}</h1>

    <h1>{{analyticsParam}}</h1>

    <router-outlet></router-outlet>
     <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <div id="section1">This is section 1</div>

       <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>
    <br/>

    <h1>THis is the bottom of the page</h1>

  `,
  styles: []
})
export class UserComponent implements OnInit, OnDestroy {
  id: string
  analyticsParam: string
  subscription: Subscription
  queryParamsSubscription: Subscription
  constructor(private activatedRoute: ActivatedRoute) {
    this.subscription = activatedRoute.params.subscribe((data: any) => {
      this.id = data['id']
    })

    this.queryParamsSubscription = activatedRoute.queryParams.subscribe((data: any) => {
      this.analyticsParam = data['analytics']
    })
  }



  ngOnInit() {
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
    this.queryParamsSubscription.unsubscribe();
  }

}
