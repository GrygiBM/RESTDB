package com.bm.server.model;

import com.bm.server.service.StateRequest;
import org.junit.Test;

import static com.bm.server.service.StateRequest.sequentState;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StateRequestTest {

    @Test
    public void nastepstwoStanowTest() throws Exception {

        assertTrue("CREATE 1", sequentState(StateRequest.StateType.CREATED.getValue(), StateRequest.StateType.DELETED.getValue()));
        assertTrue("CREATE 2", sequentState(StateRequest.StateType.CREATED.getValue(), StateRequest.StateType.CREATED.getValue()));
        assertFalse("CREATE 3", sequentState(StateRequest.StateType.CREATED.getValue(), StateRequest.StateType.ACCEPTED.getValue()));
        assertTrue("CREATE 2", sequentState(StateRequest.StateType.CREATED.getValue(), StateRequest.StateType.VERIFIED.getValue()));

        assertTrue("VERIFIED 1", sequentState(StateRequest.StateType.VERIFIED.getValue(), StateRequest.StateType.REJECTED.getValue()));
        assertTrue("VERIFIED 2", sequentState(StateRequest.StateType.VERIFIED.getValue(), StateRequest.StateType.ACCEPTED.getValue()));
        assertFalse("VERIFIED 3", sequentState(StateRequest.StateType.VERIFIED.getValue(), StateRequest.StateType.CREATED.getValue()));
        assertTrue("VERIFIED 4", sequentState(StateRequest.StateType.VERIFIED.getValue(), StateRequest.StateType.VERIFIED.getValue()));

        assertTrue("ACCEPTED 1", sequentState(StateRequest.StateType.ACCEPTED.getValue(), StateRequest.StateType.REJECTED.getValue()));
        assertTrue("ACCEPTED 2", sequentState(StateRequest.StateType.ACCEPTED.getValue(), StateRequest.StateType.PUBLISHED.getValue()));
        assertFalse("ACCEPTED 3", sequentState(StateRequest.StateType.ACCEPTED.getValue(), StateRequest.StateType.VERIFIED.getValue()));
        assertFalse("ACCEPTED 4", sequentState(StateRequest.StateType.ACCEPTED.getValue(), StateRequest.StateType.ACCEPTED.getValue()));

        assertFalse("DELETED 1", sequentState(StateRequest.StateType.DELETED.getValue(), StateRequest.StateType.REJECTED.getValue()));
        assertFalse("DELETED 2", sequentState(StateRequest.StateType.DELETED.getValue(), StateRequest.StateType.PUBLISHED.getValue()));
        assertFalse("DELETED 3", sequentState(StateRequest.StateType.DELETED.getValue(), StateRequest.StateType.VERIFIED.getValue()));
        assertFalse("DELETED 4", sequentState(StateRequest.StateType.DELETED.getValue(), StateRequest.StateType.CREATED.getValue()));

        assertFalse("REJECTED 1", sequentState(StateRequest.StateType.REJECTED.getValue(), StateRequest.StateType.REJECTED.getValue()));
        assertFalse("REJECTED 2", sequentState(StateRequest.StateType.REJECTED.getValue(), StateRequest.StateType.PUBLISHED.getValue()));

        assertFalse("PUBLISHED 1", sequentState(StateRequest.StateType.PUBLISHED.getValue(), StateRequest.StateType.VERIFIED.getValue()));
        assertFalse("PUBLISHED 2", sequentState(StateRequest.StateType.PUBLISHED.getValue(), StateRequest.StateType.ACCEPTED.getValue()));
        assertFalse("PUBLISHED 3", sequentState(StateRequest.StateType.PUBLISHED.getValue(), StateRequest.StateType.DELETED.getValue()));
        assertFalse("PUBLISHED 4", sequentState(StateRequest.StateType.PUBLISHED.getValue(), StateRequest.StateType.PUBLISHED.getValue()));

    }

}