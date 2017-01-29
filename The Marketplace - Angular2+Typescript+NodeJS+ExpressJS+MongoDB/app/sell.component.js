System.register(['angular2/core', './products', './products.service', 'angular2/common', 'angular2/router', './user-search.component', './footer.component'], function(exports_1, context_1) {
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
    var core_1, products_1, products_service_1, common_1, router_1, user_search_component_1, footer_component_1;
    var SellComponent;
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
            function (common_1_1) {
                common_1 = common_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (user_search_component_1_1) {
                user_search_component_1 = user_search_component_1_1;
            },
            function (footer_component_1_1) {
                footer_component_1 = footer_component_1_1;
            }],
        execute: function() {
            SellComponent = class SellComponent {
                constructor(fb, prodObj, _productService, router, _routeParams) {
                    this.prodObj = prodObj;
                    this._productService = _productService;
                    this.router = router;
                    this._routeParams = _routeParams;
                    this.types = [
                        { value: 'Rent', display: 'Rent' },
                        { value: 'Sell', display: 'Sell' }
                    ];
                    this.sellForm = fb.group({
                        productTitle: ['', common_1.Validators.required],
                        category: ['', common_1.Validators.required],
                        desc: ['', common_1.Validators.required],
                        amount: ['', common_1.Validators.required],
                        // postDate: ['', Validators.required],
                        type: ['']
                    });
                }
                routerCanDeactivate() {
                    if (this.sellForm.dirty) {
                        return confirm("Are you sure ?");
                    }
                    return true;
                }
                ngOnInit() {
                    this.username = this._routeParams.get('username');
                    console.log('Init : Sell : ', this.username);
                }
                fileChangeEvent(fileInput) {
                    console.log('invoked file change event');
                    this.filesToUpload = fileInput.target.files;
                }
                uploadProduct() {
                    console.log('filesToUpload :', this.filesToUpload.length);
                    this._productService
                        .saveProductDetails(this.prodObj, this.username, "/item/upload", this.filesToUpload)
                        .then(response => {
                        if (response) {
                            console.log('File uploaded successfully', response);
                            // navigate user
                            this.router.navigate(['Dashboard', { username: this.username }]);
                        }
                        else {
                            // show error
                            console.log('error uploading data');
                        }
                    });
                }
            };
            SellComponent = __decorate([
                core_1.Component({
                    selector: 'sell',
                    templateUrl: 'app/sell.component.html',
                    // styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
                    //     'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
                    providers: [products_1.Products, products_service_1.ProductsService],
                    directives: [user_search_component_1.UserSearchComponent, footer_component_1.FooterComponent]
                }), 
                __metadata('design:paramtypes', [common_1.FormBuilder, products_1.Products, products_service_1.ProductsService, router_1.Router, router_1.RouteParams])
            ], SellComponent);
            exports_1("SellComponent", SellComponent);
        }
    }
});
//# sourceMappingURL=sell.component.js.map