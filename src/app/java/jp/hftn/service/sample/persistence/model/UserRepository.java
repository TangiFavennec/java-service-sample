package jp.hftn.service.sample.persistence.model;

import jp.hftn.service.sample.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {

    Collection<User> getUsers();

    Optional<User> getUserById(final Integer input);
}
