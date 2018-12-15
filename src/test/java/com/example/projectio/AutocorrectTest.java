package com.example.projectio;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class AutocorrectTest {

    public Autocorrect auto;

    @BeforeClass
    public static void loadDict() {
        Autocorrect.init("slowa.txt");
    }
    @Before
    public void setUp(){
        auto = new Autocorrect();
    }

    @Test
    public void emptyStringTest() {
        assertEquals("", auto.correctSentence(""));
    }
    @Test
    public void correctWordTest() {
        assertEquals("gżegżółka ", auto.correctSentence("gżegżółka"));
    }

    @Test
    public void incorrectWordTest() {
        assertEquals("gżegżółka ", auto.correctSentence("grzegrzulka"));
    }

    @Test
    public void incorrectSenetenceTest() {
        assertEquals("gżegżółka jest ruda ", auto.correctSentence("grzegrzulka jest ruda"));
    }

    @Test
    public void correctSenetenceTest() {
        assertEquals("kaczka nosi okulary ", auto.correctSentence("kaczka nosi okulary"));
    }

    @Test
    public void nonExistingWordTest() {
        assertEquals("gkdsfkgjsdfhglk ", auto.correctSentence("gkdsfkgjsdfhglk"));
    }
}