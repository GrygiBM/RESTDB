package com.bm.server.service;


import com.bm.server.model.Wniosek;

public class MerytorykaWniosku {

    public static boolean sprawdzPoprawnoscDanych(Wniosek wniosekBaza, Wniosek wniosekRest) {

        int stan = wniosekRest.getStan();

        // Zmiana nazwy niedozwolona ?
//        if (!wniosekRest.getNazwa().equals(wniosekBaza.getNazwa())) {
//                return false;
//        }


        // Zmiana tresci
        if (!wniosekRest.getTresc().equals(wniosekBaza.getTresc())) {

            if (stan == StanyWniosku.StanType.CREATED.getValue()
                    ||
                    stan == StanyWniosku.StanType.VERIFIED.getValue())
                return true;
            else
                return false;
        }

        // Przyczyna usuwania / odrzucania
        if (wniosekRest.getInfo().equals("")) {

            if (stan == StanyWniosku.StanType.REJECTED.getValue()
                    ||
                    stan == StanyWniosku.StanType.DELETED.getValue())
                return false;
            else
                return true;
        }

        // Unikalny numer
        if (stan == StanyWniosku.StanType.PUBLISHED.getValue())
        {
            wniosekRest.setNumer(wniosekBaza.getId());
        }

        return true;
    }
}
