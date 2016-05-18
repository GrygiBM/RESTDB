package com.bm.server.service;

import java.util.HashMap;
import java.util.Map;

public class StateRequest {

    public enum StateType {
        CREATED(1),
        VERIFIED(2),
        ACCEPTED(3),
        PUBLISHED(4),
        REJECTED(5),
        DELETED(6);

        private int value;
        private static Map map = new HashMap<>();

        private StateType(int value) {
            this.value = value;
        }

        static {
            for (StateType stateType : StateType.values()) {
                map.put(stateType.value, stateType);
            }
        }

        public static StateType valueOf(int stanType) {
            return (StateType) map.get(stanType);
        }

        public int getValue() {
            return value;
        }
    }

    public static boolean sequentState(int stanPoprzedniBazyInt, int stanAktualnyRestInt) {


        // DIAGRAM STANÓW

        StateType stanBazy = StateType.valueOf(stanPoprzedniBazyInt);
        StateType stanREST = StateType.valueOf(stanAktualnyRestInt);

        if (stanBazy == StateType.CREATED) {
            if (stanREST == StateType.DELETED || stanREST == StateType.CREATED
                    || stanREST == StateType.VERIFIED) {
                return true;
            } else
                return false;
        }
        ;

        if (stanBazy == StateType.VERIFIED) {
            if (stanREST == StateType.REJECTED || stanREST == StateType.ACCEPTED || stanREST == StateType.VERIFIED) {
                return true;
            } else
                return false;
        }

        if (stanBazy == StateType.ACCEPTED) {
            if (stanREST == StateType.REJECTED || stanREST == StateType.PUBLISHED) {
                return true;
            }
            else
                return false;
        }

        if (stanBazy == StateType.PUBLISHED) {
            return false;
        }

        if (stanBazy == StateType.REJECTED)
            return false;

        if (stanBazy == StateType.DELETED)
            return false;

        return false;


    }


}

