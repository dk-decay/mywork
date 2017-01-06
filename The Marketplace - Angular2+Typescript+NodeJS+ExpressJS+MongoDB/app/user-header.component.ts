import { Component } from 'angular2/core';


@Component({

    selector : 'user-header',
    template : `
        <nav class="navbar navbar-default navbar-fixed-top">
             <div class="container-fluid">
                 <div class="navbar-header">
                     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                         <span class="icon-bar"></span>
                     </button>
                 </div> 
                <div class="collapse navbar-collapse" id="myNavbar">
                   <ul class="nav navbar-nav">
                   <li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                   <li><a href="#">About Us</a></li>
                   <li><a href="#">Contact</a></li>
                </ul>
             </div>
        </div>
    </nav>
    `

})

export class UserHeaderComponent{
    
}