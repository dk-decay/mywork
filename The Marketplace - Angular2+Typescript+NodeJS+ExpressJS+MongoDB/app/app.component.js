/// <reference path="../typings/tsd.d.ts" />
System.register(['angular2/core', 'angular2/router', './buy.component', './sell.component', './userhome.component', './dashboard.component', './rent.component', './user-search.component', './footer.component', './static-home.component', './users', './users.service'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, buy_component_1, sell_component_1, userhome_component_1, dashboard_component_1, rent_component_1, user_search_component_1, footer_component_1, static_home_component_1, users_1, users_service_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (buy_component_1_1) {
                buy_component_1 = buy_component_1_1;
            },
            function (sell_component_1_1) {
                sell_component_1 = sell_component_1_1;
            },
            function (userhome_component_1_1) {
                userhome_component_1 = userhome_component_1_1;
            },
            function (dashboard_component_1_1) {
                dashboard_component_1 = dashboard_component_1_1;
            },
            function (rent_component_1_1) {
                rent_component_1 = rent_component_1_1;
            },
            function (user_search_component_1_1) {
                user_search_component_1 = user_search_component_1_1;
            },
            function (footer_component_1_1) {
                footer_component_1 = footer_component_1_1;
            },
            function (static_home_component_1_1) {
                static_home_component_1 = static_home_component_1_1;
            },
            function (users_1_1) {
                users_1 = users_1_1;
            },
            function (users_service_1_1) {
                users_service_1 = users_service_1_1;
            }],
        execute: function() {
            AppComponent = class AppComponent {
                constructor(router, 
                    //  _routeParams: RouteParams,
                    userObj, _userService) {
                    this.router = router;
                    this.userObj = userObj;
                    this._userService = _userService;
                    this.bidNow = false;
                    this.loggedIn = false;
                }
                onStatus($event) {
                    console.log("catching event", $event);
                    this.loggedIn = true;
                    this.username = $event.newValue.username;
                    this.router.navigate(['Dashboard', { username: JSON.stringify($event.newValue.username) }]);
                }
                onBidNowModal($event) {
                    console.log('app component');
                    this.bidNow = true;
                }
            };
            AppComponent = __decorate([
                router_1.RouteConfig([
                    { path: "/userhome", name: 'UserHome', component: userhome_component_1.UserHomeComponent },
                    { path: "/userhome/buy", name: 'Buy', component: buy_component_1.BuyComponent },
                    { path: "/userhome/sell", name: 'Sell', component: sell_component_1.SellComponent },
                    { path: "/userhome/dashboard", name: 'Dashboard', component: dashboard_component_1.DashboardComponent },
                    { path: "/userhome/rent", name: 'Rent', component: rent_component_1.RentComponent },
                    { path: "/marketplace", name: 'Marketplace', component: static_home_component_1.StaticHomeComponent }
                ]),
                core_1.Component({
                    selector: 'my-app',
                    templateUrl: 'app/app.component.html',
                    styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
                        'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
                    directives: [router_1.ROUTER_DIRECTIVES, router_1.RouterOutlet, userhome_component_1.UserHomeComponent, user_search_component_1.UserSearchComponent, static_home_component_1.StaticHomeComponent, footer_component_1.FooterComponent, buy_component_1.BuyComponent],
                    providers: [router_1.ROUTER_PROVIDERS, users_1.Users, users_service_1.UsersService]
                }), 
                __metadata('design:paramtypes', [router_1.Router, users_1.Users, users_service_1.UsersService])
            ], AppComponent);
            exports_1("AppComponent", AppComponent);
        }
    }
});
//# sourceMappingURL=app.component.js.map