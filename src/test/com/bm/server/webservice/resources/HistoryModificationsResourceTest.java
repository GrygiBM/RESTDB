package com.bm.server.webservice.resources;

import com.bm.server.webservice.guice.GuiceConfiguration;
import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.apache.http.HttpStatus;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

import static com.jayway.restassured.RestAssured.given;


public class HistoryModificationsResourceTest extends JerseyTest {

    public static final String BASE_URI = "http://localhost:8080/api/";
    private Server server;

    @Override
    protected javax.ws.rs.core.Application configure() {
        return new ResourceConfig(HistoryModificationsResource.class);
    }


    @Before
    public void setUp() throws Exception {

        // Create Server
        server = new Server(8080);
        ServletContextHandler root = new ServletContextHandler(server, "/api", ServletContextHandler.SESSIONS);
        root.addEventListener(new GuiceConfiguration());
        root.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        root.addServlet(ServletContainer.class, "/*");
        server.start();

        // Start Server
        server.start();

    }

    @After
    public void tearDown() throws Exception {

        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHistoria() throws Exception {

        given().expect()
                .response().statusCode(HttpStatus.SC_OK)
                .when().get(BASE_URI  + "historiazmian");

    }

    @Test
    public void getHistoriaWniosku() throws Exception {

        given().expect()
                .response().statusCode(HttpStatus.SC_OK)
                .when().get(BASE_URI  + "historiazmian/wniosek/1");

    }

    @Test
    public void find() throws Exception {

//        given().expect()
//                .response().statusCode(HttpStatus.SC_OK)
//                .when().get(BASE_URI  + "historiazmian/1");


    }

}