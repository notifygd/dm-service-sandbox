package com.dm.dmservicesandbox.domain;

import com.dm.dmservicesandbox.validation.PasswordMatches;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@PasswordMatches
@Getter
@Setter
public class UserSignUpRequest {

    private Long userDBIdentifier;

    private String userId;

    @NotEmpty
    private String displayName;

    @NotEmpty
    private String email;

    @Size(min = 6, message = "{Size.userDto.password}")
    private String password;

    @NotEmpty
    private String matchingPassword;

    public UserSignUpRequest(String userId, String displayName, String email, String password) {
        this.userId = userId;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private String displayName;
        private String email;
        private String password;

        public Builder addUserID(final String userID) {
            this.userId = userID;
            return this;
        }

        public Builder addDisplayName(final String displayName) {
            this.displayName = displayName;
            return this;
        }

        public Builder addEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder addPassword(final String password) {
            this.password = password;
            return this;
        }

        public UserSignUpRequest build() {
            return new UserSignUpRequest(userId, displayName, email, password);
        }
    }
}
