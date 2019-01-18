import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

import { UserService } from '../user.service';
import { User } from '../user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-active-users',
  templateUrl: './active-users.component.html',
  styleUrls: ['./active-users.component.css']
})
export class ActiveUsersComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { 
    this.user = new User();
  }

  users: Observable<User[]>;
  user: User;
  personName = localStorage.getItem("name");
  chat = false;


  reloadSite(){
    this.reloadData();
  }

  ngOnInit() {
    this.reloadData();
    this.connect();
  }

  reloadData() {
    this.users = this.userService.getUsersByActive(true); 
  }

  logOut(){
    localStorage.setItem("name", null);
    this.router.navigate([""]);
    this.user = JSON.parse(localStorage.getItem("user"));
    console.log(this.user);
    this.userService.logOut(this.user).subscribe();
  }

  open(){
    this.chat = true;
  }

  greetings: string[] = [];
  disabled = true;
  name: string;
  private stompClient = null;
 
 
  setConnected(connected: boolean) {
    this.disabled = !connected;
 
    if (connected) {
      this.greetings = [];
    }
  }
 
  connect() {
    const socket = new SockJS('http://localhost:8080/chat-endpoint');
    this.stompClient = Stomp.over(socket);
 
    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
 
      _this.stompClient.subscribe('/topic/hi', function (hello) {
        _this.showGreeting(JSON.parse(hello.body).content);
      });
    });
  }
 
  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }
 
    this.setConnected(false);
    console.log('Disconnected!');
  }
 
  sendName() {
    this.stompClient.send(
      '/chat/hello',
      {},
      localStorage.getItem("name") + ': ' + this.name
    );
  }
 
  showGreeting(message) {
    this.greetings.push(message);
  }

}
