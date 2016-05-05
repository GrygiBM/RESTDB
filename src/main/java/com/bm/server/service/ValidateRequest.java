package com.bm.server.service;

import com.bm.server.model.Wniosek;

public class ValidateRequest {

    public static boolean Validate(Wniosek wniosekBaza, Wniosek wniosekRest)
    {
        if (!StateRequest.sequentState(wniosekBaza.getStan(), wniosekRest.getStan()))
            return false;

        if (!LogicRequest.validateData(wniosekBaza, wniosekRest))
            return false;

        return true;

    }


}

