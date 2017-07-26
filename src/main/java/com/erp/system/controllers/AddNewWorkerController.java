package com.erp.system.controllers;

import com.erp.system.constants.ModelConstants;
import com.erp.system.controllers.methods.MethodsForControllers;
import com.erp.system.dto.ProfileDTO;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by Roma on 18.06.2017
 */
@Controller
public class AddNewWorkerController extends ExceptionsController {
    @Autowired
    RegistrationNewWorkerValidator registrationNewWorkerValidator;
    @Autowired
    RegistrationNewProfileValidator registrationNewProfileValidator;
    @Autowired
    WorkerService workerService;
    @Autowired
    ProfileService profileService;

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/isSuccessAddNewProfile", method = RequestMethod.GET)
    public String isSuccessAddNewProfile(HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        model.addAttribute(ModelConstants.PROFILE, new Profile());
        return "pages/addNewProfile";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/isSuccessAddNewWorker", method = RequestMethod.GET)
    public String isSuccessAddNewWorker(HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        model.addAttribute(ModelConstants.WORKER, new Worker());
        return "pages/addNewWorker";
    }

    /**
     * @param profile
     * @param result
     * @param model
     * @return
     * @throws ParseException
     * @throws IOException
     */
    @RequestMapping(value = "/isSuccessAddNewProfile", method = RequestMethod.POST)
    public String isSuccessAddNewProfile(@ModelAttribute(ModelConstants.PROFILE) @Valid Profile profile, BindingResult result, HttpSession session,
                                         Model model) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        registrationNewProfileValidator.validate(profile, result);
        if (result.hasErrors()) return "pages/addNewProfile";
        model.addAttribute(ModelConstants.WORKER, new Worker());
        session.setAttribute(ModelConstants.PROFILE, profile);
        return "pages/addNewWorker";
    }

    /**
     * @param worker
     * @param result
     * @return
     */
    @RequestMapping(value = "/isSuccessAddNewWorker", method = RequestMethod.POST)
    public String isSuccessAddNewWorker(@ModelAttribute(ModelConstants.WORKER) @Valid Worker worker, BindingResult result,
                                        HttpSession session) throws IOException {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        registrationNewWorkerValidator.validate(worker, result);
        if (result.hasErrors()) return "pages/addNewWorker";
//        worker.setPassword(MethodsForControllers.convertToMD5(worker.getPassword())); // Для записи в БД в зашифрованном виде MD5
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("photo/me-flat.png");
        Profile profile = (Profile) session.getAttribute(ModelConstants.PROFILE);
        profile.setPhoto(MethodsForControllers.returnDefaultPhotoBytes(inputStream));
        profileService.createProfile(profile);
        worker.setProfile(profile);
        workerService.createWorker(worker);
        session.removeAttribute(ModelConstants.PROFILE);
        return "redirect: /main";
    }
}
