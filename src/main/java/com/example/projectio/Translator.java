package com.example.projectio;

import static java.lang.Character.isUpperCase;

public class Translator {

    public static String toLowerCase(String text) {
        return text.toLowerCase();
    }

    public static String toUpperCase(String text) {return text.toUpperCase(); }

    /**
     * Metoda służąca do obracania tekstu przekazanego jako parametr
     * z zachowaniem wielkości znaków na odpowiednich pozycjach
     *
     * @param text - (String) tekst do obrócenia
     * @return (String) tekst po obróceniu
     */

    public static String toInverse(String text){
        int [] letterSize = new int[text.length()];
        String newText = "";
        String inversed = "";
        for(int i = 0; i < text.length(); i++){ // obracanie tekstu oraz zapisywanie wielkości liter
            if(isUpperCase(text.charAt(i))){
                letterSize[i] = 1;
            }
            else
                letterSize[i] = 0;
            newText = text.charAt(i) + newText;
        }
        for(int i = 0; i < text.length(); i++){ // powiększanie oraz pomniejszanie właściwych liter
            if(letterSize[i] == 1)
                inversed += Character.toUpperCase(newText.charAt(i));
            else
                inversed += Character.toLowerCase(newText.charAt(i));
        }
        return inversed;
    }

    public static String toCapitalize(String text)
    {
        text = text.toLowerCase();

        StringBuilder result = new StringBuilder(text.length());
        String words[] = text.split("\\ ");
        for (int i = 0; i < words.length; i++)
            result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1)).append(" ");

        return result.toString().substring(0,result.length()-1);
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

    public static String expandNumbers(String zdanie) {
        String[] arr = zdanie.split(" ", 0);

        String x = "";
        int i = 0;
        for (String w : arr) {

            try{
                if(Integer.valueOf(w) >=0 && Integer.valueOf(w) < 1000) {
                    x += " " + change_on_word_pol(w);
                }
                else x += " " + w;
            }
            catch (NumberFormatException e){
                if(i == 0) {
                    x += w;
                }
                else{
                    x += " " + w;
                }
            }

            i++;
        }
        return x;
    }

    private static final String[] FIRST = new String[]{"zero" , "jeden", "dwa", "trzy", "cztery", "pięć", "sześć", "siedem", "osiem", "dziewięć"};
    private static final String[] SECOND = new String[] {"dziesięć", "jedenaście", "dwanaście", "trzynaście", "czternaście", "piętnaście",
            "szesnaście", "siedemnaście", "osiemnaście", "dziewiętnaście"};
    private static final String[] OTHERS = {"dwadzieścia", "trzydzieści", "czterdzieści", "pięćdziesiąt", "sześćdziesiąt",
            "siedemdziesiąt", "osiemdziesiąt", "dziewięćdziesiąt"};
    private static final String[] HUNDRETS = {"sto", "dwieście", "trzysta", "czterysta", "pięćset", "sześćset", "siedemset",
            "osiemset", "dziewięćset"};

    private static String change_on_word_pol(String w) {

        String x = "";
        int number = Integer.valueOf(w);

        if (number<10){
            x += FIRST[number];
            return x;
        }

        else if (number<20){

            x+= SECOND[number-10];
            return x;
        }

        else if (number%10==0 && (String.valueOf(number).length())<3){
            x+= OTHERS[Integer.valueOf((number/10)-2)];
            return x;
        }

        else if (number%10!=0 && (String.valueOf(number).length())<3){
            x+= OTHERS[Integer.valueOf((number/10)-2)];
            x+=" "+ FIRST[Integer.valueOf((number%10))];
            return x;
        }

        else if (number%10==0 && String.valueOf(number).length() == 3 && number% 100 == 0){

            x+= HUNDRETS[Integer.valueOf((number/100)-1)];   //pięćset itp.
            return x;
        }

        else {
            x += HUNDRETS[Integer.valueOf((number/100)-1)];

            if(number%10 == 0){
                x += " " + OTHERS[Integer.valueOf((number%100)/10-2)];
                return x;
            }

            else if (Integer.valueOf(number/10)%10 == 0){
                x += " " + change_on_word_pol(String.valueOf(number%10));
                return x;
            }

            else if(Integer.valueOf(number/10)%10 == 1){
                x+=" " + change_on_word_pol(String.valueOf(number%100));
                return x;
            }

            else{
                x += " " + OTHERS[(number/10)%10-2];
                x += " " + change_on_word_pol(String.valueOf(number%10));
                return x;
            }
        }
    }
}
