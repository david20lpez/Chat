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
import { LoginGuard } from './login.guard';
import { ChatRoomComponent } from './chat-room/chat-room.component';

const routes: Routes = [
    { path: '', component: LoginComponent },
    { path: 'home', component: HomeComponent, canActivate: [LoginGuard]},
    { path: 'home/user/user', component: UsersListComponent,  canActivate: [LoginGuard] },
    { path: 'home/user/add', component: CreateUserComponent,  canActivate: [LoginGuard] },
    { path: 'home/user/active_users', component: ActiveUsersComponent,  canActivate: [LoginGuard] },
    { path: 'home/user/findbyactive', component: SearchUsersComponent,  canActivate: [LoginGuard] },
    { path: 'home/user/chat', component: ChatRoomComponent, canActivate : [LoginGuard] },
    { path: 'home/category/category', component: CategoryListComponent,  canActivate: [LoginGuard] },
    { path: 'home/category/add', component: CreateCategoryComponent,  canActivate: [LoginGuard] },
    { path: 'home/category/findbyname', component: SearchCategoriesComponent,  canActivate: [LoginGuard] },
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})

export class AppRoutingModule { }