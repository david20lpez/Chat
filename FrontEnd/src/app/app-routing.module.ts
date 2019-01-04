import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UsersListComponent } from './users-list/users-list.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { SearchUsersComponent } from './search-users/search-users.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { CreateCategoryComponent } from './create-category/create-category.component';
import { SearchCategoriesComponent } from './search-categories/search-categories.component';

const routes: Routes = [
    { path: '', redirectTo: 'user', pathMatch: 'full' },
    { path: 'user/user', component: UsersListComponent },
    { path: 'user/add', component: CreateUserComponent },
    { path: 'user/findbyactive', component: SearchUsersComponent },
    { path: 'category/category', component: CategoryListComponent },
    { path: 'category/add', component: CreateCategoryComponent },
    { path: 'category/findbyname', component: SearchCategoriesComponent },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }