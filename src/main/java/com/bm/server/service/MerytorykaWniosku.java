package com.bm.server.service;


import com.bm.server.model.Wniosek;

public class MerytorykaWniosku {

    public static boolean sprawdzPoprawnoscDanych(Wniosek wniosekWejsciowy, Wniosek wniosekAktualny) {
        int stan = wniosekWejsciowy.getStan();

        // Zmiana tresci
        if (!wniosekWejsciowy.getTresc().equals(wniosekAktualny.getTresc())) {

            if (stan == StanyWniosku.StanType.CREATED.getValue()
                    ||
                    stan == StanyWniosku.StanType.VERIFIED.getValue())
                return true;
            else
                return false;
        }

        // Przyczyna usuwania / odrzucania
        if (wniosekWejsciowy.getInfo().equals("")) {

            if (stan == StanyWniosku.StanType.REJECTED.getValue()
                    ||
                    stan == StanyWniosku.StanType.DELETED.getValue())
                return false;
            else
                return true;
        }

        // Unikalny numer
        if (stan == StanyWniosku.StanType.ACCEPTED.getValue())
        {
            wniosekWejsciowy.setNumer(wniosekAktualny.getId());
        }

        return true;
    }
}
