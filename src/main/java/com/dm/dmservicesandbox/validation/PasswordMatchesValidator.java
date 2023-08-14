package com.dm.dmservicesandbox.validation;


import com.dm.dmservicesandbox.domain.UserSignUpRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, UserSignUpRequest> {

    @Override
    public boolean isValid(final UserSignUpRequest user, final ConstraintValidatorContext context) {
        return true;
    }
}