package jp.hftn.service.sample.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class JsonUser {
    private final String emailAddress;
    private final String firstName;
    private final Integer id;
    private final String lastName;
}
