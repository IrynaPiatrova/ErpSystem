package com.erp.system.controllers;

import com.erp.system.dao.profile.impl.ProfileDaoImpl;
import com.erp.system.dao.worker.impl.WorkerDaoImpl;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import com.erp.system.validators.RegistrationNewProfileValidator;
import com.erp.system.validators.RegistrationNewWorkerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

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
//    Worker worker = new Worker();
//    Profile profile = new Profile();

    @RequestMapping(value ="/isSuccessAddNewWorker", method = RequestMethod.POST)
    public String isSuccessAddNewWorker(@ModelAttribute("profile")@Valid Profile profile, BindingResult resultProfile,
                                        @ModelAttribute("worker") @Valid Worker worker, BindingResult resultWorker){
        registrationNewProfileValidator.validate(profile, resultProfile);
        registrationNewWorkerValidator.validate(worker,resultWorker);
        if(resultProfile.hasErrors() || resultWorker.hasErrors()){
            return "pages/addNewWorker";
        }else {
            profileDao.createProfile(profile);
            //worker.setIdProfile(profile.getIdProfile());
            //workerDao.createWorker(worker);
            return "pages/next";
        }
    }
}
