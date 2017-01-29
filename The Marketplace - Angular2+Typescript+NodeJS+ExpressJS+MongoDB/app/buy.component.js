System.register(['angular2/core', './products', './products.service', './user-search.component', 'angular2/router', './accordion.component', './footer.component'], function(exports_1, context_1) {
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
    var core_1, products_1, products_service_1, user_search_component_1, router_1, accordion_component_1, footer_component_1;
    var BuyComponent;
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
            },
            function (accordion_component_1_1) {
                accordion_component_1 = accordion_component_1_1;
            },
            function (footer_component_1_1) {
                footer_component_1 = footer_component_1_1;
            }],
        execute: function() {
            BuyComponent = class BuyComponent {
                constructor(_productService, _routeParams) {
                    this._productService = _productService;
                    this._routeParams = _routeParams;
                    this.change = new core_1.EventEmitter();
                }
                ngOnInit() {
                    this.username = this._routeParams.get("username");
                    console.log('Init : Buy : ', this.username);
                    this._productService.getProducts()
                        .subscribe(response => {
                        console.log('Received all products', response);
                        this.products = response;
                    });
                }
                onBidNow() {
                    console.log("catching event from bid now");
                    this.bidNow = true;
                    this.change.emit({ newValue: this.bidNow });
                }
                submitProductBid(product) {
                    console.log('selected product', product);
                    if (product.amount > this.bidAmt) {
                        alert('Cannot Accept bid amount lesser than expected value');
                    }
                    else {
                        alert('Bid accepted');
                        this._productService
                            .placeBid(this.bidAmt, product)
                            .subscribe(res => {
                            this.bidAmt = '';
                            alert('Bid Accepted!');
                        });
                    }
                }
            };
            __decorate([
                core_1.Input(), 
                __metadata('design:type', Object)
            ], BuyComponent.prototype, "bidNow", void 0);
            __decorate([
                core_1.Output(), 
                __metadata('design:type', Object)
            ], BuyComponent.prototype, "change", void 0);
            BuyComponent = __decorate([
                core_1.Component({
                    selector: 'buy',
                    templateUrl: 'app/buy.component.html',
                    // styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
                    //     'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
                    providers: [products_1.Products, products_service_1.ProductsService],
                    directives: [user_search_component_1.UserSearchComponent, accordion_component_1.AccordionComponent, footer_component_1.FooterComponent]
                }), 
                __metadata('design:paramtypes', [products_service_1.ProductsService, router_1.RouteParams])
            ], BuyComponent);
            exports_1("BuyComponent", BuyComponent);
        }
    }
});
//# sourceMappingURL=buy.component.js.map