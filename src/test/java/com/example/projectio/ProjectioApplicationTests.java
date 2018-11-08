package com.example.projectio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectioApplicationTests {

    @Test
    public void myShortCutsExpandTest() {
        MyShortcutsRestController restController = new MyShortcutsRestController();
        restController.deleteMyShortcut("DK");
        assert (restController.createMyShortCut(false, "DK;Dominik").compareTo("True") == 0);
        assert (Translator.expandMyShortcuts("DK mDKabc").compareTo("Dominik mDominikabc") == 0);
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

    @Test
    public void contextLoads() {
    }

}
