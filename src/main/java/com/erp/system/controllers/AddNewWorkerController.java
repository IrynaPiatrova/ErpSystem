package com.erp.system.controllers;

import com.erp.system.dao.profile.impl.ProfileDaoImpl;
import com.erp.system.dao.worker.impl.WorkerDaoImpl;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import com.erp.system.tags.MD5;
import com.erp.system.validators.RegistrationNewProfileValidator;
import com.erp.system.validators.RegistrationNewWorkerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    private MD5 md5 = new MD5();
    private Profile profileWorker = new Profile();

    @RequestMapping(value = "/isSuccessAddNewProfile", method = RequestMethod.GET)
    public String isSuccessAddNewProfile(Model model){
        model.addAttribute("profile", new Profile());
        return "pages/addNewProfile";
    }
    @RequestMapping(value = "/isSuccessAddNewWorker", method = RequestMethod.GET)
    public String isSuccessAddNewWorker(Model model){
        model.addAttribute("worker", new Worker());
        return "pages/addNewWorker";
    }

    @RequestMapping(value = "/isSuccessAddNewProfile", method = RequestMethod.POST)
    public String isSuccessAddNewProfile(@ModelAttribute("profile") @Valid Profile profile, BindingResult result,
                                         @RequestParam("Date") String startDate, Model model) throws ParseException {
        registrationNewProfileValidator.validate(profile, result);
        if (startDate.length() == 0) result.rejectValue("startDateProfile", "empty.date");
        if (result.hasErrors()) return "pages/addNewProfile";
        SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = oldDateFormat.parse(startDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        profile.setStartDateProfile(sqlDate);
        model.addAttribute("worker", new Worker());
        profileWorker = profile;
        return "pages/addNewWorker";
    }

    @RequestMapping(value = "/isSuccessAddNewWorker", method = RequestMethod.POST)
    public String isSuccessAddNewWorker(@ModelAttribute("worker") @Valid Worker worker, BindingResult result) {
        registrationNewWorkerValidator.validate(worker, result);
        if (result.hasErrors()) return "pages/addNewWorker";
        worker.setPassword(md5.getMD5(worker.getPassword()));
        profileDao.createProfile(profileWorker);
        worker.setProfile(profileWorker);
        workerDao.createWorker(worker);
        return "pages/main";
    }
}
