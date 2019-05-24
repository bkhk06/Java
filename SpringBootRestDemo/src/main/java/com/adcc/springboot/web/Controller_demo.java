package com.adcc.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller_demo {
    @RequestMapping("/hello")
    public String hello() {
        return "Hello Spring Boot!ZZZZZZZZ";
    }
}
