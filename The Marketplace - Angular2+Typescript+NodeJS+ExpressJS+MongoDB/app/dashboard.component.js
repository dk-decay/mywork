System.register(['angular2/core', './user-search.component', './footer.component', 'angular2/router', './users.service'], function(exports_1, context_1) {
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
    var core_1, user_search_component_1, footer_component_1, router_1, users_service_1;
    var DashboardComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (user_search_component_1_1) {
                user_search_component_1 = user_search_component_1_1;
            },
            function (footer_component_1_1) {
                footer_component_1 = footer_component_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (users_service_1_1) {
                users_service_1 = users_service_1_1;
            }],
        execute: function() {
            DashboardComponent = class DashboardComponent {
                constructor(_routeParams, _userService) {
                    this._routeParams = _routeParams;
                    this._userService = _userService;
                    this.obj = [];
                }
                ngOnInit() {
                    this.username = this._routeParams.get('username');
                    console.log('Init : Dashboard : ', this.username);
                    this._userService.populateUserDetails(this.username)
                        .subscribe(response => {
                        this._userObj = response;
                        console.log('user details found :', this._userObj);
                    }, error => { console.log('error !'); });
                    this._userService
                        .populateItemsSold(this.username)
                        .subscribe(response => {
                        for (var i in response) {
                            var res = response[i];
                            this._userService.findHighestBid(res._id)
                                .subscribe(newResponse => {
                                response[i].highestAmount = newResponse.highestAmount;
                                this.obj.push(response[i]);
                            }).unsubscribe();
                        }
                        this.obj = response;
                    });
                }
                refreshBid(u) {
                    this._userService.findHighestBid(u._id)
                        .subscribe(newResponse => {
                        console.log('response received for highest bid', newResponse.highestBid);
                        u.highestAmount = newResponse.highestAmount;
                    });
                }
            };
            DashboardComponent = __decorate([
                core_1.Component({
                    selector: 'dashboard',
                    templateUrl: 'app/dashboard.component.html',
                    // styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
                    //     'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
                    directives: [user_search_component_1.UserSearchComponent, footer_component_1.FooterComponent],
                    providers: [users_service_1.UsersService]
                }), 
                __metadata('design:paramtypes', [router_1.RouteParams, users_service_1.UsersService])
            ], DashboardComponent);
            exports_1("DashboardComponent", DashboardComponent);
        }
    }
});
//# sourceMappingURL=dashboard.component.js.map