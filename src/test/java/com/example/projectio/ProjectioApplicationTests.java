package com.example.projectio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectioApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void capitalizeTest(){
        assert (Translator.toCapitalize("Test test").compareTo("Test Test")==0);
    }

}
