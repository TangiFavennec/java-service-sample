package jp.hftn.service.core.jersey;

import com.google.inject.AbstractModule;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.TypeLiteral;
import com.google.inject.servlet.ServletModule;
import jp.hftn.service.core.JettyServerProvider;
import org.eclipse.jetty.server.Server;

import java.util.function.Supplier;

import static com.google.inject.name.Names.named;

public abstract class JerseyModule extends AbstractModule {

    protected void configure() {
        Provider<Injector> injectorProvider = getProvider(Injector.class);

        install(new ServletModule() {
            @Override
            public void configureServlets() {
                configureResources();
            }
        });

        install(new ServletModule());
        bind(Integer.class).annotatedWith(named("port")).toInstance(8080);
        bind(String.class).annotatedWith(named("host")).toInstance("localhost");
        bind(String.class).annotatedWith(named("context.path")).toInstance("/");
        bind(JerseyConfiguration.class);
        bind(Server.class).toProvider(JettyServerProvider.class);
        bind(new TypeLiteral<Supplier<Injector>>() {}).toInstance(injectorProvider::get);
        bind(JerseyServer.class);
    }

    protected abstract void configureResources();
}
