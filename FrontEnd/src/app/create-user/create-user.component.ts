import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { User } from '../user';
import { UserService } from '../user.service';
import { Category } from '../category'
import { CategoryService } from '../category.service'


@Component({
  selector: 'create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  submitted = false;
  categories: Observable<Category[]>;


  constructor(private userService: UserService, private categoryService: CategoryService) { }

  ngOnInit() {
    this.reloadCatData();
  }

  newUser(): void {
    this.submitted = false;
    this.user = new User();
  }

  save() {
    this.userService.createUser(this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
  }

  reloadCatData(){
    this.categories = this.categoryService.getCategoriesList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }


}