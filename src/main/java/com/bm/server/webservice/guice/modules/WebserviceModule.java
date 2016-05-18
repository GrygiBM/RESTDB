package com.bm.server.webservice.guice.modules;

import com.bm.server.webservice.jackson.CustomJacksonJaxbJsonProvider;
import com.bm.server.webservice.resources.HistoryModificationsResource;
import com.bm.server.webservice.resources.RequestResource;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;

import java.util.HashMap;
import java.util.Map;

public class WebserviceModule extends ServletModule {

    @Override
    protected void configureServlets() {
        // bindRestResources();
        bind(HistoryModificationsResource.class);
        bind(RequestResource.class);

        bind(JacksonJaxbJsonProvider.class).to(CustomJacksonJaxbJsonProvider.class).asEagerSingleton();

        Map<String, String> params = new HashMap<String, String>();
        params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
        serve("/*").with(GuiceContainer.class, params);
    }

    /**
     * bind the REST resources
     */
    protected void bindRestResources() {
        bind(HistoryModificationsResource.class);
        bind(RequestResource.class);
    }

}