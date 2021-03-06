import { Router, Routes } from '@angular/router'
import { UserDetailComponent } from './user-detail.component'
import { UserEditComponent } from './user-edit.component'
import { UserDetailGuard } from './user-detail.guard'
export const USER_ROUTES: Routes = [
    { path: 'edit', component: UserEditComponent, canActivate: [UserDetailGuard] },
    { path: 'detail', component: UserDetailComponent }
]