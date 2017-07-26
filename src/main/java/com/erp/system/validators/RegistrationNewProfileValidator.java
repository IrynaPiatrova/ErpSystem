package com.erp.system.validators;

import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Roma on 04.07.2017.
 */
@Component
public class RegistrationNewProfileValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
    @Autowired
    ProfileDao profileDao;

    @Override
    public void validate(Object o, Errors errors) {
        Profile profile = (Profile) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "empty.position", "Please enter worker's position");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employmentStatus", "empty.employmentStatus", "Please enter worker's status");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "empty.department", "Please enter worker's department");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "empty.telephone", "Please enter worker's telephone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty.email", "Please enter worker's email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startDateProfile", "empty.date");
        String emailPattern = "^[A-Za-z0-9-]+([._A-Za-z0-9-])*@([A-Za-z0-9-]+).[a-zа-я]{2,6}";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(profile.getEmail());
        if (!matcher.matches()) errors.rejectValue("email", "not.valid.email");
        if (profileDao.isEmailUnique(profile.getEmail())) errors.rejectValue("email", "exist.email");
        if (profileDao.isTelephoneUnique(profile.getTelephone())) errors.rejectValue("telephone", "exist.telephone"); // надо добавить паттер на телефон
    }
}
