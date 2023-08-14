package com.dm.dmservicesandbox.domain;

import com.dm.dmservicesandbox.validation.PasswordMatches;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



@Getter
@Setter
@ToString(callSuper=true, includeFieldNames=true)
@PasswordMatches
@Builder
public class UserSignUpRequest {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String displayName;

    @NotEmpty
    private String email;

    @Size(min = 12, message = "Min Length is 12")
    private String password;

    @NotEmpty
    private String country;

    @NotEmpty
    private String city;

    @NotEmpty
    private String mapBoxId;


}
