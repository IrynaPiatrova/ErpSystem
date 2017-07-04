package com.erp.system.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Roma on 04.07.2017.
 */
@Component
public class RegistrationNewProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {//copypast с логином и паролем в классе LoginPasswordValidator, надо подумать что с этим делать
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDateProfile", "empty.startDateProfile", "Please enter nowaday Date");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "empty.position", "Please enter worker's position");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employmentStatus", "empty.employmentStatus", "Please enter worker's status");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "empty.department", "Please enter worker's department");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "empty.telephone", "Please enter worker's telephone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty.email", "Please enter worker's email");
    }
}
