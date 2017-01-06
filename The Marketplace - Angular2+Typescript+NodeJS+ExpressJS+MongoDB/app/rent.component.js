System.register(['angular2/core', './products', './products.service', './user-search.component', 'angular2/router'], function(exports_1, context_1) {
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
    var core_1, products_1, products_service_1, user_search_component_1, router_1;
    var RentComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (products_1_1) {
                products_1 = products_1_1;
            },
            function (products_service_1_1) {
                products_service_1 = products_service_1_1;
            },
            function (user_search_component_1_1) {
                user_search_component_1 = user_search_component_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            }],
        execute: function() {
            RentComponent = class RentComponent {
                constructor(_productService, _routeParams) {
                    this._productService = _productService;
                    this._routeParams = _routeParams;
                }
                ngOnInit() {
                    this.username = this._routeParams.get("username");
                    console.log('Init : Rent : ', this.username);
                    this._productService.getProducts()
                        .subscribe(res => this.products = res);
                    //  .subscribe(response => {
                    //      this._productObj = response
                    //    });
                }
            };
            RentComponent = __decorate([
                core_1.Component({
                    selector: 'rent',
                    templateUrl: 'app/rent.component.html',
                    // styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
                    //     'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
                    providers: [products_1.Products, products_service_1.ProductsService],
                    directives: [user_search_component_1.UserSearchComponent]
                }), 
                __metadata('design:paramtypes', [products_service_1.ProductsService, router_1.RouteParams])
            ], RentComponent);
            exports_1("RentComponent", RentComponent);
        }
    }
});
//# sourceMappingURL=rent.component.js.map