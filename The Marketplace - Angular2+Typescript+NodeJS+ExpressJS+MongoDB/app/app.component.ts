/// <reference path="../typings/tsd.d.ts" />

import { Component, Input } from 'angular2/core';
import { ROUTER_DIRECTIVES, RouteConfig, RouterOutlet, ROUTER_PROVIDERS, RouterLink, Router, RouteParams } from 'angular2/router';
import { HomeComponent } from './home.component';
import { UserBodyComponent } from './user-body.component';
import { UserHeaderComponent } from './user-header.component';
import { BuyComponent } from './buy.component';
import { SellComponent } from './sell.component';
import { UserHomeComponent } from './userhome.component';
import { DashboardComponent } from './dashboard.component';
import { RentComponent } from './rent.component';
import { UserSearchComponent } from './user-search.component';
import { FooterComponent } from './footer.component';
import { StaticHomeComponent } from './static-home.component';
import { Users } from './users';
import { UsersService } from './users.service';


@RouteConfig([

    { path: "/userhome", name: 'UserHome', component: UserHomeComponent },
    { path: "/userhome/buy", name: 'Buy', component: BuyComponent },
    { path: "/userhome/sell", name: 'Sell', component: SellComponent },
    { path: "/userhome/dashboard", name: 'Dashboard', component: DashboardComponent },
    { path: "/userhome/rent", name: 'Rent', component: RentComponent },
    { path: "/marketplace", name: 'Marketplace', component: StaticHomeComponent }

])
@Component({
    selector: 'my-app',
    templateUrl: 'app/app.component.html',
    styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
        'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
    directives: [ROUTER_DIRECTIVES, RouterOutlet, UserHomeComponent, UserSearchComponent, StaticHomeComponent, FooterComponent, BuyComponent],
    providers: [ROUTER_PROVIDERS, Users, UsersService]
})


export class AppComponent {
    username;
    bidNow = false;
    loggedIn = false;

    constructor(private router: Router,
        //  _routeParams: RouteParams,
        private userObj: Users,
        private _userService: UsersService, ) {
    }

    onStatus($event) {
        console.log("catching event", $event);
        this.loggedIn = true;
        this.username = $event.newValue.username;
        this.router.navigate(['Dashboard', { username: JSON.stringify($event.newValue.username) }]);
    }

    onBidNowModal($event) {
        console.log('app component')
        this.bidNow = true;
    }

}