/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.controller;

import com.example.cassandra.cassandra.model.Hello;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


/**
 *
 * @author LENOVO
 */
@Controller
public class WebSocketController {
    @MessageMapping("/hello")
	@SendTo("/topic/hi")
	public Hello greeting1(String message) throws Exception {
		return new Hello(message);
	}
}
