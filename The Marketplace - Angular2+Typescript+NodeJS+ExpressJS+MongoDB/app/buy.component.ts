import { Component, OnInit, Input, EventEmitter, Output } from 'angular2/core';
import { Products } from './products';
import { ProductsService } from './products.service';
import { UserSearchComponent } from './user-search.component'
import { RouteParams } from 'angular2/router';
import { AccordionComponent } from './accordion.component';
import { FooterComponent } from './footer.component';

@Component({
    selector: 'buy',
    templateUrl: 'app/buy.component.html',
    // styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
    //     'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
    providers: [Products, ProductsService],
    directives: [UserSearchComponent, AccordionComponent, FooterComponent]
})

export class BuyComponent implements OnInit {

    @Input() bidNow;
    @Output() change = new EventEmitter();
    private _productObj: Products[];
    current;
    images;
    products;
    username;

    bidAmt;
    constructor(private _productService: ProductsService,
        private _routeParams: RouteParams) { }

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
        console.log('selected product',product);
        if(product.amount > this.bidAmt) {
            alert('Cannot Accept bid amount lesser than expected value');
        } else {
            alert('Bid accepted');
            this._productService
            .placeBid(this.bidAmt, product)
            .subscribe(res => {
                this.bidAmt = '';
                alert('Bid Accepted!')
            });
            
        }
    }
}