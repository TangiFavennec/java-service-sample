package jp.hftn.service.core;

import com.google.inject.Provider;
import org.eclipse.jetty.server.Server;

public class JettyServerProvider implements Provider<Server> {

    private final Integer port = 8080;

    @Override
    public Server get() {
        return new Server(port);
    }
}
