package com.bm.server.model;

import com.bm.server.service.StanyWniosku;
import org.junit.Test;

import static com.bm.server.service.MerytorykaWniosku.sprawdzPoprawnoscDanych;
import static org.junit.Assert.assertEquals;


public class MerytorykaWnioskuTest {
    @Test
    public void sprawdzPoprawnoscDanychTest() throws Exception {


        Wniosek w1_CREATED = new Wniosek();
        Wniosek w1_CREATED_B = new Wniosek();

        Wniosek w2_VERIFIED = new Wniosek();
        Wniosek w2_VERIFIED_B = new Wniosek();

        Wniosek w3_ACCEPTED = new Wniosek();
        Wniosek w4_PUBLISHED = new Wniosek();
        Wniosek w5_DELETED = new Wniosek();
        Wniosek w6_REJECTED = new Wniosek();

        w1_CREATED.setStan(StanyWniosku.StanType.CREATED.getValue());
        w1_CREATED.setTresc("Tresc 1");
        w1_CREATED.setNazwa("Nazwa 1");
        w1_CREATED.setInfo("");

        w1_CREATED_B.setStan(StanyWniosku.StanType.CREATED.getValue());
        w1_CREATED_B.setTresc("Tresc 1");
        w1_CREATED_B.setNazwa("Nazwa 1");
        w1_CREATED_B.setInfo("");

        w2_VERIFIED.setStan(StanyWniosku.StanType.VERIFIED.getValue());
        w2_VERIFIED.setTresc("Tresc 1");
        w2_VERIFIED.setNazwa("Nazwa 1");
        w2_VERIFIED.setInfo("");

        w2_VERIFIED_B.setStan(StanyWniosku.StanType.VERIFIED.getValue());
        w2_VERIFIED_B.setTresc("Tresc 1");
        w2_VERIFIED_B.setNazwa("Nazwa 1");
        w2_VERIFIED_B.setInfo("");

        w3_ACCEPTED.setStan(StanyWniosku.StanType.ACCEPTED.getValue());
        w3_ACCEPTED.setTresc("Tresc 1");
        w3_ACCEPTED.setNazwa("Nazwa 1");
        w3_ACCEPTED.setInfo("");


        w4_PUBLISHED.setStan(StanyWniosku.StanType.PUBLISHED.getValue());
        w4_PUBLISHED.setTresc("Tresc 1");
        w4_PUBLISHED.setNazwa("Nazwa 1");
        w4_PUBLISHED.setInfo("Publikowanie");

        w5_DELETED.setStan(StanyWniosku.StanType.DELETED.getValue());
        w5_DELETED.setTresc("Tresc 1");
        w5_DELETED.setNazwa("Nazwa 1");
        w5_DELETED.setInfo("Kasowanie");

        w6_REJECTED.setStan(StanyWniosku.StanType.REJECTED.getValue());
        w6_REJECTED.setTresc("Tresc 1");
        w6_REJECTED.setNazwa("Nazwa 1");
        w6_REJECTED.setInfo("Odrzucanie");


        // true tresc

        assertEquals("Poprawnosc danych tresc CREATED 1 ", true, sprawdzPoprawnoscDanych(w1_CREATED, w1_CREATED_B));
        assertEquals("Poprawnosc danych tresc VERIFIED 1 ", true, sprawdzPoprawnoscDanych(w2_VERIFIED, w2_VERIFIED_B));

        w1_CREATED.setTresc("Zmiana tresci");
        w2_VERIFIED.setTresc("Zmiana tresci");

        assertEquals("Poprawnosc danych tresc CREATED 2 ", true, sprawdzPoprawnoscDanych(w1_CREATED, w1_CREATED_B));
        assertEquals("Poprawnosc danych tresc VERIFIED 2 ", true, sprawdzPoprawnoscDanych(w2_VERIFIED, w2_VERIFIED_B));


        // false tresc

        assertEquals("Poprawnosc danych tresc CREATED 3 ", false, sprawdzPoprawnoscDanych(w1_CREATED,w3_ACCEPTED));
        assertEquals("Poprawnosc danych tresc VERIFIED 3 ", false, sprawdzPoprawnoscDanych(w2_VERIFIED,w3_ACCEPTED));


        //

        assertEquals("Poprawnosc danych ACCEPTED ", true, sprawdzPoprawnoscDanych(w3_ACCEPTED, w3_ACCEPTED));
        assertEquals("Poprawnosc danych PUBLISHED ", true, sprawdzPoprawnoscDanych(w3_ACCEPTED, w3_ACCEPTED));


        // Przyczyna usuwania / odrzucania

        assertEquals("Poprawnosc danych Kasowanie ", true, sprawdzPoprawnoscDanych(w5_DELETED, w5_DELETED));
        assertEquals("Poprawnosc danych odrzucanie ", true, sprawdzPoprawnoscDanych(w6_REJECTED, w6_REJECTED));

        w5_DELETED.setInfo("");
        w6_REJECTED.setInfo("");

        assertEquals("Poprawnosc danych Kasowanie ", false, sprawdzPoprawnoscDanych(w5_DELETED, w5_DELETED));
        assertEquals("Poprawnosc danych odrzucanie ", false, sprawdzPoprawnoscDanych(w6_REJECTED, w6_REJECTED));


    }

}