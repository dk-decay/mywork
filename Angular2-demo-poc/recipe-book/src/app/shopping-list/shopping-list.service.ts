import { Ingrediant } from '../shared/ingrediant';
export class ShoppingListService {
  private ingrediants: Ingrediant[] = [];
  constructor() { }

  getIngrediants() {
    return this.ingrediants;
  }

  addIngrediants(items: Ingrediant[]) {
    console.log('adding ingrediants');
    Array.prototype.push.apply(this.ingrediants, items);
  }

  addIngrediant(item: Ingrediant) {
    this.ingrediants.push(item)
  }

  editIngrediant(oldItem: Ingrediant, newItem: Ingrediant) {
    this.ingrediants[this.ingrediants.indexOf(oldItem)] = newItem;
  }

  deleteIngrediant(item: Ingrediant) {
    this.ingrediants.splice(this.ingrediants.indexOf(item), 1);
  }

}
