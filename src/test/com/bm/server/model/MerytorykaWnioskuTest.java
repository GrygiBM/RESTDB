package com.bm.server.model;

import org.junit.Test;

import static com.bm.server.model.MerytorykaWniosku.sprawdzPoprawnoscDanych;
import static org.junit.Assert.assertEquals;

/**
 * Created by KAKE on 2016-04-30.
 */
public class MerytorykaWnioskuTest {
    @Test
    public void sprawdzPoprawnoscDanychTest() throws Exception {

        Wniosek w1 = new Wniosek();
        Wniosek w2 = new Wniosek();

        w1.setTresc("");
        w2.setTresc("TES");

        w1.setNazwa("Nazwa1");
        w2.setNazwa("Nazwa2");

        assertEquals("Poprawnosc danych 1 ", false, sprawdzPoprawnoscDanych(w1, w2));
        assertEquals("Poprawnosc danych 2 ", false, sprawdzPoprawnoscDanych(w2, w1));
    }

}