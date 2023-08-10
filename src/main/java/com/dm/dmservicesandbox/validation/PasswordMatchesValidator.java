package com.dm.dmservicesandbox.validation;


import com.dm.dmservicesandbox.domain.UserSignUpRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserSignUpRequest> {

    @Override
    public boolean isValid(final UserSignUpRequest user, final ConstraintValidatorContext context) {
        return user.getPassword().equals(user.getMatchingPassword());
    }
}