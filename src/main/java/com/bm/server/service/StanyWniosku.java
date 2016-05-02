package com.bm.server.service;

import java.util.HashMap;
import java.util.Map;

public class StanyWniosku {

    public enum StanType {
        CREATED(1),
        VERIFIED(2),
        ACCEPTED(3),
        PUBLISHED(4),
        REJECTED(5),
        DELETED(6);

        private int value;
        private static Map map = new HashMap<>();

        private StanType(int value) {
            this.value = value;
        }

        static {
            for (StanType stanType : StanType.values()) {
                map.put(stanType.value, stanType);
            }
        }

        public static StanType valueOf(int stanType) {
            return (StanType) map.get(stanType);
        }

        public int getValue() {
            return value;
        }
    }

    public static boolean nastepstwoStanow(int stanPoprzedniBazyInt, int stanAktualnyRestInt) {


        // DIAGRAM STANÓW

        StanType stanBazy = StanType.valueOf(stanPoprzedniBazyInt);
        StanType stanREST = StanType.valueOf(stanAktualnyRestInt);

        if (stanBazy == StanType.CREATED) {
            if (stanREST == StanType.DELETED || stanREST == StanType.CREATED
                    || stanREST == StanType.VERIFIED) {
                return true;
            } else
                return false;
        }
        ;

        if (stanBazy == StanType.VERIFIED) {
            if (stanREST == StanType.REJECTED || stanREST == StanType.ACCEPTED || stanREST == StanType.VERIFIED) {
                return true;
            } else
                return false;
        }

        if (stanBazy == StanType.ACCEPTED) {
            if (stanREST == StanType.REJECTED || stanREST == StanType.PUBLISHED) {
                return true;
            }
            else
                return false;
        }

        if (stanBazy == StanType.PUBLISHED) {
            return false;
        }

        if (stanBazy == StanType.REJECTED)
            return false;

        if (stanBazy == StanType.DELETED)
            return false;

        return false;


    }


}

