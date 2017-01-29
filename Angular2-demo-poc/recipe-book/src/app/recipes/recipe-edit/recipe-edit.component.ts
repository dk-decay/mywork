import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { Recipe } from '../recipe';
import { RecipeService } from '../recipe.service';
import { FormArray, FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'rb-recipe-edit',
  templateUrl: './recipe-edit.component.html',
  styles: [`
  #resize {
    max-height: 300px;
    min-height : 300px;
  }
  `]
})
export class RecipeEditComponent implements OnInit, OnDestroy {
  private recipeForm: FormGroup
  private isNew: boolean;
  private recipeIndex: number;
  private subscription: Subscription;
  private recipe: Recipe;

  constructor(private activatedRoute: ActivatedRoute, private router : Router,
    private recipeService: RecipeService, 
    private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.subscription = this.activatedRoute.params.subscribe((data: any) => {
      if (data.hasOwnProperty('id')) {
        this.recipeIndex = +data['id']
        this.isNew = false;
        this.recipe = this.recipeService.getRecipe(this.recipeIndex);
      } else {
        this.recipe = null;
        this.isNew = true;
        this.recipe = null;
      }
      this.initForm();
    })

  }

  ngOnDestroy() {
    this.subscription.unsubscribe()
  }

  private initForm() {
    let recipeName = '';
    let recipeImageUrl = '';
    let recipeContent = '';
    let recipeIngrediants: FormArray = new FormArray([])

    if (!this.isNew) {
      if(this.recipe.hasOwnProperty('ingrediants')) {
      for (let i = 0; i < this.recipe.ingrediants.length; i++) {
        recipeIngrediants.push(new FormGroup({
          name: new FormControl(this.recipe.ingrediants[i].name, Validators.required),
          amount: new FormControl(this.recipe.ingrediants[i].amount,
            [Validators.required, Validators.pattern("\\d+")])
        }))
      }
      }
      recipeName = this.recipe.name;
      recipeImageUrl = this.recipe.imagePath;
      recipeContent = this.recipe.description;
      
    }

    this.recipeForm = this.formBuilder.group({
      name: [recipeName, Validators.required],
      imagePath: [recipeImageUrl, Validators.required],
      description: [recipeContent, Validators.required],
      ingrediants: recipeIngrediants
    })

  }

  onSubmit() {
    const newRecipe = this.recipeForm.value;
    if(this.isNew) {
      this.recipeService.addRecipe(newRecipe);
    } else {
      this.recipeService.editRecipe(this.recipe, newRecipe);
    }
    this.navigateBack();
  }

  private navigateBack() {
    this.router.navigate(['../'])
  }

  onCancel() {
    this.navigateBack();
  }

  onAddItem(itemName : string, itemAmount : number) {
    (<FormArray>this.recipeForm.controls['ingrediants']).push(
      new FormGroup({
          name: new FormControl(itemName, Validators.required),
          amount: new FormControl(itemAmount,
            [Validators.required, Validators.pattern("\\d+")])
        }))
  }
  onRemoveItem(index: number) {
    (<FormArray>this.recipeForm.controls['ingrediants']).removeAt(index);
  }

}
