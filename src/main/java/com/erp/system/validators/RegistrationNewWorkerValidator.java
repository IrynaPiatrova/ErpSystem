package com.erp.system.validators;

import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    WorkerDao workerDao;

    @Override
    public void validate(Object o, Errors errors) {
        Worker worker = (Worker) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Please enter your login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Please enter your password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nameWorker", "empty.nameWorker", "Please enter name of Worker");
        if (workerDao.isLoginUnique(worker.getLogin())) errors.rejectValue("login","exist.login");
    }
}
