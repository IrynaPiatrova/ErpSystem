package com.erp.system.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Roma on 11.07.2017.
 */
@Component
public class NewTicketValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"nameProjectTicket","empty.ticket.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"specification","empty.ticket.specification");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"statusProjectTicket","empty.ticket.status");
    }
}
