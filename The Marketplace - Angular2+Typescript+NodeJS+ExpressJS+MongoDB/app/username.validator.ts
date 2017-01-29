import { Control } from 'angular2/common'

export class UsernameValidator {

   static cannotContainSpace(control: Control) {
       if (control.value && control.value.indexOf('') >= 0) {
           return { cannotContainSpace: true };
       }
       return null;
   }
}