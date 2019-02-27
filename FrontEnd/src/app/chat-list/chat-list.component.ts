import { Component, OnInit, Input } from '@angular/core';
import { User } from '../user';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

@Component({
  selector: 'chat-list',
  templateUrl: './chat-list.component.html',
  styleUrls: ['./chat-list.component.css']
})
export class ChatListComponent implements OnInit {

  @Input() user: User;

  constructor() { 
    this.id = localStorage.getItem("id");
  }

  id: string;
  id1: string;
  equalId: boolean = false;
  disabled: boolean;
  visitorClient = null;

  ngOnInit() {
    this.connectVisitor();
  }

  showId() {
    localStorage.setItem("userId", JSON.stringify(this.user.id));
    console.log(localStorage.getItem("userId"));
    console.log("wdadaefaw" + this.id);
    this.sendResponse();
    this.sendLocal();
    this.sendVisitor();
  }

  setConnected(connected: boolean) {
    this.disabled = !connected;
  }

  connectVisitor() {
    const socket = new SockJS('http://localhost:8080/chat-endpoint');
    this.visitorClient = Stomp.over(socket);
 
    const _this = this;
    this.visitorClient.connect({}, function (frame) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);

      _this.visitorClient.subscribe('/topic/id', function (hello) {
      });
    });
  }

  sendVisitor(){
    this.visitorClient.send(
      '/chat/get/id',
      {},
      localStorage.getItem("userId")
    ) 
  }

  sendLocal(){
    this.visitorClient.send(
      '/chat/get/local',
      {},
      this.id
    )
  }

  sendResponse(){
    this.visitorClient.send(
      '/chat/ids',
      {},
      "response"
    )
  }

}

