import { Component } from 'angular2/core';
import { ROUTER_DIRECTIVES, RouteConfig , RouterOutlet} from 'angular2/router';

@Component({
    selector : 'user-body',
    template : `
<h1>Welcome to the MarketHub</h1>   


        <ul class="nav nav-tabs">
            <li class="active"><a [routerLink]="['Buy']">Buy</a></li>
            <li class="active"><a [routerLink]="['Sell']">Sell</a></li>
        </ul>`,

    directives:[ROUTER_DIRECTIVES]
})

export class UserBodyComponent{

}