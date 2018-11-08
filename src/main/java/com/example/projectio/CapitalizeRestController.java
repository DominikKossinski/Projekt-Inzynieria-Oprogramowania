package com.example.projectio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapitalizeRestController {
    @GetMapping("/api/capitalize")
    public String getTextToCapitalize(@RequestParam(name = "text") String text) {
        return Translator.toCapitalize(text);
    }
}
