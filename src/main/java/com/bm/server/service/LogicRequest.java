package com.bm.server.service;


import com.bm.server.model.Wniosek;

public class LogicRequest {

    public static boolean validateData(Wniosek wniosekBaza, Wniosek wniosekRest) {

        int stan = wniosekRest.getStan();

        // Zmiana nazwy niedozwolona ?
//        if (!wniosekRest.getNazwa().equals(wniosekBaza.getNazwa())) {
//                return false;
//        }


        // Zmiana tresci
        if (!wniosekRest.getTresc().equals(wniosekBaza.getTresc())) {

            if (stan == StateRequest.StateType.CREATED.getValue()
                    ||
                    stan == StateRequest.StateType.VERIFIED.getValue())
                return true;
            else
                return false;
        }

        // Przyczyna usuwania / odrzucania
        if (wniosekRest.getInfo().equals("")) {

            if (stan == StateRequest.StateType.REJECTED.getValue()
                    ||
                    stan == StateRequest.StateType.DELETED.getValue())
                return false;
            else
                return true;
        }

        // Unikalny numer
        if (stan == StateRequest.StateType.PUBLISHED.getValue())
        {
            wniosekRest.setNumer(wniosekBaza.getId());
        }

        return true;
    }
}
