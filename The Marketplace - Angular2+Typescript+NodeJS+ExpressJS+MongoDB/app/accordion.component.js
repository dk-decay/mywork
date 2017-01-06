System.register(['angular2/core'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1;
    var AccordionComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            }],
        execute: function() {
            AccordionComponent = class AccordionComponent {
                constructor() {
                    this.isExpanded = false;
                }
                toggle() {
                    this.isExpanded = !this.isExpanded;
                }
            };
            __decorate([
                core_1.Input(), 
                __metadata('design:type', String)
            ], AccordionComponent.prototype, "title", void 0);
            AccordionComponent = __decorate([
                core_1.Component({
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
                }), 
                __metadata('design:paramtypes', [])
            ], AccordionComponent);
            exports_1("AccordionComponent", AccordionComponent);
        }
    }
});
//# sourceMappingURL=accordion.component.js.map