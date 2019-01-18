/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.controller;

import com.example.cassandra.cassandra.model.Hello;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Hello greeting(String message) throws Exception {
        String currentTime = new SimpleDateFormat("HH:mm:ss ").format(new Date());
	return new Hello(currentTime + " - " + message);
    }
        
}
