import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})

@Component({
  selector: 'category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})


export class UsersListComponent implements OnInit {

  users: Observable<User[]>;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.users = this.userService.getUsersList();
  }
}