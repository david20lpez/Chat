import { Component } from '@angular/core';
import {Router} from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';
import { AuthService } from '../auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private router: Router, private userService: UserService, private auth: AuthService) {
    this.user = new User();
  }
  user: User;
  email : string;
  password : string;
 

  ngOnInit() {
  }

  login(){
    this.userService.validLogIn(this.user).subscribe( res => {
      if (res != null) {
        localStorage.setItem("name", res.name);
        localStorage.setItem("user",JSON.stringify(res));
        localStorage.setItem("category", res.category);
        localStorage.setItem("id", JSON.stringify(res.id));
        this.auth.sendToken(res.email);
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
