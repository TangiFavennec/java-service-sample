package jp.hftn.service.sample.model;

import java.util.Collection;

public interface UserService {

    User getUserById(final Integer input);

    Collection<User> getUsers();
}
