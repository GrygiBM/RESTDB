package com.bm.server.webservice.resources;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Application;

public class RequestResourceTest extends JerseyTest {





    @Override
    protected Application configure() {
        return new ResourceConfig(RequestResource.class) {
        };
    }

    @Before
    public void setUp() throws Exception {


    }

    @After
    public void tearDown() throws Exception {


    }

    @Test
    public void getWszystkieWnioski() throws Exception {

    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void edit() throws Exception {

    }

    @Test
    public void findRange() throws Exception {

    }

    @Test
    public void find() throws Exception {

    }

}