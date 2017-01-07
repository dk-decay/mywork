import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';
@Component({
  selector: 'rb-recipe-list',
  templateUrl: './recipe-list.component.html',
  styles: []
})
export class RecipeListComponent implements OnInit {
  recipes: Recipe[] = [];
  constructor(private recipeService: RecipeService) { }
  @Output() recipeSelected = new EventEmitter<Recipe>();
  ngOnInit() {
    this.recipes = this.recipeService.getRecipes();
  }
  onSelected(recipe: Recipe) {
    this.recipeSelected.emit(recipe);
  }
}
