import { Component, Input, OnChanges, Output, EventEmitter } from '@angular/core';
import { Ingrediant } from '../shared/ingrediant';
import { ShoppingListService } from './shopping-list.service'
@Component({
  selector: 'rb-shopping-list-add',
  templateUrl: './shopping-list-add.component.html',
  styles: []
})
export class ShoppingListAddComponent implements OnChanges {
  isAdd = true;
  @Input() item: Ingrediant;
  @Output() cleared = new EventEmitter();
  constructor(private sls: ShoppingListService) { }

  ngOnChanges(changes) {
    if (changes.item.currentValue === null) {
      this.isAdd = true;
      this.item = { 'name': null, 'amount': null }
    } else {
      this.isAdd = false;
    }
  }

  onSubmit(ingrediant: Ingrediant) {
    const newIngrediant = new Ingrediant(ingrediant.name, ingrediant.amount)
    if (!this.isAdd) {
      // edit
      this.sls.editIngrediant(this.item, newIngrediant);
      this.onClear()
    } else {
      this.item = newIngrediant;
      this.sls.addIngrediant(this.item);
    }
  }

  onDelete() {
    this.sls.deleteIngrediant(this.item);
    this.onClear()
  }

  onClear() {
    this.isAdd = true;
    this.cleared.emit(null);
  }

}
