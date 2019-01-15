import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersListComponent } from './users-list/users-list.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { SearchUsersComponent } from './search-users/search-users.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { CreateCategoryComponent } from './create-category/create-category.component';
import { SearchCategoriesComponent } from './search-categories/search-categories.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ActiveUsersComponent } from './active-users/active-users.component';
<<<<<<< HEAD

const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: HomeComponent},
    { path: 'home/user/user', component: UsersListComponent },
    { path: 'home/user/add', component: CreateUserComponent },
    { path: 'home/user/active_users', component: ActiveUsersComponent },
    { path: 'home/user/findbyactive', component: SearchUsersComponent },
    { path: 'home/category/category', component: CategoryListComponent },
    { path: 'home/category/add', component: CreateCategoryComponent },
    { path: 'home/category/findbyname', component: SearchCategoriesComponent },
=======
import { LoginGuard } from './login.guard';

const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate: [LoginGuard]},
    { path: 'home/user/user', component: UsersListComponent,  canActivate: [LoginGuard] },
    { path: 'home/user/add', component: CreateUserComponent,  canActivate: [LoginGuard] },
    { path: 'home/user/active_users', component: ActiveUsersComponent,  canActivate: [LoginGuard] },
    { path: 'home/user/findbyactive', component: SearchUsersComponent,  canActivate: [LoginGuard] },
    { path: 'home/category/category', component: CategoryListComponent,  canActivate: [LoginGuard] },
    { path: 'home/category/add', component: CreateCategoryComponent,  canActivate: [LoginGuard] },
    { path: 'home/category/findbyname', component: SearchCategoriesComponent,  canActivate: [LoginGuard] },
>>>>>>> 96ad669b3704dafc8bf5322b95a2c507281f936d
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }