import { Component, OnInit , Input} from 'angular2/core';
import { RouterLink, Router, RouteParams } from 'angular2/router';
import { FormBuilder, Control, ControlGroup, Validators } from 'angular2/common';
import { User } from './user';
import {HTTP_PROVIDERS} from 'angular2/http';
import {LoginService} from './login.service';
import { Http, Headers, RequestOptions } from 'angular2/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
//import {Ng2PageScrollModule} from 'ng2-page-scroll';

@Component({
    selector: 'home',
    templateUrl: 'app/home.component.html',
        //  styleUrls : ['app/home/css/bootstrap.min.css','app/home/css/bootstrap.css', 'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
    providers: [User, HTTP_PROVIDERS, LoginService],
  //  directives : [Ng2PageScrollModule]
})
export class HomeComponent implements OnInit {
authenticatedUser = {};
    loggedIn;
    signUpForm: ControlGroup;
    constructor(fb: FormBuilder, private userObj: User,
        private router: Router,
        private _routeParams: RouteParams,
        private _loginService : LoginService) {


        this.signUpForm = fb.group({
            email: ['', Validators.compose([Validators.required])],
            password: ['', Validators.compose([Validators.required])]
        });
    }
    ngOnInit() {
this.loggedIn = this.route

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

}