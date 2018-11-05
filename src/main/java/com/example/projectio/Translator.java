package com.example.projectio;

import static java.lang.Character.isUpperCase;

public class Translator {

    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }

    public static String toUpperCase(String text) {return text.toUpperCase(); }

    public static String toInverse(String text){
        int [] letterSize = new int[text.length()];
        String newText = "";
        String inversed = "";
        for(int i = 0; i < text.length(); i++){
            if(isUpperCase(text.charAt(i))){
                letterSize[i] = 1;
            }
            else
                letterSize[i] = 0;
            newText = text.charAt(i) + newText;
        }
        for(int i = 0; i < text.length(); i++){
            if(letterSize[i] == 1)
                inversed += Character.toUpperCase(newText.charAt(i));
            else
                inversed += Character.toLowerCase(newText.charAt(i));
        }
        return inversed;
    }

    public static String toCapitalize(String text)
    {
        StringBuilder result = new StringBuilder(text.length());
        String words[] = text.split("\\ ");
        for (int i = 0; i < words.length; i++)
            result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");

        return result.toString();
    }

    public static String expandShortcuts(String text) {
        text = text.replace("Dr ", "Doktor ");
        text = text.replace("dr ", "doktor ");

        text = text.replace("Prof.", "Profesor");
        text = text.replace("prof.", "profesor");

        text = text.replace("mgr ", "magister ");
        text = text.replace("Mgr ", "Magister ");

        text = text.replace("inż.", "inżynier");
        text = text.replace("Inż.", "Inżynier");

        text = text.replace("hab.", "habilitowany");
        text = text.replace("Hab.", "Habilitowany");


        return text;

    }
}
