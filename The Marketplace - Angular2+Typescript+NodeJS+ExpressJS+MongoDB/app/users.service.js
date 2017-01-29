System.register(['angular2/core', 'angular2/http', 'rxjs/add/operator/map'], function(exports_1, context_1) {
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
    var core_1, http_1;
    var UsersService;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (_1) {}],
        execute: function() {
            UsersService = class UsersService {
                constructor(_http) {
                    this._http = _http;
                }
                registerUser(userObj) {
                    console.log(userObj);
                    var body = JSON.stringify(userObj);
                    var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
                    var options = new http_1.RequestOptions({ headers: headers });
                    console.log("making post request");
                    return this._http.post('/user-sign-up', body, options)
                        .map(response => response.json(), error => error.json());
                }
                validateExistingUser(userObj) {
                    var body = JSON.stringify(userObj);
                    var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
                    var options = new http_1.RequestOptions({ headers: headers });
                    console.log("making post request");
                    return this._http.post('/login', body, options)
                        .map(response => response.json(), error => error.json());
                }
                populateUserDetails(username) {
                    return this._http.get('/getUserDetails/' + username)
                        .map(response => response.json(), error => error.json());
                }
                populateItemsPurchase() {
                }
                populateItemsRented() {
                }
                populateItemsSold(username) {
                    return this._http.get('/findAllItemsSold/' + username)
                        .map(response => response.json(), error => error.json());
                }
                findHighestBid(buyId) {
                    console.log('passing bid id :', buyId);
                    return this._http.get('/getHighestPrice/' + buyId)
                        .map(response => response.json(), error => error.json());
                }
            };
            UsersService = __decorate([
                core_1.Injectable(), 
                __metadata('design:paramtypes', [http_1.Http])
            ], UsersService);
            exports_1("UsersService", UsersService);
        }
    }
});
//# sourceMappingURL=users.service.js.map