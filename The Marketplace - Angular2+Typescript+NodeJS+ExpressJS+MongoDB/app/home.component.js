System.register(['angular2/core', 'angular2/router', 'angular2/common', './user', 'angular2/http', './login.service', 'rxjs/add/operator/map'], function(exports_1, context_1) {
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
    var core_1, router_1, common_1, user_1, http_1, login_service_1;
    var HomeComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (user_1_1) {
                user_1 = user_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (login_service_1_1) {
                login_service_1 = login_service_1_1;
            },
            function (_1) {}],
        execute: function() {
            //import {Ng2PageScrollModule} from 'ng2-page-scroll';
            HomeComponent = class HomeComponent {
                constructor(fb, userObj, router, _routeParams, _loginService) {
                    this.userObj = userObj;
                    this.router = router;
                    this._routeParams = _routeParams;
                    this._loginService = _loginService;
                    this.authenticatedUser = {};
                    this.signUpForm = fb.group({
                        email: ['', common_1.Validators.compose([common_1.Validators.required])],
                        password: ['', common_1.Validators.compose([common_1.Validators.required])]
                    });
                }
                ngOnInit() {
                    this.loggedIn = this.route;
                }
                signIn() {
                    // this.userObj = {email : 'soham@gmail.com', password : 'ads'};
                    // console.log("user object:", this.userObj);
                    // this._loginService.loginuser(this.userObj)
                    // .subscribe(res => {
                    //     this.authenticatedUser = res;
                    // },
                    // err => console.log('error : ',err));
                    // console.log('Printing authenticatedUser ');
                    // console.log(this.authenticatedUser);
                    console.log('Checking value');
                    this.router.navigate(['UserHome']);
                }
            };
            HomeComponent = __decorate([
                core_1.Component({
                    selector: 'home',
                    templateUrl: 'app/home.component.html',
                    //  styleUrls : ['app/home/css/bootstrap.min.css','app/home/css/bootstrap.css', 'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
                    providers: [user_1.User, http_1.HTTP_PROVIDERS, login_service_1.LoginService],
                }), 
                __metadata('design:paramtypes', [common_1.FormBuilder, user_1.User, router_1.Router, router_1.RouteParams, login_service_1.LoginService])
            ], HomeComponent);
            exports_1("HomeComponent", HomeComponent);
        }
    }
});
//# sourceMappingURL=home.component.js.map