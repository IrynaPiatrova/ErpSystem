package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dto.ProfileDTO;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import com.erp.system.validators.EditProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    EditProfileValidator editProfileValidator;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String mainPage(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        String login = (String) session.getAttribute(IConstants.LOGED_AS);
        Worker workerByLogin = workerDao.getWorkerByLogin(login);
        Profile profileById = profileDao.getProfileById(workerByLogin.getProfile().getIdProfile());
        byte[] photo = profileById.getPhoto();
        session.setAttribute(IConstants.PHOTO, photo != null && photo.length > 0 ? photo : null);
        model.addAttribute(IConstants.NAME_USER, workerByLogin.getNameWorker());
        model.addAttribute(IConstants.PROFILE, profileById);
        session.setAttribute(IConstants.PROFILE_DATA, profileById);
        session.removeAttribute(IConstants.ADMIN_EDIT_PROFILE);
        return "pages/profile";
    }


    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editProfileInit(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(IConstants.PROFILE, session.getAttribute(IConstants.PROFILE_DATA));
        return "pages/editProfile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    //это метод для изменения СВОЕГО профиля (или пользователь меняет свой профиль, или админ свой)
    public String editProfile(@ModelAttribute(IConstants.PROFILE) @Valid ProfileDTO profileDTO, @RequestParam("photo") MultipartFile photo, HttpSession session, BindingResult result) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        Profile profile = (Profile) session.getAttribute(IConstants.PROFILE_DATA);
        profile.setTelephone(profileDTO.getTelephone());
        profile.setEmail(profileDTO.getEmail());
        String profileDTOLogin = profileDTO.getWorker().getLogin();
        profile.getWorker().setPassword(profileDTO.getWorker().getPassword());
        profileDTO.setEmploymentStatus(profile.getEmploymentStatus());
        profileDTO.setPosition(profile.getPosition());
        profileDTO.setDepartment(profile.getDepartment());
        editProfileValidator.validate(profileDTO,result);
        if (workerDao.isLoginUnique(profileDTOLogin) && !profile.getWorker().getLogin().equals(profileDTOLogin)) result.rejectValue("worker.login","exist.login");
        profile.getWorker().setLogin(profileDTOLogin);
        if (result.hasErrors()) return "pages/editProfile";
        try {
            if(photo.getBytes().length > 0) profile.setPhoto(photo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        profileDao.updateProfile(profile);
        session.setAttribute(IConstants.LOGED_AS, profile.getWorker().getLogin());
        return "redirect:/profile";
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    //этот метод для изменения других полей профиля - меняет ТОЛЬКО админ после выбора из списка пользователей
    public String editProfileByAdmin(@ModelAttribute(IConstants.PROFILE) ProfileDTO profileDTO, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Profile profile = new Profile(); //тут надо подумать как передавать из списка пользователей характеристики выбранного пользователя, потом переход на новую страницу с изменением профиля выбранного пользователя
        profile.setPosition(profileDTO.getPosition());
        profile.setEmploymentStatus(profileDTO.getEmploymentStatus());
        profile.setDepartment(profileDTO.getDepartment());
        profileDao.updateProfile(profile);
        return "redirect:/profile";
    }

//    @RequestMapping(value = "/findWorker", method = RequestMethod.GET)
//    public String findWorker(HttpSession session) {
//        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
//        return "pages/findWorker";
//    }


//    @RequestMapping(value = "/findWorkerById", params = "id", method = RequestMethod.GET)
//    @ResponseBody
//    public Worker findWorkerByValue(@RequestParam("id") Long id, HttpSession session, Model model, HttpServletResponse response) {
//        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // эту ошибку потом надо отловить и отправить...
//            return null;
//        }
//        Worker worker = workerDao.getWorkerById(id);
//
//        return workerDao.getWorkerById(id);
//    }

    @RequestMapping(value = "/findWorkerByLogin", method = RequestMethod.POST)
    public String findWorkerByValue(@RequestParam("login") String login, HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Worker workerByLogin = workerDao.getWorkerByLogin(login);
        Profile profileById = profileDao.getProfileById(workerByLogin.getProfile().getIdProfile());
        session.setAttribute(IConstants.PROFILE_DATA, profileById);
        session.setAttribute(IConstants.ADMIN_EDIT_PROFILE, true);
        return "redirect:/edit";
    }

    @RequestMapping(value = "/allWorkers", method = RequestMethod.GET)
    public String getAllWorker(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        model.addAttribute(IConstants.ALL_WORKERS, workerDao.getAllWorkers());
            return "pages/allWorkers";
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(HttpSession session){
        session.setAttribute(IConstants.IS_ADMIN, null);
        session.setAttribute(IConstants.LOGED_AS, null);
        return "redirect:/";
    }
}
