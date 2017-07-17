package com.erp.system.controllers;

import com.erp.system.controllers.methods.MethodsForControllers;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import com.erp.system.services.profile.ProfileService;
import com.erp.system.services.worker.WorkerService;
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
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by Roma on 18.06.2017
 */
@Controller
public class AddNewWorkerController {
    @Autowired
    RegistrationNewWorkerValidator registrationNewWorkerValidator;
    @Autowired
    RegistrationNewProfileValidator registrationNewProfileValidator;
    @Autowired
    WorkerService workerService;
    @Autowired
    ProfileService profileService;
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
                                         @RequestParam("Date") String startDate, Model model) throws ParseException, IOException {
        registrationNewProfileValidator.validate(profile, result);
        if (startDate.length() == 0) result.rejectValue("startDateProfile", "empty.date");
        if (result.hasErrors()) return "pages/addNewProfile";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("photo/me-flat.png");
        profile.setPhoto(MethodsForControllers.returnDefaultPhotoBytes(inputStream));
        profile.setStartDateProfile(startDate);
        model.addAttribute("worker", new Worker());
        profileWorker = profile;
        return "pages/addNewWorker";
    }

    @RequestMapping(value = "/isSuccessAddNewWorker", method = RequestMethod.POST)
    public String isSuccessAddNewWorker(@ModelAttribute("worker") @Valid Worker worker, BindingResult result) {
        registrationNewWorkerValidator.validate(worker, result);
        if (result.hasErrors()) return "pages/addNewWorker";
        worker.setPassword(worker.getPassword());                                       // Для записи в БД в незашифрованном виде
//        worker.setPassword(MethodsForControllers.convertToMD5(worker.getPassword())); // Для записи в БД в зашифрованном виде MD5
        profileService.createProfile(profileWorker);
        worker.setProfile(profileWorker);
        workerService.createWorker(worker);
        return "pages/main";
    }
}
