package com.phonedirectory.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.validation.annotation.Validated;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "phoneNumber",
        "firstName",
        "LastName"
})
@Data
@NoArgsConstructor
@NonNull
public class PhoneDirectory {

    @JsonProperty("phoneNumber")
    Long phoneNumber;

    @JsonProperty("firstName")
    String firstName;

    @JsonProperty("lastName")
    String lastName;
}