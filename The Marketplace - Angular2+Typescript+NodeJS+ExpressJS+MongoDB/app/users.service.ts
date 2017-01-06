import { Injectable } from 'angular2/core';
import { Http, Headers, RequestOptions } from 'angular2/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { Users } from './users';

@Injectable()
export class UsersService {




    constructor(private _http: Http) { }

    registerUser(userObj: Users) {

        console.log(userObj);
        var body = JSON.stringify(userObj);
        var headers = new Headers({ 'Content-Type': 'application/json' });
        var options = new RequestOptions({ headers: headers });

        console.log("making post request");
        return this._http.post('/user-sign-up', body, options)
            .map(response => response.json(), error => error.json());

    }

    validateExistingUser(userObj: Users) {
         var body = JSON.stringify(userObj);
        var headers = new Headers({ 'Content-Type': 'application/json' });
        var options = new RequestOptions({ headers: headers });
        console.log("making post request");
        return this._http.post('/login', body, options)
            .map(response => response.json(), error => error.json());
    }

    populateUserDetails(username: String) {
        return this._http.get('/getUserDetails/'+username)
        .map(response => response.json(), error => error.json());
    }

    populateItemsPurchase() {

    }

    populateItemsRented() {

    }

    populateItemsSold(username : String) {
         return this._http.get('/findAllItemsSold/'+username)
        .map(response => response.json(), error => error.json());
        
    }

    findHighestBid(buyId : string) {
        console.log('passing bid id :', buyId);
          return this._http.get('/getHighestPrice/'+buyId)
        .map(response => response.json(), error => error.json());
    }

}