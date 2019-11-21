package jp.hftn.service.sample.persistence.cache;

import com.google.inject.Inject;
import jp.hftn.service.core.annotations.UnCached;
import jp.hftn.service.sample.model.User;
import jp.hftn.service.sample.persistence.model.UserRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CacheUserRepository implements UserRepository {

    private final UserRepository innerRepository;

    private final Map<Integer, User> kvStore;

    @Inject
    public CacheUserRepository(@UnCached final UserRepository innerRepository) {
        this.innerRepository = innerRepository;
        kvStore = new HashMap<>();
    }

    @Override
    public Collection<User> getUsers() {
        return innerRepository.getUsers();
    }

    @Override
    public Optional<User> getUserById(final Integer input) {
        return innerRepository.getUserById(input);
    }
}
