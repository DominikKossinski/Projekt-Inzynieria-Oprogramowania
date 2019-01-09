package com.example.projectio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TextTransformerController {

    @GetMapping("/textTransformer")
    public String getTextTransformer() {
        return "textTransformer";
    }
}
