package jp.hftn.service.sample.client;

import jp.hftn.service.sample.client.model.JsonUser;
import jp.hftn.service.sample.model.User;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

public class ClientMapper {

    public JsonUser toClientUser(final User input) {
        return JsonUser.builder()
                .emailAddress(input.getEmailAddress())
                .firstName(input.getFirstName())
                .id(input.getId())
                .lastName(input.getLastName())
                .build();
    }

    public Collection<JsonUser> toClientUsersResponse(final Collection<User> inputList) {
        return inputList.stream().map(it -> toClientUser(it)).collect(toList());
    }

}
