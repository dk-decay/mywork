import { Routes, RouterModule } from '@angular/router';
import { HomeComponentComponent } from './home-component.component';
import { UserComponent } from './user/user.component';
import { USER_ROUTE } from './user/user.route'

const APP_ROUTES: Routes = [
    { path: 'user/:id', component: UserComponent },
    { path: 'user/:id', component: UserComponent, children: USER_ROUTE },
    { path: 'user', redirectTo: '/user/1', pathMatch: 'full' },
    { path: '', component: HomeComponentComponent },
    { path: '**', redirectTo: '/user/1', pathMatch: 'full' },
]

export const routing = RouterModule.forRoot(APP_ROUTES);