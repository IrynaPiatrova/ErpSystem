package com.erp.system.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Roma on 04.07.2017.
 */
@Component
public class RegistrationNewWorkerValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {//copypast с логином и паролем в классе LoginPasswordValidator, надо подумать что с этим делать
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Please enter your login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Please enter your password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameWorker", "empty.nameWorker", "Please enter name of Worker");
    }
}
