package com.erp.system.validators;

import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dto.ProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by klinster on 12.07.2017
 */
@Component
public class EditProfileValidator implements Validator{
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
    @Autowired
    WorkerDao workerDao;

    @Override
    public void validate(Object o, Errors errors) {
        ProfileDTO profile = (ProfileDTO) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "empty.position", "Please enter worker's position");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "employmentStatus", "empty.employmentStatus", "Please enter worker's status");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "department", "empty.department", "Please enter worker's department");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephone", "empty.telephone", "Please enter worker's telephone");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "empty.email", "Please enter worker's email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "worker.login", "empty.login", "Please enter your login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "worker.password", "empty.password", "Please enter your password");
        String emailPattern = "^[A-Za-z0-9-]+([._A-Za-z0-9-])*@([A-Za-z0-9-]+).[a-zа-я]{2,6}";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(profile.getEmail());
        if (!matcher.matches()) errors.rejectValue("email", "not.valid.email");
    }
}
