package com.example.projectio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SimpleController {

    @GetMapping("/simple")
    public String getSimplePage() {
        return "simplePage";
    }
}
