package com.example.projectio;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiTranslationRestController {

    @RequestMapping(value = "/api/multiTranslation")
    public String getMultiTranslation(@RequestParam(name = "text") String text, @RequestParam(name = "translations") String jsonText) {
        JSONParser parser = new JSONParser();
        try {
            JSONArray translationsArray = (JSONArray) parser.parse(jsonText);
            //TODO dodać przetwarzenie skrótów
            for (int i = 0; i < translationsArray.size(); i++) {
                String actTranslation = (String) translationsArray.get(i);
                if (actTranslation.compareTo("lower") == 0) {
                    text = Translator.toLowerCase(text);
                } else if (actTranslation.compareTo("upper") == 0) {
                    text = Translator.toUpperCase(text);
                } else if (actTranslation.compareTo("capitalize") == 0) {
                    //TODO dodać capitalize
                    //text = Translator.toCapitalize(text);
                } else if (actTranslation.compareTo("inverse") == 0) {
                    //TODO dodać capitalize
                    //text = Translator.inverse(text);
                }

                return text;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "ERROR";
        }
        return "ERROR";
    }
}
