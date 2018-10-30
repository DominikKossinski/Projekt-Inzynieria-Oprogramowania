package com.example.projectio;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpperRestController {

    @GetMapping("/api/upper")
    public String getTextToUpperCase(@RequestParam(name = "text") String text) {
        return Translator.toUpperCase(text);
    }

}
