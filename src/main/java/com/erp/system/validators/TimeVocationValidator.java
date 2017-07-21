package com.erp.system.validators;

import com.erp.system.constants.ModelConstants;
import com.erp.system.entity.TimeVocation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by klinster on 20.07.2017
 */
@Component
public class TimeVocationValidator implements Validator{

    SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        TimeVocation timeVocation = (TimeVocation) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startVocDate", "empty.startVocDate", "Please choose start date");
        if(ModelConstants.VACATION.equals(timeVocation.getType())){
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endVocDate", "empty.endVocDate", "Please choose end date");
        }
        try {
            Date start = oldDateFormat.parse(timeVocation.getStartVocDate());
            Date end = oldDateFormat.parse(timeVocation.getEndVocDate());
            if(start.compareTo(end)!=-1) errors.rejectValue("endVocDate", "not.valid.endDate");
        } catch (ParseException e) {
            e.printStackTrace(); //надо залоггировать
        }
    }
}
