import { Component, OnInit } from 'angular2/core';
import { Products } from './products';
import { ProductsService } from './products.service';
import { ControlGroup, FormBuilder, Validators } from 'angular2/common';
import { RouterLink, CanDeactivate, Router, RouteParams } from 'angular2/router';
import { UserSearchComponent } from './user-search.component';
import { FooterComponent } from './footer.component';


@Component({
    selector: 'sell',
    templateUrl: 'app/sell.component.html',
    // styleUrls: ['app/home/css/bootstrap.min.css', 'app/home/css/bootstrap.css',
    //     'app/home/css/additional.css', 'app/home/css/material-dashboard.css'],
    providers: [Products, ProductsService],
    directives: [UserSearchComponent, FooterComponent]
})

export class SellComponent implements CanDeactivate, OnInit {
    filesToUpload: Array<File>;
    username: string;
    sellForm: ControlGroup;

    types = [
        { value: 'Rent', display: 'Rent' },
        { value : 'Sell' ,display: 'Sell' }
    ];
    constructor(fb: FormBuilder,
        private prodObj: Products,
        private _productService: ProductsService,
        private router: Router,
        private _routeParams: RouteParams) {

        this.sellForm = fb.group({
            productTitle: ['', Validators.required],
            category: ['', Validators.required],
            desc: ['', Validators.required],
            amount: ['', Validators.required],
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

    fileChangeEvent(fileInput: any) {
        console.log('invoked file change event');
        this.filesToUpload = <Array<File>>fileInput.target.files;
    }

    uploadProduct() {
        console.log('filesToUpload :', this.filesToUpload.length);
        this._productService
            .saveProductDetails(this.prodObj,
            this.username, "/item/upload",
            this.filesToUpload)
            .then(response => {
                if (response) {
                    console.log('File uploaded successfully', response);
                    // navigate user
                    this.router.navigate(['Dashboard', { username: this.username }]);
                } else {
                    // show error
                    console.log('error uploading data');
                }
            });
    }

}