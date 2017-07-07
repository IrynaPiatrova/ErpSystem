package com.erp.system.controllers;

import com.erp.system.dao.profile.impl.ProfileDaoImpl;
import com.erp.system.dao.worker.impl.WorkerDaoImpl;
import com.erp.system.dto.RegistrationNewWorkerAndAddNewProfile;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import com.erp.system.validators.RegistrationNewProfileValidator;
import com.erp.system.validators.RegistrationNewWorkerValidator;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Roma on 18.06.2017.
 */
@Controller
public class AddNewWorkerController {
    @Autowired
    RegistrationNewWorkerValidator registrationNewWorkerValidator;
    @Autowired
    RegistrationNewProfileValidator registrationNewProfileValidator;
    @Autowired
    WorkerDaoImpl workerDao;
    @Autowired
    ProfileDaoImpl profileDao;

    @RequestMapping(value ="/isSuccessAddNewProfile", method = RequestMethod.POST)
    public String isSuccessAddNewProfile(@ModelAttribute("profile")@Valid Profile profile, BindingResult result,
                                         @RequestParam("Date")@Valid String startDate, BindingResult resultDate, Model model) throws ParseException {
        registrationNewProfileValidator.validate(profile, result);
        //registrationNewProfileValidator.validateDate(startDate, resultDate);
        if (startDate.length() == 0){//Калечная проверка на дату, нужно сделать нормальную
            return "pages/addNewProfile";
        }else {
            if (result.hasErrors()) {
                return "pages/addNewProfile";
            } else {
                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = oldDateFormat.parse(startDate);
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                profile.setStartDateProfile(sqlDate);
                profileDao.createProfile(profile);
                model.addAttribute("worker", new Worker());
                return "pages/addNewWorker";
            }
        }
    }

    @RequestMapping(value = "/isSuccessAddNewWorker", method = RequestMethod.POST)
    public String isSuccessAddNewWorker( @ModelAttribute("worker")@Valid Worker worker, BindingResult result){
        registrationNewWorkerValidator.validate(registrationNewWorkerValidator, result);
        if (result.hasErrors()){
            return "pages/addNewWorker";
        }else {
            workerDao.createWorker(worker);
            return "pages/main";
        }
    }
}
