package com.playdata.secondservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("second-service") //스프링 5버전부터 /seccond-service에서  '/' 생략 가능
public class SecondserviceController {
    @GetMapping("hello")
    public String hello(){
        return "Hello, second-service";
    }
}
