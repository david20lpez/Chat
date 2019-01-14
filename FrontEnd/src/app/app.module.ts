import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UsersListComponent } from './users-list/users-list.component';
import { SearchUsersComponent } from './search-users/search-users.component';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { CreateCategoryComponent } from './create-category/create-category.component';
import { CategoryDetailsComponent } from './category-details/category-details.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { SearchCategoriesComponent } from './search-categories/search-categories.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ActiveUsersComponent } from './active-users/active-users.component';
import { LoginGuard } from './login.guard';

@NgModule({
  declarations: [
    AppComponent,
    CreateUserComponent,
    UserDetailsComponent,
    UsersListComponent,
    SearchUsersComponent,
    CreateCategoryComponent,
    CategoryDetailsComponent,
    CategoryListComponent,
    SearchCategoriesComponent,
    LoginComponent,
    HomeComponent,
    ActiveUsersComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [LoginGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
