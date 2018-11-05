package com.example.projectio;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpandRestController {

    @RequestMapping("/api/expandShortcuts")
    public String getExpandedShortcuts(@RequestParam(name = "text") String text) {
        return Translator.expandShortcuts(text);
    }
}
