package com.example.projectio.Decorators;

import org.json.simple.JSONArray;

/**
 * Klasa pozwalająca na obsługę listy zmian, które mają zaaplikowane na podanym tekscie
 *
 * @author Dominik
 */


public class MultiDecorator extends Decorator {

    private Decorator decorator;

    public MultiDecorator(Decorator decorator, JSONArray translationsArray) {
        this.decorator = decorator;
        this.translationsArray = translationsArray;
    }

    /**
     * Pole klasy przechowujące listę operacji
     */
    private JSONArray translationsArray;

    public MultiDecorator(String text, JSONArray translationsArray) {
        this.text = text;
        this.translationsArray = translationsArray;
    }

    /**
     * Metoda klasy MultiDecorator pozwalająca zastosowaniu listy translacji
     *
     * @return (String) tekst po zastosowaniu listy translacji
     */

    @Override
    public String decore() {
        if (decorator != null) {
            text = decorator.decore();
        }
        for (Object aTranslationsArray : translationsArray) {
            String actTranslation = (String) aTranslationsArray;
            if (actTranslation.compareTo("lower") == 0) {
                LowerCaseDecorator decorator = new LowerCaseDecorator(text);
                text = decorator.decore();
            } else if (actTranslation.compareTo("upper") == 0) {
                UpperCaseDecorator decorator = new UpperCaseDecorator(text);
                text = decorator.decore();
            } else if (actTranslation.compareTo("capitalize") == 0) {
                CapitalizeDecorator decorator = new CapitalizeDecorator(text);
                text = decorator.decore();
            } else if (actTranslation.compareTo("inverse") == 0) {
                InverseDecorator decorator = new InverseDecorator(text);
                text = decorator.decore();
            } else if (actTranslation.compareTo("expandShortcuts") == 0) {
                ExpandShortcutsDecorator decorator = new ExpandShortcutsDecorator(text);
                text = decorator.decore();
            } else if (actTranslation.compareTo("expandNumbersPl") == 0) {
                ExpandNumbersDecorator decorator = new ExpandNumbersDecorator(text, "pl");
                text = decorator.decore();
            } else if (actTranslation.compareTo("expandNumbersEn") == 0) {
                ExpandNumbersDecorator decorator = new ExpandNumbersDecorator(text, "eng");
                text = decorator.decore();
            } else if (actTranslation.compareTo("expandMyShortcuts") == 0) {
                ExpandMyShortCutsDecorator decorator = new ExpandMyShortCutsDecorator(text);
                text = decorator.decore();
            }
        }
        return text;
    }
}
