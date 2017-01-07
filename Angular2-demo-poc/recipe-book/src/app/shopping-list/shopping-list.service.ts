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

}
