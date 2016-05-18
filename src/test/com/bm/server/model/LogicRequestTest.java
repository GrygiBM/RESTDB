package com.bm.server.model;

import com.bm.server.service.StateRequest;
import org.junit.Test;

import static com.bm.server.service.LogicRequest.validateData;
import static org.junit.Assert.assertEquals;


public class LogicRequestTest {
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

        w1_CREATED.setStan(StateRequest.StateType.CREATED.getValue());
        w1_CREATED.setTresc("Tresc 1");
        w1_CREATED.setNazwa("Nazwa 1");
        w1_CREATED.setInfo("");

        w1_CREATED_B.setStan(StateRequest.StateType.CREATED.getValue());
        w1_CREATED_B.setTresc("Tresc 1");
        w1_CREATED_B.setNazwa("Nazwa 1");
        w1_CREATED_B.setInfo("");

        w2_VERIFIED.setStan(StateRequest.StateType.VERIFIED.getValue());
        w2_VERIFIED.setTresc("Tresc 1");
        w2_VERIFIED.setNazwa("Nazwa 1");
        w2_VERIFIED.setInfo("");

        w2_VERIFIED_B.setStan(StateRequest.StateType.VERIFIED.getValue());
        w2_VERIFIED_B.setTresc("Tresc 1");
        w2_VERIFIED_B.setNazwa("Nazwa 1");
        w2_VERIFIED_B.setInfo("");

        w3_ACCEPTED.setStan(StateRequest.StateType.ACCEPTED.getValue());
        w3_ACCEPTED.setTresc("Tresc 1");
        w3_ACCEPTED.setNazwa("Nazwa 1");
        w3_ACCEPTED.setInfo("");


        w4_PUBLISHED.setStan(StateRequest.StateType.PUBLISHED.getValue());
        w4_PUBLISHED.setTresc("Tresc 1");
        w4_PUBLISHED.setNazwa("Nazwa 1");
        w4_PUBLISHED.setInfo("Publikowanie");

        w5_DELETED.setStan(StateRequest.StateType.DELETED.getValue());
        w5_DELETED.setTresc("Tresc 1");
        w5_DELETED.setNazwa("Nazwa 1");
        w5_DELETED.setInfo("Kasowanie");

        w6_REJECTED.setStan(StateRequest.StateType.REJECTED.getValue());
        w6_REJECTED.setTresc("Tresc 1");
        w6_REJECTED.setNazwa("Nazwa 1");
        w6_REJECTED.setInfo("Odrzucanie");


        // true tresc

        assertEquals("Poprawnosc danych tresc CREATED 1 ", true, validateData(w1_CREATED, w1_CREATED_B));
        assertEquals("Poprawnosc danych tresc VERIFIED 1 ", true, validateData(w2_VERIFIED, w2_VERIFIED_B));

        w1_CREATED.setTresc("Zmiana tresci");
        w2_VERIFIED.setTresc("Zmiana tresci");

        assertEquals("Poprawnosc danych tresc CREATED 2 ", true, validateData(w1_CREATED, w1_CREATED_B));
        assertEquals("Poprawnosc danych tresc VERIFIED 2 ", true, validateData(w2_VERIFIED, w2_VERIFIED_B));


        // false tresc

        assertEquals("Poprawnosc danych tresc CREATED 3 ", false, validateData(w1_CREATED,w3_ACCEPTED));
        assertEquals("Poprawnosc danych tresc VERIFIED 3 ", false, validateData(w2_VERIFIED,w3_ACCEPTED));


        //

        assertEquals("Poprawnosc danych ACCEPTED ", true, validateData(w3_ACCEPTED, w3_ACCEPTED));
        assertEquals("Poprawnosc danych PUBLISHED ", true, validateData(w3_ACCEPTED, w3_ACCEPTED));


        // Przyczyna usuwania / odrzucania

        assertEquals("Poprawnosc danych Kasowanie ", true, validateData(w5_DELETED, w5_DELETED));
        assertEquals("Poprawnosc danych odrzucanie ", true, validateData(w6_REJECTED, w6_REJECTED));

        w5_DELETED.setInfo("");
        w6_REJECTED.setInfo("");

        assertEquals("Poprawnosc danych Kasowanie ", false, validateData(w5_DELETED, w5_DELETED));
        assertEquals("Poprawnosc danych odrzucanie ", false, validateData(w6_REJECTED, w6_REJECTED));


    }

}