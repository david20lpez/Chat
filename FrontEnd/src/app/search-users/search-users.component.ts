import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.css']
})
export class SearchUsersComponent implements OnInit {

  active: boolean;
  users: User[];

  constructor(private dataService: UserService) { }

  ngOnInit() {
    this.active = false;
  }

  private searchUsers() {
    this.dataService.getUsersByActive(this.active)
      .subscribe(users => this.users = users);
  }

  onSubmit() {
    this.searchUsers();
  }
}