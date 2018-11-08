package com.example.projectio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Przykładowy RestController
 */
@RestController
public class MyRestController {

    /**
     * Przykładowa metoda
     *
     * @return - Hello World
     */
    @RequestMapping("/")
    public String getHello() {
        return "Hello world";
    }
}
