package com.dm.dmservicesandbox.domain.restservice;

import com.dm.dmservicesandbox.validation.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@PasswordMatches
@Builder
public class UserSignUpRequest {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String displayName;

    @NotEmpty
    private String email;

    @Size(min = 6, message = "Min Length is 12")
    private String password;

    @Size(max = 2, message = "Max Length is 2")
    @NotEmpty
    private String country;

    @NotEmpty
    private String city;

    private String region;

    private String district;

    private Float longitute;

    private Float latitude;


}