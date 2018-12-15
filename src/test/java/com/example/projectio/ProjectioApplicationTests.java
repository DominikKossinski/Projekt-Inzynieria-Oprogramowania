package com.example.projectio;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = "classpath*:/spring/applicationContext.xml")
public class ProjectioApplicationTests {


    @Test
    public void myShortcutsRestControllerTest() {
        MyShortcutsRestController restController = new MyShortcutsRestController();
        restController.deleteMyShortcut("DK");
        assert (restController.createMyShortCut(false, "DK;Dominik").compareTo("True") == 0);
        assert (restController.expandMyShortcuts("DKDK").compareTo("DominikDominik") == 0);
        assert (restController.createMyShortCut(false, "DK;ok").compareTo("Waring REPLACE!") == 0);
        assert (restController.createMyShortCut(true, "DK;ok").compareTo("True replace") == 0);
        assert (restController.expandMyShortcuts("DK").compareTo("ok") == 0);
        assert (restController.deleteMyShortcut("DK").compareTo("True") == 0);
        assert (restController.deleteMyShortcut("DK").compareTo("NO SHORTCUT DK FOUND") == 0);
    }

    @DisplayName(value = "First MultiTranslationTest")
    public void acceptGetMultiTranslation() {
        assert (MultiTranslationRestController.getMultiTranslation(
                "Kossa 123 prof.", "[\"upper\", \"capitalize\"]")
                .compareTo("Kossa 123 Prof.") == 0);
        assert (MultiTranslationRestController.getMultiTranslation(
                "Kossa 123 prof.", "[\"upper\", \"expandNumbers\"]")
                .compareTo("KOSSA sto dwadzieścia trzy PROF.") == 0);
    }


    @Test
    public void contextLoads() {
    }
    @Test
    public void numbersTest() {
        assert(Translator.expandNumbers("17.961","eng").compareTo("seventeen point nine six") == 0);
        assert(Translator.expandNumbers("Szymon to gosc na 102","eng").compareTo("Szymon to gosc na one hundred two") == 0);
        assert(Translator.expandNumbers("pi to około 3.14","eng").compareTo("pi to około three point one four") == 0);
        assert(Translator.expandNumbers("najlepsze 0.7","eng").compareTo("najlepsze zero point seven") == 0);
        assert(Translator.expandNumbers("997 ten numer to kłopoty","eng").compareTo("nine hundred ninety seven" +
                " ten numer to kłopoty") == 0);
        assert(Translator.expandNumbers("Dodam sobie kropke 102. i tyle","eng").compareTo("Dodam sobie kropke " +
                "one hundred two i tyle") == 0);

        assert(Translator.expandNumbers("17.961","pl").compareTo("siedemnaście i dziewięćdziesiąt sześć setnych") == 0);
        assert(Translator.expandNumbers("Szymon to gosc na 102","pl").compareTo("Szymon to gosc na sto dwa") == 0);
        assert(Translator.expandNumbers("pi to około 3.14","pl").compareTo("pi to około trzy i czternaście setnych") == 0);
        assert(Translator.expandNumbers("najlepsze 0.7","pl").compareTo("najlepsze zero i siedemdziesiąt setnych") == 0);
        assert(Translator.expandNumbers("997 ten numer to kłopoty","pl").compareTo("dziewięćset dziewięćdziesiąt siedem" +
                " ten numer to kłopoty") == 0);
        assert(Translator.expandNumbers("Dodam sobie kropke 102. i tyle","pl").compareTo("Dodam sobie kropke " +
                "sto dwa i tyle") == 0);
}
    @Test
    public void capitalizeTest(){
        assert (Translator.toCapitalize("Test TEST test").compareTo("Test Test Test")==0);
    }

    @Test
    public void inverseTest() {
        assert (InverseRestController.inverseText("MarIusz Nie UmiE pIsAc").compareTo("CasIp eiMu eIn ZsuIrAm") == 0);
    }

    @Test
    public void deleteRepeatsTest() {
        assert (Translator.delRepeatWords("Ide do do sklepu").compareTo("Ide do sklepu") == 0);
        assert (Translator.delRepeatWords("Ide Ide do do sklepu sklepu").compareTo("Ide do sklepu") == 0);
        assert (Translator.delRepeatWords("PODWOJNY Mariusz Mariusz").compareTo("PODWOJNY Mariusz") == 0);
        assert (Translator.delRepeatWords("Wczoraj Wczoraj bylem na uczelni, bylo super super").compareTo("Wczoraj bylem na uczelni, bylo super") == 0);
        assert (Translator.delRepeatWords("Nie Nie moge moge sie sie doczekac doczekac kolejnego kolejnego wykladu").compareTo("Nie moge sie doczekac kolejnego wykladu") == 0);
    }
}
