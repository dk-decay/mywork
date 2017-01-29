import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';

@Component({
  selector: 'app-user',
  template: `
    <p>
      User Works!
    </p>
    <button (click)="onNavigate()">Go Home</button>
    <hr>
    {{id}}
    <hr>
    <router-outlet></router-outlet>
  `,
  styles: []
})
export class UserComponent implements OnInit, OnDestroy {
  id: string;
  subscription: Subscription;
  constructor(private router: Router, private activateRoute: ActivatedRoute) {
    this.subscription = activateRoute.params.subscribe((params) => this.id = params['id'])
  }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  onNavigate() {
    this.router.navigate(['/'], { queryParams: { 'analytics': 100 }, fragment: 'section1' });
  }

}
