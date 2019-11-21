package jp.hftn.service.core.module;

import com.google.inject.AbstractModule;

import java.util.Properties;

public class ConfigurableModule extends AbstractModule {

    Properties properties;

    public ConfigurableModule() {
        properties = new Properties();
    }

}
