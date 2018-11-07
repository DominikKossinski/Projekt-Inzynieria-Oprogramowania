package com.example.projectio;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectioApplicationTests {

    @Test
    @DisplayName(value = "First MultiTranslationTest")
    public void acceptGetMultiTranslation() {
        assert (MultiTranslationRestController.getMultiTranslation(
                "Kossa 123 prof.", "[\"upper\", \"expandNumbers\"]")
                .compareTo("KOSSA sto dwadzieścia trzy PROF.") == 0);
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void capitalizeTest(){
        assert (Translator.toCapitalize("Test test").compareTo("Test Test")==0);
    }

}
