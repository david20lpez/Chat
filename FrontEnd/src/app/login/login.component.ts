import { Component } from '@angular/core';
import {Router} from '@angular/router';
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

  ngOnInit() {
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