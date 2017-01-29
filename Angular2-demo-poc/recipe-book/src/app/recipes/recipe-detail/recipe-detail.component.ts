import { Component, OnInit, OnDestroy } from '@angular/core';
import { Recipe } from '../recipe';
import { ShoppingListService } from '../../shopping-list/shopping-list.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { RecipeService } from '../recipe.service';

@Component({
  selector: 'rb-recipe-detail',
  templateUrl: './recipe-detail.component.html'
})
export class RecipeDetailComponent implements OnInit, OnDestroy {

  selectedRecipe: Recipe;
  private subscription: Subscription;
  private id;

  constructor(private sls: ShoppingListService,
    private activatedRoute: ActivatedRoute,
    private recipeService: RecipeService,
    private router: Router) { }

  ngOnInit() {
    this.subscription = this.activatedRoute.params.subscribe((params) => {
      this.id = params['id'];
      this.selectedRecipe = this.recipeService.getRecipe(this.id);
    })
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  addToShoppingList() {
    this.sls.addIngrediants(this.selectedRecipe.ingrediants);
  }

  onEdit() {
    this.router.navigate(['/recipes', this.id, 'edit'])
  }

  onDelete() {
    this.recipeService.deleteRecipe(this.selectedRecipe);
    this.router.navigate(['/recipes'])
  }

}
