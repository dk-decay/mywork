import { Component, Input, Output, EventEmitter, OnInit } from 'angular2/core';
import { ROUTER_DIRECTIVES, RouteConfig, RouterOutlet, ROUTER_PROVIDERS, RouterLink, Router, RouteParams, CanDeactivate } from 'angular2/router';
import { ControlGroup, FormBuilder, Validators } from 'angular2/common';
import { Users } from './users';
import { UsersService } from './users.service';
import { EmailValidator } from './email.validator';
import { UsernameValidator } from './username.validator';

@Component({

    selector: 'static-home',
    templateUrl: 'app/static-home.component.html',
    styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
        'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
    directives: [ROUTER_DIRECTIVES, RouterOutlet],
    providers: [ROUTER_PROVIDERS, Users, UsersService]


})

export class StaticHomeComponent implements CanDeactivate, OnInit {
    @Input() loggedIn;

    @Output() status = new EventEmitter();



    onSubmit() {
        console.log("Emitting event");
        this.loggedIn = true;
        //this.status.emit({newValue : this.loggedIn});
        this.status._next({ newValue: this.loggedIn });
        // console.log("Emitting event after");
        // console.log(this.status);

    }


    signInForm: ControlGroup;
    signUpForm: ControlGroup;

    constructor(fb: FormBuilder, private userObj: Users,
        private _userService: UsersService,
        private router: Router,
    ) {

        this.signInForm = fb.group({
            username: [''],
            password: [''],


        })

        this.signUpForm = fb.group({


            email: ['',Validators.compose([Validators.required,
                        EmailValidator.validateEmail])],
            password: [''],
            confirmPassword: [''],
            fullname: [''],
            username: [''],
            dateOfBirth: ['', Validators.required],
            education: [''],
            selfDescription: ['', Validators.required],
            hobbies: ['', Validators.required]





        //     email: ['',Validators.compose([Validators.required,
        //                  EmailValidator.validateEmail])],
        //     password: ['', Validators.required],
        //     confirmPassword: [''],
        //     fullname: ['', Validators.required],
        //     username: ['',Validators.compose([
        //        Validators.required,
        //        UsernameValidator.cannotContainSpace
        //    ])
            
        //     ],
        //     dateOfBirth: ['', Validators.required],
        //     education: [''],
        //     selfDescription: ['', Validators.required],
        //     hobbies: ['',Validators.required]

        });



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
                } else {
                    console.log('Success from Sign Up! Shooting event', response);
                    this.loggedIn = true;
                    this.status._next({ newValue: response });
                    //   this.router.parent.navigate();
                }
            },
            error => { console.log('error !') });

    }

    onSignIn() {
        this._userService.validateExistingUser(this.userObj)
            .subscribe(response => {
                if (response.errorCode) {
                    console.log('error :', response);
                } else {
                    console.log('Success from Sign In ! Shooting event', response);
                    this.loggedIn = true;
                    this.status._next({ newValue: response });
                }
            },
            error => { console.log('error !') });
    }
}