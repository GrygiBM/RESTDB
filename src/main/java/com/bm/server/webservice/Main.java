package com.bm.server.webservice;


import com.google.inject.servlet.GuiceFilter;
import com.bm.server.webservice.guice.GuiceConfiguration;
import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {


    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletContextHandler root = new ServletContextHandler(server, "/api", ServletContextHandler.SESSIONS);
        root.addEventListener(new GuiceConfiguration());
        root.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        root.addServlet(ServletContainer.class, "/*");
        server.start();
    }
}