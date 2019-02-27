import { Component, OnInit, Input } from '@angular/core';
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
    this.user = JSON.parse(localStorage.getItem("user"));
    this.category = localStorage.getItem("category");
    this.id = localStorage.getItem("id");
  }

  users: Observable<User[]>;
  user: User;
  personName = localStorage.getItem("name");
  category: string;
  id: string;
  response: boolean;
  greetings: string[] = [];
  disabled = true;
  message: string;
  private stompClient = null;
  private visitorClient = null;
  private chatClient = null;
  private localClient = null;
  private idClient = null;
  local: string;
  randomId: string;

  ngOnInit() {
    this.connect();
    this.connectId();
    this.connectLocal();
    console.log(this.user);
    this.connectVisitor();
    }

  reloadData() {
    this.users = this.userService.getUsersByActive(true); 
  }


  logOut(){
    localStorage.removeItem("LoggedInUser");
    this.disconnectVisitor();
    this.userService.logOut(this.user).subscribe();
    this.sendName();
    this.sendCategory();
    this.disconnect();
    this.disconnectLocal();
    this.disconnectVisitor();
    this.disconnectId();
    this.router.navigate(['']);
    this.connect();
  }

  openRoom(){
    this.router.navigate(['home/user/chat']);
  }


 
  setConnected(connected: boolean) {
    this.disabled = !connected;
  }

 
  connect() {
    const socket = new SockJS('http://localhost:8080/chat-endpoint');
    this.stompClient = Stomp.over(socket);
 
    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.sendName();
      _this.sendCategory();
      _this.stompClient.subscribe('/topic/user', function (hello) {
        _this.reloadData();
      });
    });
  }

  async delay(ms: number) {
    await new Promise(resolve => setTimeout(()=>resolve(), ms)).then(()=> this.connectChat());
}

  connectVisitor() {
    const socket = new SockJS('http://localhost:8080/chat-endpoint');
    this.visitorClient = Stomp.over(socket);
 
    const _this = this;
    this.visitorClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.visitorClient.subscribe('/topic/id', function (hello) {
        if(_this.id == hello.body || _this.id == _this.local){
          console.log("this is the local id: " + _this.local);
          console.log("this is the visitor id::: " + hello.body);
          console.log("ramdom uuid: "+ _this.randomId);
          _this.delay(3000);
        }
        else{
          console.log("Not this time man");
          console.log(_this.local);
          _this.response = false;
        }
      });
    });
  }

  connectChat() {
    const socket = new SockJS('http://localhost:8080/chat-endpoint');
    this.chatClient = Stomp.over(socket);
 
    const _this = this;
    this.chatClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.chatClient.subscribe('/topic/private/'+ _this.randomId, function (hello) {
        _this.showGreeting(JSON.parse(hello.body).content);
      });
    });
  }

  connectId() {
    const socket = new SockJS('http://localhost:8080/chat-endpoint');
    this.idClient = Stomp.over(socket);
 
    const _this = this;
    this.idClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.idClient.subscribe('/topic/random', function (hello) {
        _this.randomId = hello.body;
      });
    });
  }

  connectLocal() {
    const socket = new SockJS('http://localhost:8080/chat-endpoint');
    this.localClient = Stomp.over(socket);
 
    const _this = this;
    this.localClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.localClient.subscribe('/topic/local', function (hello) {
        _this.local = hello.body;
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

  disconnectVisitor() {
    if (this.visitorClient != null) {
      this.visitorClient.disconnect();
    }
    this.setConnected(false);
    console.log('Disconnected Visitor!');
  }

  disconnectChat() {
    if (this.chatClient != null) {
      this.chatClient.disconnect();
    }
    this.setConnected(false);
    console.log('Disconnected Chat!');
  }

  disconnectLocal() {
    if (this.localClient != null) {
      this.localClient.disconnect();
    }
    this.setConnected(false);
    console.log('Disconnected Chat!');
  }

  disconnectId() {
    if (this.idClient != null) {
      this.idClient.disconnect();
    }
    this.setConnected(false);
    console.log('Disconnected Chat!');
  }
 
  sendName() {
    this.stompClient.send(
      '/chat/users',
      {},
      "response"
    );
  }

  sendCategory() {
    this.stompClient.send(
      '/chat/categories',
      {},
      this.category
    );
  }


  sendVisitor(){
    this.visitorClient.send(
      '/chat/get/id',
      {},
      localStorage.getItem("userId")
    ) 
  }
  

  sendMessage() {
    this.chatClient.send(
      '/chat/private/'+this.randomId, {},
      localStorage.getItem("name") + ': ' + this.message 
    );
    this.message = "";
  }
 
  showGreeting(words) {
    this.greetings.push(words);
  }

}
