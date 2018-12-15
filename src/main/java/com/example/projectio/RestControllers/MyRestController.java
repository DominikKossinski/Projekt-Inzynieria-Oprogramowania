package com.example.projectio.RestControllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController odpowiadający za obsługę strony głównej
 *
 * @author Dominik
 */

@RestController
public class MyRestController {

    /**
     * Przykładowa metoda zwracająca Hello World
     *
     * @return - Hello World
     */
    @RequestMapping("/")
    public String getHello() {
        return "Hello world";
    }
}
