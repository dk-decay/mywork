import { Control } from 'angular2/common';

export class EmailValidator {

   static validateEmail(control: Control) {
       var email = control.value;
       var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
       console.log(re.test(email));
       if(email != null && !re.test(email)) {
           return {validEmail : true};
       }
       return null;
   }

}