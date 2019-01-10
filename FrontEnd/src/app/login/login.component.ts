import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router, private userService: UserService) {
    this.user = new User();
  }
  user: User;
  email : string
  password : string
  users: Observable<User[]>;

  ngOnInit() {
    this.reloadData();
  }

  reloadData(){
    this.users = this.userService.getUsersList();
  }

  login() : void {
    this.userService.validLogIn(this.user).subscribe( res => {
      if (res != null) {
        this.router.navigate(["home"]);
      }
    }, err => {
      alert('LogIn invalido');
    });
  }

}