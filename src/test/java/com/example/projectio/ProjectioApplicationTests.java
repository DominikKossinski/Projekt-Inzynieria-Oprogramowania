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
    public void myShortCutsExpandTest() {
        MyShortcutsRestController restController = new MyShortcutsRestController();
        restController.deleteMyShortcut("DK");
        assert (restController.createMyShortCut(false, "DK;Dominik").compareTo("True") == 0);
        assert (new Translator().expandMyShortcuts("DK mDKabc").compareTo("Dominik mDominikabc") == 0);
        assert (restController.deleteMyShortcut("DK").compareTo("True") == 0);
        assert (restController.deleteMyShortcut("DK").compareTo("NO SHORTCUT DK FOUND") == 0);
    }

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
        assert(Translator.expandNumbers("Szymon to gosc na 102").compareTo("Szymon to gosc na sto dwa") == 0);
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
