package com.example.projectio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LowerRestController {

    /**
     * Przykładowy url http://localhost:8080/api/lower?text=Dominik
     *
     * @param text
     * @return
     */
    @GetMapping("/api/lower")
    public String getTextToLowerCase(@RequestParam(name = "text") String text) {
        return Translator.toLowerCase(text);
    }
}
