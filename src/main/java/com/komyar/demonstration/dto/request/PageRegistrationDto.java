package com.komyar.demonstration.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import static com.komyar.demonstration.enums.ValidationMessage.*;


@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PageRegistrationDto {

    @NotEmpty(message = FULL_NAME_MUST_NOT_BE_EMPTY)
    private String fullName;

    @NotEmpty(message = USERNAME_MUST_NOT_BE_EMPTY)
    private String username;

    @NotEmpty(message = PASSWORD_MUST_NOT_BE_EMPTY)
    private String password;

    @NotEmpty(message = PHONE_NUMBER_MUST_NOT_BE_EMPTY)
    private String phoneNumber;

}
