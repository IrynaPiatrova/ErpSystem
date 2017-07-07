package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.erp.system.controllers.MethodsForControllers.getPhoto;

/**
 * Created by John on 18.06.2017.
 */
@Controller
public class MenuController {
    @Autowired
    WorkerDao workerDao;
    @Autowired
    ProfileDao profileDao;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String mainPage(Model model, HttpServletRequest request) {
        if (!MethodsForControllers.isLogedIn(request)) return "redirect:/";
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute(IConstants.LOGED_AS);
        Worker workerByLogin = workerDao.getWorkerByLogin(login);
        Profile profileById = profileDao.getProfileById(workerByLogin.getIdProfile());
        MultipartFile photo = getPhoto(profileById.getPhoto());
        model.addAttribute(IConstants.PHOTO, photo != null ? photo : IConstants.URL_DEFAULT_PHOTO);
        model.addAttribute(IConstants.NAME_USER, workerByLogin.getNameWorker());
        model.addAttribute(IConstants.PROFILE, profileById);
        model.addAttribute(IConstants.PROFILE_DATA, profileById);
        return "pages/profile";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    private String editProfile(HttpServletRequest request, Model model, @ModelAttribute(IConstants.PROFILE_DATA) @Valid Profile profile) {
        model.addAttribute(IConstants.PROFILE_DATA, profile);
        return !MethodsForControllers.isLogedIn(request) ? "redirect:/" : "pages/editProfile";
    }
}
