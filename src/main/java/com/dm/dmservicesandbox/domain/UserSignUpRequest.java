package com.dm.dmservicesandbox.domain;

import com.dm.dmservicesandbox.validation.PasswordMatches;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@PasswordMatches
public class UserSignUpRequest {

    private String userId;

    @NotEmpty
    private String displayName;

    @NotEmpty
    private String email;

    @Size(min = 6, message = "Min Length is 6")
    private String password;

    @NotEmpty
    private String matchingPassword;

    public UserSignUpRequest(String userId, String displayName, String email, String password) {
        this.userId = userId;
        this.displayName = displayName;
        this.email = email;
        this.password = password;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
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
