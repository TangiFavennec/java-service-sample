package jp.hftn.service.core.module;

import com.google.inject.AbstractModule;

import java.util.Properties;

public class ConfigurableModule extends AbstractModule {

    public ConfigurableModule() {
        properties = new Properties();
    }

    Properties properties;

}
