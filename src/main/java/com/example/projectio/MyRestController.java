package com.example.projectio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

    @RequestMapping("/")
    public String getHello() {
        return "Hello world";
    }
}
