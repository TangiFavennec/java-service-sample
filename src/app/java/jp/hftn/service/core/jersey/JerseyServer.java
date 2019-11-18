package jp.hftn.service.core.jersey;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import jp.hftn.service.core.server.ApplicationConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.List;
import java.util.function.Supplier;

public class JerseyServer {

    private final JerseyConfiguration jerseyConfiguration;
    private final Supplier<Injector> injectorSupplier;
    private final Server server;

    @Inject
    JerseyServer(final JerseyConfiguration jerseyConfiguration,
                 final Supplier<Injector> injectorSupplier,
                 final Server server) {
        this.jerseyConfiguration = jerseyConfiguration;
        this.injectorSupplier = injectorSupplier;
        this.server = server;

        configureServer();
    }

    public void start() throws Exception {
        server.start();
        System.out.println("Server has started");
    }

    public void stop() throws Exception {
        server.stop();
        System.out.println("Server stopped");
    }

    private void configureServer() {

        List<ServerConnectorConfiguration> serverConnectorConfigurations = jerseyConfiguration.getServerConnectors();
        serverConnectorConfigurations.forEach(configuration -> {
            ServerConnector connector = new ServerConnector(server);
            connector.setName(configuration.getName());
            connector.setHost(configuration.getHost());
            connector.setPort(configuration.getPort());
            server.addConnector(connector);
        });

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setServer(server);

        context.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));

        ServletHolder holder = new ServletHolder(ServletContainer.class);
        holder.setInitParameter("javax.ws.rs.Application", ApplicationConfig.class.getName());

        context.addServlet(holder, "/*");
        context.setResourceBase("/");
        context.setContextPath(jerseyConfiguration.getContextPath());
        context.addEventListener(new GuiceServletContextListener() {
            @Override
            protected Injector getInjector() {
                return injectorSupplier.get();
            }
        });

        server.setHandler(context);
    }
}
