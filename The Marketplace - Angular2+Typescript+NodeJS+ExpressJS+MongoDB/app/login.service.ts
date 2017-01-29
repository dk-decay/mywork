import { Http, Headers, RequestOptions } from 'angular2/http';
import { Injectable } from 'angular2/core';
import 'rxjs/add/operator/map';

@Injectable()
export class LoginService {

    constructor(private _http: Http) { }

    getUser() {
        return this._http.get("http://jsonplaceholder.typicode.com/posts")
            .map(res => res.json());
    }

    loginuser(post) {
        var headers = new Headers({ 'Content-Type': 'application/json', 'charset': 'UTF-8' });
        var options = new RequestOptions({ headers: headers });

        var body = JSON.stringify(post);
        console.log("post parsing", body);
        return this._http.post("/login", body, options)
            .map(res => res.json());
    }
}