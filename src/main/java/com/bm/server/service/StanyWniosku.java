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

    public static boolean nastepstwoStanow(int stanPoprzedniInt, int stanAktualnyInt) {


        // DIAGRAM STANÓW

        StanType stanPoprzedni = StanType.valueOf(stanPoprzedniInt);
        StanType stanAktualny = StanType.valueOf(stanAktualnyInt);

        if (stanPoprzedni == StanType.CREATED) {
            if (stanAktualny == StanType.DELETED || stanAktualny == StanType.CREATED
                    || stanAktualny == StanType.VERIFIED) {
                return true;
            } else
                return false;
        }
        ;

        if (stanPoprzedni == StanType.VERIFIED) {
            if (stanAktualny == StanType.REJECTED || stanAktualny == StanType.ACCEPTED || stanAktualny == StanType.VERIFIED) {
                return true;
            } else
                return false;
        }

        if (stanPoprzedni == StanType.ACCEPTED) {
            if (stanAktualny == StanType.REJECTED || stanAktualny == StanType.PUBLISHED) {
                return true;
            }
        }

        if (stanPoprzedni == StanType.PUBLISHED) {
            return false;
        }

        if (stanPoprzedni == StanType.REJECTED)
            return false;

        if (stanPoprzedni == StanType.DELETED)
            return false;

        return false;


    }


}

