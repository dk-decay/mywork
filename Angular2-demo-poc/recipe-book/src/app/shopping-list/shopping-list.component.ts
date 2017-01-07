import { Component, OnInit } from '@angular/core';
import { Ingrediant } from '../shared/ingrediant';
import { ShoppingListService } from './shopping-list.service';

@Component({
  selector: 'rb-shopping-list',
  templateUrl: './shopping-list.component.html'
})
export class ShoppingListComponent implements OnInit {
  items: Ingrediant[];
  constructor(private sls: ShoppingListService) { }

  ngOnInit() {
    this.items = this.sls.getIngrediants();
  }

}
