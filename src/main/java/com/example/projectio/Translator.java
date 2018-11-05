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

}
