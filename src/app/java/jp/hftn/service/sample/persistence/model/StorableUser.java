package jp.hftn.service.sample.persistence.model;

import lombok.Data;

@Data
public class StorableUser {

    private String emailAddress;
    private String firstName;
    private Integer id;
    private String lastName;

}
