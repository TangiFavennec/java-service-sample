package jp.hftn.service.sample.persistence.model;

import jp.hftn.service.sample.model.User;

import java.util.function.Function;

public class StorableUserMapper {

    public static class From implements Function<StorableUser, User> {

        @Override
        public User apply(StorableUser input) {
            return User.builder()
                    .emailAddress(input.getEmailAddress())
                    .firstName(input.getFirstName())
                    .id(input.getId())
                    .lastName(input.getLastName())
                    .build();
        }
    }

}
