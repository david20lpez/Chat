import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';
import { UserService } from '../user.service';
import { Category } from '../category'
import { CategoryService } from '../category.service'

@Component({
  selector: 'search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.css']
})
export class SearchUsersComponent implements OnInit {

  category: string;
  users: User[];
  categories: Observable<Category[]>;

  constructor(private dataService: UserService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.reloadCatData();
    this.category = "";
  }

  private searchUsers() {
    this.dataService.getUsersByCategory(this.category)
      .subscribe(users => this.users = users);
  }

  private reloadCatData(){
    this.categories = this.categoryService.getCategoriesList();
  }

  onSubmit() {
    this.searchUsers();
  }
}