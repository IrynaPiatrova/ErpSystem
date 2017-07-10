package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dto.ProfileDTO;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;


/**
 * Created by John on 18.06.2017
 */
@Controller
public class MenuController {
    @Autowired
    WorkerDao workerDao;
    @Autowired
    ProfileDao profileDao;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String mainPage(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        String login = (String) session.getAttribute(IConstants.LOGED_AS);
        Worker workerByLogin = workerDao.getWorkerByLogin(login);
        Profile profileById = profileDao.getProfileById(workerByLogin.getProfile().getIdProfile());
        byte[] photo = profileById.getPhoto();
        model.addAttribute(IConstants.PHOTO, photo != null && photo.length > 0 ? profileById.getPhoto() : null);
        model.addAttribute(IConstants.NAME_USER, workerByLogin.getNameWorker());
        model.addAttribute(IConstants.PROFILE, profileById);
        session.setAttribute(IConstants.PROFILE_DATA, profileById);
        return "pages/profile";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProfileInit(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(IConstants.PROFILE_DATA, session.getAttribute(IConstants.PROFILE_DATA));
        return "pages/editProfile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    //это метод для изменения СВОЕГО профиля (или пользователь меняет свой профиль, или админ свой)
    public String editProfile(@ModelAttribute(IConstants.PROFILE_DATA) @Valid ProfileDTO profileDTO, @RequestParam("photo") MultipartFile photo, HttpSession session, BindingResult result) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        if (result.hasErrors()) return "pages/editProfile";
        Profile profile = (Profile) session.getAttribute(IConstants.PROFILE_DATA);
        profile.setTelephone(profileDTO.getTelephone());
        profile.setEmail(profileDTO.getEmail());
        try {
            profile.setPhoto(photo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        profileDao.updateProfile(profile);
        return "redirect:/profile";
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    //этот метод для изменения других полей профиля - меняет ТОЛЬКО админ после выбора из списка пользователей
    private String editProfile(HttpSession session, @ModelAttribute(IConstants.PROFILE_DATA) ProfileDTO profileDTO) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Profile profile = new Profile(); //тут надо подумать как передавать из списка пользователей характеристики выбранного пользователя, потом переход на новую страницу с изменением профиля выбранного пользователя
        profile.setPosition(profileDTO.getPosition());
        profile.setEmploymentStatus(profileDTO.getEmploymentStatus());
        profile.setDepartment(profileDTO.getDepartment());
        profileDao.updateProfile(profile);
        return "redirect:/profile";
    }

    @RequestMapping(value = "findWorker", method = RequestMethod.GET)
    private String findWorker(HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        return "pages/findWorker";
    }


    @RequestMapping(value = "findWorkerById", params = "id", method = RequestMethod.GET)
    @ResponseBody
    private Worker findWorkerByValue(@RequestParam("id") Long id) {
        return workerDao.getWorkerById(id);
    }

    @RequestMapping(value = "allWorker", method = RequestMethod.GET)
    private String getAllWorker(HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(IConstants.ALL_WORKERS, workerDao.getAllWorkers());
        return "pages/allWorker";
    }
}
