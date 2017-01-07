import { Injectable } from '@angular/core';
import { Recipe } from './recipe';
import { Ingrediant } from '../shared/ingrediant';
@Injectable()
export class RecipeService {
  private recipes: Recipe[] = [
    new Recipe('Schnitzel', 'Very Tasty', 'http://www.daringgourmet.com/wp-content/uploads/2014/03/Schnitzel-7_edited.jpg', [new Ingrediant('Sour Cream', 2), new Ingrediant('Port Meat', 1)]),
    new Recipe('Summer Salad', 'Okayish', 'http://cdn.iowagirleats.com/wp-content/uploads/2013/05/Triple-Berry-Summer-Salad-03_mini.jpg', [])
  ];
  constructor() { }

  getRecipes() {
    return this.recipes;
  }
}
