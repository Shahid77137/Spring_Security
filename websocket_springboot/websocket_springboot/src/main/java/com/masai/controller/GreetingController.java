package com.masai.controller;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.masai.model.Greeting;
import com.masai.model.HelloMessage;

@Controller
public class GreetingController {


  @MessageMapping("/hello") //app/hello
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message) throws Exception {
    Thread.sleep(1000); // simulated delay
    return new Greeting("Hello, "+message.getName());
  }

}