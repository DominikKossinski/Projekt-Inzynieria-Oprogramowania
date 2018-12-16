package com.example.projectio;

import com.example.projectio.Decorators.*;
import com.example.projectio.RestControllers.MultiTranslationRestController;
import com.example.projectio.RestControllers.MyShortcutsRestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations = "classpath*:/spring/applicationContext.xml")
public class ProjectioApplicationTests {

    private ExpandNumbersDecorator numbersDecorator;
    private CapitalizeDecorator capitalizeDecorator;
    private InverseDecorator inverseDecorator;
    private DeleteRepeatWordsDecorator deleteDecorator;
    private UpperCaseDecorator upperCaseDecorator;


    @Before
    public void setUp() {
        numbersDecorator = new ExpandNumbersDecorator("17.961", "eng");
        capitalizeDecorator = new CapitalizeDecorator("Test TEST test");
        inverseDecorator = new InverseDecorator("MarIusz Nie UmiE pIsAc");
        deleteDecorator = new DeleteRepeatWordsDecorator("Ide do do sklepu");
        upperCaseDecorator = new UpperCaseDecorator("Cos tam");
    }

    @Test
    public void upperTest() {
        assert (upperCaseDecorator.decore().compareTo("COS TAM") == 0);
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

    @Test
    public void acceptGetMultiTranslation() {
        assert (new MultiTranslationRestController().getMultiTranslation(
                "Kossa 123 prof.", "[\"upper\", \"capitalize\"]")
                .compareTo("Kossa 123 Prof.") == 0);
        assert (new MultiTranslationRestController().getMultiTranslation(
                "Kossa 123 prof.", "[\"upper\", \"expandNumbersPl\"]")
                .compareTo("KOSSA sto dwadzieścia trzy PROF.") == 0);
    }

    @Test
    public void delAndNumTest() {
        deleteDecorator.setText("123 123");
        assert (deleteDecorator.decore().compareTo("123") == 0);
        numbersDecorator.setParameters(deleteDecorator.decore(), "pl");
        assert (numbersDecorator.decore().compareTo("sto dwadzieścia trzy") == 0);
    }

    @Test
    public void capAndInvTest() {
        capitalizeDecorator.setText("abc cba");
        assert (capitalizeDecorator.decore().compareTo("Abc Cba") == 0);
        inverseDecorator.setText(capitalizeDecorator.decore());
        assert (inverseDecorator.decore().compareTo("Abc Cba") == 0);
    }

    @Test
    public void upperAndDelTest() {
        upperCaseDecorator.setText("abc ABC");
        assert (upperCaseDecorator.decore().compareTo("ABC ABC") == 0);
        deleteDecorator.setText(upperCaseDecorator.decore());
        assert (deleteDecorator.decore().compareTo("ABC") == 0);
    }

    @Test
    public void numbersTestEng() {
        assert (numbersDecorator.decore().compareTo("seventeen point nine six") == 0);
        numbersDecorator.setParameters("Szymon to gosc na 102", "eng");
        assert (numbersDecorator.decore().
                compareTo("Szymon to gosc na one hundred two") == 0);
        numbersDecorator.setParameters("pi to około 3.14", "eng");
        assert (numbersDecorator.decore().compareTo("pi to około three point one four") == 0);
        numbersDecorator.setParameters("najlepsze 0.7", "eng");
        assert (numbersDecorator.decore().compareTo("najlepsze zero point seven") == 0);
        numbersDecorator.setParameters("997 ten numer to kłopoty", "eng");
        assert (numbersDecorator.decore().compareTo("nine hundred ninety seven" +
                " ten numer to kłopoty") == 0);
        numbersDecorator.setParameters("Dodam sobie kropke 102. i tyle", "eng");
        assert (numbersDecorator.decore().compareTo("Dodam sobie kropke " +
                "one hundred two i tyle") == 0);

    }

    @Test
    public void numberTestPl() {
        numbersDecorator.setParameters("17.961", "pl");
        assert (numbersDecorator.decore().compareTo("siedemnaście i dziewięćdziesiąt sześć setnych") == 0);
        numbersDecorator.setParameters("Szymon to gosc na 102", "pl");
        assert (numbersDecorator.decore().compareTo("Szymon to gosc na sto dwa") == 0);
        numbersDecorator.setParameters("pi to około 3.14", "pl");
        assert (numbersDecorator.decore().compareTo("pi to około trzy i czternaście setnych") == 0);
        numbersDecorator.setParameters("najlepsze 0.7", "pl");
        assert (numbersDecorator.decore().compareTo("najlepsze zero i siedemdziesiąt setnych") == 0);
        numbersDecorator.setParameters("997 ten numer to kłopoty", "pl");
        assert (numbersDecorator.decore().compareTo("dziewięćset dziewięćdziesiąt siedem" +
                " ten numer to kłopoty") == 0);
        numbersDecorator.setParameters("Dodam sobie kropke 102. i tyle", "pl");
        assert (numbersDecorator.decore().compareTo("Dodam sobie kropke " +
                "sto dwa i tyle") == 0);
    }

    @Test
    public void capitalizeTest() {
        assert (capitalizeDecorator.decore().compareTo("Test Test Test") == 0);
    }

    @Test
    public void inverseTest() {
        assert (inverseDecorator.decore().compareTo("CasIp eiMu eIn ZsuIrAm") == 0);
    }

    @Test
    public void deleteRepeatsTest() {
        assert (deleteDecorator.decore().compareTo("Ide do sklepu") == 0);
        deleteDecorator.setText("Ide Ide do do sklepu sklepu");
        assert (deleteDecorator.decore().compareTo("Ide do sklepu") == 0);
        deleteDecorator.setText("PODWOJNY Mariusz Mariusz");
        assert (deleteDecorator.decore().compareTo("PODWOJNY Mariusz") == 0);
        deleteDecorator.setText("Wczoraj Wczoraj bylem na uczelni, bylo super super");
        assert (deleteDecorator.decore().compareTo("Wczoraj bylem na uczelni, bylo super") == 0);
        deleteDecorator.setText("Nie Nie moge moge sie sie doczekac doczekac kolejnego kolejnego wykladu");
        assert (deleteDecorator.decore().compareTo("Nie moge sie doczekac kolejnego wykladu") == 0);
    }
}
