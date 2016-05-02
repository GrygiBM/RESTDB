package com.bm.server.webservice.resources;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class HistoriazmianResourceTest extends JerseyTest {

    @Override
    protected javax.ws.rs.core.Application configure() {
        return new ResourceConfig(HistoriazmianResource.class);
    }


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void getHistoria() throws Exception {

    }

    @Test
    public void getHistoriaWniosku() throws Exception {

    }

    @Test
    public void find() throws Exception {

    }

}