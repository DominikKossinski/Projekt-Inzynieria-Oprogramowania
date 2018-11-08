package com.example.projectio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InverseRestController {
    @GetMapping("/api/inverse")
    public String getTextToUpperCase(@RequestParam(name = "text") String text) {
        return Translator.toInverse(text);
    }
}
