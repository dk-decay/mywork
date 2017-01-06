import { Component, OnInit } from 'angular2/core';
import { UserSearchComponent } from './user-search.component';
import { FooterComponent } from './footer.component'
import { RouteParams } from 'angular2/router';
import { UsersService } from './users.service';

@Component({
    selector: 'dashboard',
    templateUrl: 'app/dashboard.component.html',
    // styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
    //     'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
    directives: [UserSearchComponent, FooterComponent],
    providers: [UsersService]
})

export class DashboardComponent implements OnInit {
    private _userObj;
    private obj = [];
    username;
    constructor(private _routeParams: RouteParams,
        private _userService: UsersService) { }
    ngOnInit() {

        this.username = this._routeParams.get('username');
        console.log('Init : Dashboard : ', this.username);
        this._userService.populateUserDetails(this.username)
            .subscribe(response => {
                this._userObj = response;
                console.log('user details found :', this._userObj);
            },
            error => { console.log('error !') });

        this._userService
            .populateItemsSold(this.username)
            .subscribe(response => {

                for (var i in response) {
                    var res = response[i]
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

            })
    }

}