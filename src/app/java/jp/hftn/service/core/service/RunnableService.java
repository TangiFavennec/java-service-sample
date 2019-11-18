package jp.hftn.service.core.service;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import jp.hftn.service.core.jersey.JerseyServer;

public class RunnableService implements Runnable {

    private final AbstractModule serviceModule;

    public RunnableService(final AbstractModule serviceModule) {
        this.serviceModule = serviceModule;
    }

    @Override
    public void run() {
        try {
            Injector injector = Guice.createInjector(serviceModule);
            injector.getInstance(JerseyServer.class).start();
        } catch (InterruptedException e) {
            System.out.println("Service has been interrupted.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Service has terminated unexpectedly.");
            e.printStackTrace();
        }
    }
}
