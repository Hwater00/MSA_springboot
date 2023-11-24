package com.example.fetchserver.controller;

import com.example.fetchserver.domian.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("persons")
    public ResponseEntity<?> getPersonInfo(){
        List<Person> personList = new ArrayList<>();

        Person p1 = new Person("ghost",25,true);
        Person p2 = new Person("pikachu",25,false);

        personList.add(p1);
        personList.add(p2);

        return ResponseEntity.ok(personList);
    }
}