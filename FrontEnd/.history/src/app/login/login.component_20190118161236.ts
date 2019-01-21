import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';


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
  email : string;
  password : string;
  private stompClient = null;

  ngOnInit() {
  }

  title = 'Chat Application';

  login(){
    this.userService.validLogIn(this.user).subscribe( res => {
      if (res != null) {
        localStorage.setItem("name", res.name);
        localStorage.setItem("user",JSON.stringify(res));
        if(res.role=="admin"){
          this.router.navigate(["home"]);
        }
        else{
          this.router.navigate(["home/user/active_users"]);
        }
      }
    }, err => {
      alert('LogIn invalido');
    });
  }
}
