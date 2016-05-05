package com.bm.server.webservice.resources;


import com.bm.server.webservice.guice.GuiceConfiguration;
import com.google.inject.servlet.GuiceFilter;
import com.jayway.restassured.http.ContentType;
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
import javax.ws.rs.core.Application;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class RequestResourceTest extends JerseyTest {

    public static final String BASE_URI = "http://localhost:8080/api/";
    private Server server;

    @Override
    protected Application configure() {
        return new ResourceConfig(RequestResource.class) {
        };
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
    public void getWszystkieWnioski() throws Exception {


                given().expect()
                .response().statusCode(HttpStatus.SC_OK)
                .when().get(BASE_URI  + "wnioski");


    }

    @Test
    public void create() throws Exception {


        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", 4);
        jsonAsMap.put("stan", 1);
        jsonAsMap.put("nazwa","WniosekT");
        jsonAsMap.put("tresc","Wniosek testowy ");
        jsonAsMap.put("numer",null);
        jsonAsMap.put("info",null);

        given().
                contentType(ContentType.JSON).
                body(jsonAsMap).
                when().
                post("/wnioski");

    }

    @Test
    public void edit() throws Exception {

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("id", 4);
        jsonAsMap.put("stan", 2);
        jsonAsMap.put("nazwa","WniosekT");
        jsonAsMap.put("tresc","Wniosek testowy ");
        jsonAsMap.put("numer",null);
        jsonAsMap.put("info",null);

        given().
                contentType(ContentType.JSON).
                body(jsonAsMap).
                when().
                put("/wnioski/4");

    }

    @Test
    public void findRange() throws Exception {

    }

    @Test
    public void find() throws Exception {

        given().expect()
                .response().statusCode(HttpStatus.SC_OK)
                .when().get(BASE_URI  + "wnioski/1");

    }

}