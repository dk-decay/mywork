import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormArray } from '@angular/forms';
import { Observable } from 'rxjs/Rx'
@Component({
  selector: 'app-data-driven',
  templateUrl: './data-driven.component.html',
  styles: []
})
export class DataDrivenComponent implements OnInit {

  myForm: FormGroup

  genders = ['male', 'female']
  constructor() {

    this.myForm = new FormGroup({
      'userData': new FormGroup({
        'username': new FormControl('Max', [Validators.required, this.exampleValidator]),
        'email': new FormControl('', [Validators.required,
        Validators.pattern("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")])
      }),
      'password': new FormControl('', Validators.required),
      'gender': new FormControl('male'),
      'hobbies': new FormArray([new FormControl('Cooking', Validators.required, this.asyncValidator)])
    });

  }

  ngOnInit() {
  }

  onSubmit() {
    console.log(this.myForm.value);
  }

  onAddHobby() {
    (<FormArray>this.myForm.controls['hobbies']).push(new FormControl('', Validators.required, this.asyncValidator))
  }


  exampleValidator(formControl: FormControl): { [s: string]: boolean } {
    if (formControl.value === 'Example') {
      return { 'Example': true }
    }
    return null
  }

  asyncValidator(formControl: FormControl): Promise<any> | Observable<any> {
    const promise = new Promise<any>(
      (resolve, reject) => {
        setTimeout(() => {
          if (formControl.value === 'Example') {
            resolve({ 'invalid': true })
          } else {
            resolve(null)
          }
        }, 1500)
      }
    )
    return promise;
  }

}
