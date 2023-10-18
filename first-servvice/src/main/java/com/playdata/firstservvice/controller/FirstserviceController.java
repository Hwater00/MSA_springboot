package com.playdata.firstservvice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/first-service")
public class FirstserviceController {
    @RequestMapping(value="hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello, First-service";
    }
}
