import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Router} from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent{
  
  constructor(private router: Router, private userService: UserService) {
    this.user = new User();
  }

  ngOnInit() {
    this.connect();
  }

  title = 'Chat Application';

  user: User;
  personName = localStorage.getItem("name");
  greetings: string[] = [];
  disabled = true;
  name: string;
  private stompClient;
  //socket = new SockJS('http://localhost:8080/chat-endpoint');
 
 
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
    this.name = "";
  }
 
  showGreeting(message) {
    this.greetings.push(message);
  }

  logOut(){
    localStorage.setItem("name", null);
    this.router.navigate([""]);
    this.user = JSON.parse(localStorage.getItem("user"));
    this.disconnect();
    console.log(this.user);
    this.userService.logOut(this.user).subscribe();
  }

}
