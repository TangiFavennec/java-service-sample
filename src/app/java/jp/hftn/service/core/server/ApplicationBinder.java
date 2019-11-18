package jp.hftn.service.core.server;

import jp.hftn.service.sample.client.ClientMapper;
import jp.hftn.service.sample.persistence.model.UserRepository;
import jp.hftn.service.sample.persistence.sql.SqlUserRepository;
import org.glassfish.jersey.internal.inject.AbstractBinder;

public class ApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(UserRepository.class).to(SqlUserRepository.class);
        bind(ClientMapper.class);
    }
}
