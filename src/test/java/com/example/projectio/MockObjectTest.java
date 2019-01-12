package com.example.projectio;
import com.example.projectio.RestControllers.*;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MockObjectTest {

        @Before
        public void init() {
            MockitoAnnotations.initMocks(this);
        }

        @Test // 1
        public void InverseController_Test() {
            InverseRestController Decorator = mock(InverseRestController.class);

            when(Decorator.inverseText("Super zabawa")).thenCallRealMethod();
            when(Decorator.getTextFromDecorator("Super zabawa")).thenReturn("Awabaz repus");

            assert(Decorator.inverseText("Super zabawa").compareTo("Awabaz repus") == 0);
        }

        @Test // 2
        public void AutoCorrectController_Test() {
            AutoCorrectRestController Decorator = mock(AutoCorrectRestController.class);

            when(Decorator.getTextToCapitalize("Muj")).thenCallRealMethod();
            when(Decorator.getTextFromDecorator("Muj")).thenReturn("Mój");

            assert(Decorator.getTextToCapitalize("Muj").compareTo("Mój") == 0);
        }

        @Test // 3
        public void CapitalizeRestController_Test() {
            CapitalizeRestController Decorator = mock(CapitalizeRestController.class);

            when(Decorator.getTextToCapitalize("ale jaja")).thenCallRealMethod();
            when(Decorator.getTextFromDecorator("ale jaja")).thenReturn("Ale jaja");

            assert(Decorator.getTextToCapitalize("ale jaja").compareTo("Ale jaja") == 0);
        }

        @Test // 4
        public void DelRepeatWordRestController_Test() {
            DelRepeatWordRestController Decorator = mock(DelRepeatWordRestController.class);

            when(Decorator.getTextWithDelRepeats("bawimy sie sie i super super jest")).thenCallRealMethod();
            when(Decorator.getTextFromDecorator("bawimy sie sie i super super jest")).thenReturn("bawimy sie i super jest");

            assert(Decorator.getTextWithDelRepeats("bawimy sie sie i super super jest").compareTo("bawimy sie i super jest") == 0);
        }

        @Test // 5
        public void ExpandRestController_Test() {
            ExpandRestController Decorator = mock(ExpandRestController.class);

            when(Decorator.getExpandedShortcuts("dr Krzysztof")).thenCallRealMethod();
            when(Decorator.getTextFromDecorator("dr Krzysztof")).thenReturn("doktor Krzysztof");

            assert(Decorator.getExpandedShortcuts("dr Krzysztof").compareTo("doktor Krzysztof") == 0);
        }

        @Test // 6
        public void LowerRestController_Test() {
            LowerRestController Decorator = mock(LowerRestController.class);

            when(Decorator.getTextToLowerCase("SuPER JeST")).thenCallRealMethod();
            when(Decorator.getTextFromDecorator("SuPER JeST")).thenReturn("super jest");

            assert(Decorator.getTextToLowerCase("SuPER JeST").compareTo("super jest") == 0);
        }

        @Test // 7
        public void MyShortcutsRestController_Test() {
            MyShortcutsRestController Decorator = mock(MyShortcutsRestController.class);

            when(Decorator.expandMyShortcuts("sk. - skrót")).thenCallRealMethod();
            when(Decorator.getTextFromDecorator("sk. - skrót")).thenReturn("skrót - skrót");

            assert(Decorator.expandMyShortcuts("sk. - skrót").compareTo("skrót - skrót") == 0);
        }

        @Test // 8
        public void NumberRestController_Test() {
            NumberRestController Decorator = mock(NumberRestController.class);

            when(Decorator.getTextToNumbers("100","pl")).thenCallRealMethod();
            when(Decorator.getTextToNumbers("100","eng")).thenCallRealMethod();

            when(Decorator.getTextFromDecorator("100","pl")).thenReturn("sto");
            when(Decorator.getTextFromDecorator("100","eng")).thenReturn("one hundred");

            assert(Decorator.getTextToNumbers("100","pl").compareTo("sto") == 0);
            assert(Decorator.getTextToNumbers("100","eng").compareTo("one hundred") == 0);
        }

        @Test // 9
        public void UpperRestController_Test() {
            UpperRestController Decorator = mock(UpperRestController.class);

            when(Decorator.getTextToUpperCase("tekst na wielkie litery")).thenCallRealMethod();
            when(Decorator.getTextFromDecorator("tekst na wielkie litery")).thenReturn("TEKST NA WIELKIE LITERY");

            assert(Decorator.getTextToUpperCase("tekst na wielkie litery").compareTo("TEKST NA WIELKIE LITERY") == 0);
        }

        @Test //10
        public void MultiTranslationRestController_Test() {
            MultiTranslationRestController Decorator = mock(MultiTranslationRestController.class);
            try
            {
                JSONParser parser = new JSONParser();
                JSONArray translationsArray = (JSONArray) parser.parse("[\"lower\",\"capitalize\"]");

                when(Decorator.getMultiTranslation("TEKST DO TRANSFORMACJI","[\"lower\",\"capitalize\"]")).thenCallRealMethod();
                when(Decorator.getTextFromDecorator("TEKST DO TRANSFORMACJI",translationsArray)).thenReturn("Tekst do transformacji");

            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }

            assert(Decorator.getMultiTranslation("TEKST DO TRANSFORMACJI","[\"lower\",\"capitalize\"]").compareTo("Tekst do transformacji") == 0);
        }
}



