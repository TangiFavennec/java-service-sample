package jp.hftn.service.sample.model;

import com.google.inject.Inject;
import jp.hftn.service.core.exception.ResourceNotFoundException;
import jp.hftn.service.sample.persistence.model.UserRepository;
import lombok.AllArgsConstructor;

import java.util.Collection;

@AllArgsConstructor(onConstructor = @__({ @Inject}))
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserById(Integer input) {
        return userRepository.getUserById(input)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public Collection<User> getUsers() {
        return userRepository.getUsers();
    }
}
