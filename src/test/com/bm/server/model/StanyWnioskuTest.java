package com.bm.server.model;

import com.bm.server.service.StanyWniosku;
import org.junit.Test;

import static com.bm.server.service.StanyWniosku.nastepstwoStanow;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StanyWnioskuTest {

    @Test
    public void nastepstwoStanowTest() throws Exception {

        assertTrue("CREATE 1", nastepstwoStanow(StanyWniosku.StanType.CREATED.getValue(),StanyWniosku.StanType.DELETED.getValue()));
        assertTrue("CREATE 2", nastepstwoStanow(StanyWniosku.StanType.CREATED.getValue(),StanyWniosku.StanType.CREATED.getValue()));
        assertFalse("CREATE 3",nastepstwoStanow(StanyWniosku.StanType.CREATED.getValue(),StanyWniosku.StanType.ACCEPTED.getValue()));

        assertTrue("VERIFIED 1",nastepstwoStanow(StanyWniosku.StanType.VERIFIED.getValue(),StanyWniosku.StanType.REJECTED.getValue()));
        assertTrue("VERIFIED 2",nastepstwoStanow(StanyWniosku.StanType.VERIFIED.getValue(),StanyWniosku.StanType.ACCEPTED.getValue()));
        assertFalse("VERIFIED 3",nastepstwoStanow(StanyWniosku.StanType.VERIFIED.getValue(),StanyWniosku.StanType.CREATED.getValue()));

        assertTrue("ACCEPTED 1",nastepstwoStanow(StanyWniosku.StanType.ACCEPTED.getValue(),StanyWniosku.StanType.REJECTED.getValue()));
        assertTrue("ACCEPTED 2",nastepstwoStanow(StanyWniosku.StanType.ACCEPTED.getValue(),StanyWniosku.StanType.PUBLISHED.getValue()));
        assertFalse("ACCEPTED 3",nastepstwoStanow(StanyWniosku.StanType.ACCEPTED.getValue(),StanyWniosku.StanType.VERIFIED.getValue()));


    }

}