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

const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: HomeComponent},
    { path: 'home/user/user', component: UsersListComponent },
    { path: 'home/user/add', component: CreateUserComponent },
    { path: 'home/user/findbyactive', component: SearchUsersComponent },
    { path: 'home/category/category', component: CategoryListComponent },
    { path: 'home/category/add', component: CreateCategoryComponent },
    { path: 'home/category/findbyname', component: SearchCategoriesComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }