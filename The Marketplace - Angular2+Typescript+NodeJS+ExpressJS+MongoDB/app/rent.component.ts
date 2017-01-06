import { Component, OnInit } from 'angular2/core';
import { Products } from './products';
import { ProductsService } from './products.service';
import { UserSearchComponent } from './user-search.component'
import { RouterLink, CanDeactivate, Router, RouteParams } from 'angular2/router';

@Component({
selector: 'rent',
    templateUrl: 'app/rent.component.html',
    // styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
    //     'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
    providers: [Products, ProductsService],
    directives: [UserSearchComponent]
})

export class RentComponent implements OnInit {
 private _productObj: Products[];
    current;
    images;
    products;
    username;
    constructor(private _productService: ProductsService,
        private _routeParams : RouteParams) { }

    ngOnInit() {
        this.username = this._routeParams.get("username");
         console.log('Init : Rent : ', this.username);
        this._productService.getProducts()
        .subscribe(res => this.products = res);
        //  .subscribe(response => {
        //      this._productObj = response
        //    });

    }
}



