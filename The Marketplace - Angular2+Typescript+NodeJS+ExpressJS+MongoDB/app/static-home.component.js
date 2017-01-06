System.register(['angular2/core', 'angular2/router', 'angular2/common', './users', './users.service', './email.validator'], function(exports_1, context_1) {
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
    var core_1, router_1, common_1, users_1, users_service_1, email_validator_1;
    var StaticHomeComponent;
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
            function (users_1_1) {
                users_1 = users_1_1;
            },
            function (users_service_1_1) {
                users_service_1 = users_service_1_1;
            },
            function (email_validator_1_1) {
                email_validator_1 = email_validator_1_1;
            }],
        execute: function() {
            StaticHomeComponent = class StaticHomeComponent {
                constructor(fb, userObj, _userService, router) {
                    this.userObj = userObj;
                    this._userService = _userService;
                    this.router = router;
                    this.status = new core_1.EventEmitter();
                    this.signInForm = fb.group({
                        username: [''],
                        password: [''],
                    });
                    this.signUpForm = fb.group({
                        email: ['', common_1.Validators.compose([common_1.Validators.required,
                                email_validator_1.EmailValidator.validateEmail])],
                        password: [''],
                        confirmPassword: [''],
                        fullname: [''],
                        username: [''],
                        dateOfBirth: ['', common_1.Validators.required],
                        education: [''],
                        selfDescription: ['', common_1.Validators.required],
                        hobbies: ['', common_1.Validators.required]
                    });
                }
                onSubmit() {
                    console.log("Emitting event");
                    this.loggedIn = true;
                    //this.status.emit({newValue : this.loggedIn});
                    this.status._next({ newValue: this.loggedIn });
                    // console.log("Emitting event after");
                    // console.log(this.status);
                }
                routerCanDeactivate() {
                    if (this.signUpForm.dirty) {
                        return confirm("Are you sure you want to leave the page?");
                    }
                    return true;
                }
                ngOnInit() {
                    //     var id = this._routeParams.get('id');
                    //     this._title = id ? "Edit User" : "Add User";
                    //     if (id) {
                    //         this._userService.getUserById(id)
                    //             .subscribe(response => { this.userObj = response }, error => {
                    //                 if (error.status == 404) {
                    //                     this.router.navigate(['NotFound']);
                    //                 }
                    //             });
                    //     }
                }
                onSignUp() {
                    this._userService.registerUser(this.userObj)
                        .subscribe(response => {
                        if (response.errorCode) {
                            // show error message;
                            console.log('error :', response);
                        }
                        else {
                            console.log('Success from Sign Up! Shooting event', response);
                            this.loggedIn = true;
                            this.status._next({ newValue: response });
                        }
                    }, error => { console.log('error !'); });
                }
                onSignIn() {
                    this._userService.validateExistingUser(this.userObj)
                        .subscribe(response => {
                        if (response.errorCode) {
                            console.log('error :', response);
                        }
                        else {
                            console.log('Success from Sign In ! Shooting event', response);
                            this.loggedIn = true;
                            this.status._next({ newValue: response });
                        }
                    }, error => { console.log('error !'); });
                }
            };
            __decorate([
                core_1.Input(), 
                __metadata('design:type', Object)
            ], StaticHomeComponent.prototype, "loggedIn", void 0);
            __decorate([
                core_1.Output(), 
                __metadata('design:type', Object)
            ], StaticHomeComponent.prototype, "status", void 0);
            StaticHomeComponent = __decorate([
                core_1.Component({
                    selector: 'static-home',
                    templateUrl: 'app/static-home.component.html',
                    styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
                        'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
                    directives: [router_1.ROUTER_DIRECTIVES, router_1.RouterOutlet],
                    providers: [router_1.ROUTER_PROVIDERS, users_1.Users, users_service_1.UsersService]
                }), 
                __metadata('design:paramtypes', [common_1.FormBuilder, users_1.Users, users_service_1.UsersService, router_1.Router])
            ], StaticHomeComponent);
            exports_1("StaticHomeComponent", StaticHomeComponent);
        }
    }
});
//# sourceMappingURL=static-home.component.js.map