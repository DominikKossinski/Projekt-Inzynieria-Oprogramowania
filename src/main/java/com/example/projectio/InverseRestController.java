package com.example.projectio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class InverseRestController {

    private static final Logger logger = LoggerFactory.getLogger(InverseRestController.class);

    @GetMapping("/api/inverse")
    public String getTextToUpperCase(@RequestParam(name = "text") String text) {

        logger.info("Rozpoczynam translację metodą inverse");

        return Translator.toInverse(text);
    }
}
