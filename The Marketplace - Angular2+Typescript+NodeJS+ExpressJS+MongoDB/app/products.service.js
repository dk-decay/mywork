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
    var ProductsService;
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
            ProductsService = class ProductsService {
                constructor(_http) {
                    this._http = _http;
                }
                saveProductDetails(prodObj, username, url, files) {
                    console.log('Making request', prodObj.type);
                    return new Promise((resolve, reject) => {
                        var formData = new FormData();
                        formData.append("category", prodObj.category);
                        formData.append("amount", prodObj.amount);
                        formData.append("desc", prodObj.desc);
                        formData.append("title", prodObj.title);
                        formData.append("username", username);
                        formData.append("type", prodObj.type);
                        var xhr = new XMLHttpRequest();
                        for (var i = 0; i < files.length; i++) {
                            formData.append("uploads", files[i], files[i].name);
                        }
                        xhr.onreadystatechange = function () {
                            if (xhr.readyState == 4) {
                                if (xhr.status == 200) {
                                    resolve(JSON.parse(xhr.response));
                                }
                                else {
                                    reject(xhr.response);
                                }
                            }
                        };
                        xhr.open("POST", url, true);
                        xhr.send(formData);
                    });
                }
                getProducts() {
                    return this._http.get('/findAllItemsForSale')
                        .map(response => response.json());
                }
                placeBid(bidAmt, product) {
                    var b = {
                        buyId: product._id,
                        biddingUserId: product.username,
                        bidAmount: bidAmt
                    };
                    var body = JSON.stringify(b);
                    var headers = new http_1.Headers({ 'Content-Type': 'application/json' });
                    var options = new http_1.RequestOptions({ headers: headers });
                    console.log("making post request");
                    return this._http.post('/placeorder', body, options)
                        .map(response => response.json(), error => error.json());
                }
            };
            ProductsService = __decorate([
                core_1.Injectable(), 
                __metadata('design:paramtypes', [http_1.Http])
            ], ProductsService);
            exports_1("ProductsService", ProductsService);
        }
    }
});
//# sourceMappingURL=products.service.js.map