package com.bm.server.service;

import com.bm.server.model.Wniosek;
import org.junit.Test;

import static com.bm.server.service.ValidateRequest.Validate;
import static org.junit.Assert.assertEquals;

public class ValidateRequestTest {
    @Test
    public void validate() throws Exception {

        Wniosek w1_CREATED = new Wniosek();
        Wniosek w1_CREATED_B = new Wniosek();

        Wniosek w2_VERIFIED = new Wniosek();
        Wniosek w2_VERIFIED_B = new Wniosek();


        w1_CREATED.setStan(StateRequest.StateType.CREATED.getValue());
        w1_CREATED.setTresc("Tresc 1");
        w1_CREATED.setNazwa("Nazwa 1");
        w1_CREATED.setInfo("");

        w1_CREATED_B.setStan(StateRequest.StateType.CREATED.getValue());
        w1_CREATED_B.setTresc("Tresc 2");
        w1_CREATED_B.setNazwa("Nazwa 1");
        w1_CREATED_B.setInfo("");

        w2_VERIFIED.setStan(StateRequest.StateType.VERIFIED.getValue());
        w2_VERIFIED.setTresc("Tresc 1");
        w2_VERIFIED.setNazwa("Nazwa 1");
        w2_VERIFIED.setInfo("");

        w2_VERIFIED_B.setStan(StateRequest.StateType.VERIFIED.getValue());
        w2_VERIFIED_B.setTresc("Tresc 2");
        w2_VERIFIED_B.setNazwa("Nazwa 1");
        w2_VERIFIED_B.setInfo("");

        // true tresc

        assertEquals("Poprawnosc danych i stanów  CREATED 1 ", true, Validate(w1_CREATED, w1_CREATED_B));
        assertEquals("Poprawnosc danych tresc VERIFIED 1 ", true, Validate(w2_VERIFIED, w2_VERIFIED_B));

        assertEquals("Poprawnosc danych tresc CREATED 2 ", true, Validate(w1_CREATED, w2_VERIFIED));
        assertEquals("Poprawnosc danych tresc VERIFIED 2 ", false, Validate(w2_VERIFIED, w1_CREATED));

    }

}