import { Component, OnInit } from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { UserService } from '../user.service';
import { User } from '../user';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';


@Component({
  selector: 'app-chat-room',
  templateUrl: './chat-room.component.html',
  styleUrls: ['./chat-room.component.css']
})
export class ChatRoomComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) {
    this.user = new User();
    this.category = localStorage.getItem("category");
   }

  ngOnInit() {
    this.reloadData();
    this.connect();
    this.connectChat()
  }

  category: string;
  users: Observable<User[]>;
  user: User;
  personName = localStorage.getItem("name");


  reloadData(){
    this.users = this.userService.getUsersByCategoryAndActive(this.category);
    console.log(this.category);
  }


  disabled = true;
  disabledChat = true;
  message: string;
  private stompClient = null;
  private chatClient = null;
  greetings: string[] = [];
 
 
  setConnected(connected: boolean) {
    this.disabled = !connected;
  }

  setConnectedChat(connected: boolean){
    this.disabledChat = !connected;
  }
 
  connect() {
    const socket = new SockJS('http://localhost:8080/chat-endpoint');
    this.stompClient = Stomp.over(socket);
 
    const _this = this;
    this.stompClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.stompClient.subscribe('/topic/'+_this.category, function (hello) {
        _this.reloadData();
      });
    });
  }

  connectChat(){
    const socket = new SockJS('http://localhost:8080/chat-endpoint');
    this.chatClient = Stomp.over(socket);
 
    const _this = this;
    this.chatClient.connect({}, function (frame) {
      _this.setConnectedChat(true);
      console.log('Connected to chat: ' + frame);
      _this.chatClient.subscribe('/topic/'+_this.category, function (hello) {
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

  disconnectChat(){
    if (this.chatClient != null) {
      this.chatClient.disconnect();
    }
    this.setConnectedChat(false);
    console.log('Disconnected chat!');
  }

  sendMessage() {
    this.chatClient.send(
      '/chat/'+this.category, {},
      localStorage.getItem("name") + ': ' + this.message 
    );
    this.message = "";
  }

  showGreeting(words) {
    this.greetings.push(words);
  }
 
}
