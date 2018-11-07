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
    public void numbersTest() {
        assert(Translator.expandNumbers("Szymon to gosc na 102").compareTo("Szymon to gosc na sto dwa") == 0);
    }


}
