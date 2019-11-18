package jp.hftn.service.core.server;

import com.google.inject.Inject;
import com.google.inject.Injector;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.jaxrs.JsonMappingExceptionMapper;
import org.codehaus.jackson.jaxrs.JsonParseExceptionMapper;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;

public class ApplicationConfig extends ResourceConfig {

    @Inject
    public ApplicationConfig(final ServiceLocator serviceLocator,
                             final @Context ServletContext servletContext) {
        this.packages("jp.hftn.service.sample.server.web", "jp.hftn.service.core.server.web");
        this.register(JacksonFeature.class);
        register(JsonParseExceptionMapper.class);
        register(JsonMappingExceptionMapper.class);
        register(JacksonJsonProvider.class, MessageBodyReader.class, MessageBodyWriter.class);

        // We access the injector that was attached to the context by GuiceServletContextListener
        Injector injector = (Injector) servletContext.getAttribute(Injector.class.getName());
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(injector);
    }
}
