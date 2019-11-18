package jp.hftn.service.core.module;

import com.google.inject.AbstractModule;
import jp.hftn.service.core.server.web.PingResources;

public class PingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PingResources.class);
    }
}
