import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-template-driven',
  templateUrl: './template-driven.component.html',
  styles: [`
  .ng-invalid {
    
  }
  
  `]
})
export class TemplateDrivenComponent implements OnInit {
  user = {
    username: 'Devesh',
    password: '123',
    email: 'devesh24@gmail.com',
    gender : 'male'
  }

  genders = ['male', 'female']
  constructor() { }

  ngOnInit() {
  }

  onSubmit(f: NgForm) {
    console.log(f.value);
  }

}
