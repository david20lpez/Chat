/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cassandra.cassandra.controller;

import com.example.cassandra.cassandra.model.Message;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LENOVO
 */
public class WebSocketControllerTest {
    
    private WebSocketController socketController;
    
    public WebSocketControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        socketController = new WebSocketController();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of greeting method, of class WebSocketController.
     */
    @Test
    public void testGreeting() throws Exception {
        String currentTime = new SimpleDateFormat("HH:mm:ss ").format(new Date());
        String message = "dnsknk";
        Message result = socketController.greeting(message);
        assertEquals(currentTime + " - " + message, result.getContent());
    }
    
}
