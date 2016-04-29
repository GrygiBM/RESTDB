package com.bm.server.webservice.guice;

import com.bm.server.webservice.guice.modules.CoreModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.bm.server.webservice.guice.modules.WebserviceModule;

public class GuiceConfiguration extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new CoreModule(), new WebserviceModule());
    }
}
