package jp.hftn.service.core.jersey;


import com.google.inject.Inject;
import com.google.inject.name.Named;
import lombok.Data;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.Objects.requireNonNull;

@Data
public class JerseyConfiguration {

    private List<ServerConnectorConfiguration> serverConnectors;
    private String contextPath;

    @Inject
    JerseyConfiguration(@Named("host") final String host,
                        @Named("port") final Integer port,
                        @Named("context.path") final String contextPath) {
        checkNotNull(host);
        checkNotNull(port);
        this.serverConnectors = newArrayList();
        this.serverConnectors.add(new ServerConnectorConfiguration(host, port));
        this.contextPath = contextPath == null ? "/" : appendLeadingSlashIfMissing(contextPath);

        if (serverConnectors.size() == 0) {
            throw new RuntimeException("Must supply at least one server connector");
        }
    }

    private String appendLeadingSlashIfMissing(String contextRoot) {
        contextRoot = requireNonNull(contextRoot);
        if (!contextRoot.startsWith("/")) {
            contextRoot = "/" + contextRoot;
        }

        return contextRoot;
    }


}
