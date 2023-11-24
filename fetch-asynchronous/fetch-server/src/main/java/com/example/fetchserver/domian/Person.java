package com.example.fetchserver.domian;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
    private String name;
    private int age;
    private boolean gender; // true 여성, false 남성
}
