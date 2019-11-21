package jp.hftn.service.sample.persistence.sql;

import jp.hftn.service.sample.model.User;
import jp.hftn.service.sample.persistence.model.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.google.common.collect.Lists.newArrayList;

public class SqlUserRepository implements UserRepository {

    private List<User> data;

    public SqlUserRepository() {
        data = newArrayList();
        data.add(User.builder()
                .emailAddress("ultraman@ultraman.com")
                .firstName("ultra")
                .id(1)
                .lastName("man")
                .build());
    }

    @Override
    public Collection<User> getUsers() {
        return data;
    }

    @Override
    public Optional<User> getUserById(final Integer input) {
        return data.stream().filter(it -> it.getId().equals(input)).findFirst();
    }
}
