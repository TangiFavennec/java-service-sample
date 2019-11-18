package jp.hftn.service.sample.model;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
public class User {

    String emailAddress;
    String firstName;
    @NonNull
    Integer id;
    String lastName;

}
