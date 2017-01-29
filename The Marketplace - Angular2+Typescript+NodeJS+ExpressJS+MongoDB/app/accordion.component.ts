import {Component, Input} from 'angular2/core';
@Component({
   selector: 'accordion',
    styles: [`
       .accordion {
               
       }
       
       .accordion .accordion-title {
           padding: 10px;
           font-weight: bold;
           color: white;
           background-color: #b80000;
       }
       
       .accordion .accordion-title:hover{
           background: rgba(184,0,0,0.9) ;
           box-shadow: 0 16px 38px -12px rgba(0, 0, 0, 0.56), 0 4px 25px 0px rgba(0, 0, 0, 0.12), 0 8px 10px -5px rgba(0, 0, 0, 0.2);
           transition-property: box-shadow, background-color;
           transition-duration: 0.2s, 0.2s;
           transition-timing-function: cubic-bezier(0.4, 0, 1, 1), cubic-bezier(0.4, 0, 0.2, 1);
           transition-delay: initial, initial;
       }
       
       .accordion .accordion-content {
           padding: 0px;
       }
   `],
   template: `
   <div class="accordion">
       <div 
           class="accordion-title"
           (click)="toggle()">
           {{ title }} 
            <i 
               class="pull-right glyphicon" 
               [ngClass]="
                   { 
                       'glyphicon-chevron-down': !isExpanded, 
                       'glyphicon-chevron-up': isExpanded 
                   }">
           </i>
       </div>
       <div *ngIf="isExpanded" class="accordion-content">
           <ng-content></ng-content> 
       </div>
   </div>    
   `
})
export class AccordionComponent {
   isExpanded = false;
   @Input() title: string;
       
   toggle(){
       this.isExpanded = !this.isExpanded;
   }
}