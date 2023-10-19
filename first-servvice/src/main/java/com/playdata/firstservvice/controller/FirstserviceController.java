package com.playdata.firstservvice.controller;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/first-service")
public class FirstserviceController {
    @RequestMapping(value="hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello, First-service";
    }


    @GetMapping("header-check")
    public String headerCheck(@RequestHeader("fsreqh") String header){
        return header;
    }
}
