package jp.hftn.service.sample;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.servlet.ServletModule;
import jp.hftn.service.core.annotations.UnCached;
import jp.hftn.service.core.jersey.JerseyModule;
import jp.hftn.service.core.module.PingModule;
import jp.hftn.service.core.service.RunnableService;
import jp.hftn.service.sample.client.ClientMapper;
import jp.hftn.service.sample.model.DefaultUserService;
import jp.hftn.service.sample.model.UserService;
import jp.hftn.service.sample.persistence.cache.CacheUserRepository;
import jp.hftn.service.sample.persistence.model.UserRepository;
import jp.hftn.service.sample.persistence.sql.SqlUserRepository;
import jp.hftn.service.sample.server.web.SampleResources;
import org.eclipse.jetty.servlet.DefaultServlet;

public class Service {

    public static void main(String[] args) {
        new Thread(new RunnableService(new SampleServiceModule())).start();
    }

    private static class CoreModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(UserService.class).to(DefaultUserService.class);
        }
    }

    private static class DataModule extends AbstractModule {

        @Override
        protected void configure() {
            bind(UserRepository.class).annotatedWith(UnCached.class).to(SqlUserRepository.class);
            bind(UserRepository.class).to(CacheUserRepository.class);
        }
    }

    private static class RestModule extends ServletModule {

        @Override
        protected void configureServlets() {
            bind(ClientMapper.class).asEagerSingleton();
            install(new JerseyModule() {
                @Override
                protected void configureResources() {
                    bind(SampleResources.class);
                    bind(DefaultServlet.class).in(Singleton.class);
                }
            });

        }
    }

    public static class SampleServiceModule extends AbstractModule {

        @Override
        public void configure() {
            install(new PingModule());
            install(new CoreModule());
            install(new DataModule());
            install(new RestModule());
        }
    }
}
