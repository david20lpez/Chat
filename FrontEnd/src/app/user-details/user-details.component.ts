import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';

import { UsersListComponent } from '../users-list/users-list.component';

@Component({
  selector: 'user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  @Input() user: User;

  constructor(private userService: UserService, private listComponent: UsersListComponent) { }

  ngOnInit() {
  }

  showId() {
    console.log(this.user.id);
  }

  deleteUser() {
    this.userService.deleteUser(this.user.id)
      .subscribe(
        data => {
          console.log(data);
          this.listComponent.reloadData();
        },
        error => console.log(error));
  }
}