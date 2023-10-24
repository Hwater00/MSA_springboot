package com.playdata.firstservvice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/first-service")
@RequiredArgsConstructor
public class FirstserviceController {

    // Environment는 application.yml 파일에 매칭 시켜 놓은 값을  자바 자료로 만들어줌
    private final Environment env;

    @GetMapping("port-check")
    public String portCheck(){
        return env.getProperty("local.server.port");
        // 그냥 server.port로 기입하면 실제로 대입된 값인 0으로 출력
    }

    @RequestMapping(value="hello",method = RequestMethod.GET)
    public String hello(){
        return "Hello, First-service";
    }


    @GetMapping("header-check")
    public String headerCheck(@RequestHeader("fsreqhyml") String header){
        return header;
    }
}
