import { Component } from '@angular/core';
import { RecipeService } from './recipes/recipe.service';
@Component({
  selector: 'rb-root',
  templateUrl: './app.component.html',
  styles: [],
  providers: [RecipeService]

})
export class AppComponent {
  title = 'rb works!';
}
