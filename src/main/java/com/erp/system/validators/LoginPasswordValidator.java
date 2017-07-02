package com.erp.system.validators;

import com.erp.system.dao.worker.impl.WorkerDaoImpl;
import com.erp.system.dto.LoginPassword;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by John on 22.06.2017.
 */
@Component
public class LoginPasswordValidator implements Validator {
    @Autowired
    WorkerDaoImpl workerDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return LoginPassword.class.equals(aClass);//.isAssignableFrom(aClass) - вернёт true, если предок класса
    }

    @Override
    public void validate(Object object, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "empty.login", "Please enter your login");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "empty.password", "Please enter your password");
        LoginPassword loginPassword = (LoginPassword) object;
        //пока не работает проверка на admin
        if (!workerDao.isLoginPasswordValid(loginPassword.getLogin(),loginPassword.getPassword())){
            errors.rejectValue("password", "err.login.password", "Incorrect login or password.");
        }
    }
}
