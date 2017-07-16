package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.controllers.methods.MethodsForControllers;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dto.ProfileDTO;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import com.erp.system.services.project.ticket.ProjectTicketService;
import com.erp.system.validators.EditProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    ProjectTicketService projectTicketService;
    @Autowired
    EditProfileValidator editProfileValidator;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String mainPage(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        String login = (String) session.getAttribute(IConstants.LOGED_AS);
        Worker workerByLogin = workerDao.getWorkerByLogin(login);
        Profile profileById = profileDao.getProfileById(workerByLogin.getProfile().getIdProfile());
        byte[] photo = profileById.getPhoto();
//        session.setAttribute(IConstants.PHOTO, photo != null && photo.length > 0 ? photo : null); // если в базе нет фото то по дефолту ссылка на файл в jsp
        session.setAttribute(IConstants.PHOTO, photo);
        model.addAttribute(IConstants.NAME_USER, workerByLogin.getNameWorker());
        model.addAttribute(IConstants.PROFILE, profileById);
        session.setAttribute(IConstants.PROFILE_DATA, profileById);
        session.removeAttribute(IConstants.ADMIN_EDIT_PROFILE);
        return "pages/profile";
    }


    @RequestMapping(value = {"/edit", "/editAdmin"}, method = RequestMethod.GET)
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
        String profileDTOLogin = profileDTO.getWorker().getLogin();
        profileDTO.setEmploymentStatus(profile.getEmploymentStatus());
        profileDTO.setPosition(profile.getPosition());
        profileDTO.setDepartment(profile.getDepartment());
        editProfileValidator.validate(profileDTO, result);
        if (workerDao.isLoginUnique(profileDTOLogin) && !profile.getWorker().getLogin().equals(profileDTOLogin))
            result.rejectValue("worker.login", "exist.login");
        if (result.hasErrors()) return "pages/editProfile";
        try {
            if (photo.getBytes().length > 0) profile.setPhoto(photo.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        profile.setTelephone(profileDTO.getTelephone());
        profile.setEmail(profileDTO.getEmail());
        profile.getWorker().setLogin(profileDTOLogin);
        profile.getWorker().setPassword(profileDTO.getWorker().getPassword());
        profileDao.updateProfile(profile);
        session.setAttribute(IConstants.LOGED_AS, profile.getWorker().getLogin());
        return "redirect:/profile";
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    //этот метод для изменения других полей профиля - меняет ТОЛЬКО админ после выбора из списка пользователей
    public String editProfileByAdmin(@ModelAttribute(IConstants.PROFILE) @Valid ProfileDTO profileDTO, HttpSession session, BindingResult result) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Profile profile = (Profile) session.getAttribute(IConstants.PROFILE_DATA);
        profileDTO.setTelephone(profile.getTelephone());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.getWorker().setLogin(profile.getWorker().getLogin());
        profileDTO.getWorker().setPassword(profile.getWorker().getPassword());
        editProfileValidator.validate(profileDTO, result);
        if (result.hasErrors()) return "pages/editProfile";
        profile.setPosition(profileDTO.getPosition());
        profile.setEmploymentStatus(profileDTO.getEmploymentStatus());
        profile.setDepartment(profileDTO.getDepartment());
        profile.getWorker().setNameWorker(profileDTO.getWorker().getNameWorker());
        profileDao.updateProfile(profile);
        return "redirect:/allWorkers";
    }

    @RequestMapping(value = "/findByIdAndEditWorker", method = RequestMethod.POST)
    public String findByIdAndEditWorker(@RequestParam("idWorker") Long idWorker, HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Worker worker = workerDao.getWorkerById(idWorker);
        Profile profile = profileDao.getProfileById(worker.getProfile().getIdProfile());
        session.setAttribute(IConstants.PROFILE_DATA, profile);
        session.setAttribute(IConstants.ADMIN_EDIT_PROFILE, true);
        return "redirect:/edit";
    }

    @RequestMapping(value = "/findByIdAndShowInfo", method = RequestMethod.POST)
    public String findByIdAndShowInfo(@RequestParam("idWorker") Long idWorker, HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Worker worker = workerDao.getWorkerById(idWorker);
        session.setAttribute(IConstants.COLLECTION_TICKETS, projectTicketService.getWorkerProjectTicketsPerfomance(worker));
        session.setAttribute(IConstants.TEMP_WORKER, worker.getNameWorker());
        return "redirect:/showWorkerInfo";
    }

    @RequestMapping(value = "/showWorkerInfo", method = RequestMethod.GET)
    public String showWorkerInfo(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";// Надо подумать будет ли доступна обычному пользователю инфо о его успеваемости
        model.addAttribute(IConstants.COLLECTION_TICKETS, session.getAttribute(IConstants.COLLECTION_TICKETS));
        return "pages/workerInfo";
    }

    @RequestMapping(value = "/allWorkers", method = RequestMethod.GET)
    public String getAllWorker(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        model.addAttribute(IConstants.ALL_WORKERS, workerDao.getAllWorkers());
        return "pages/allWorkers";
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(HttpSession session) {
        session.setAttribute(IConstants.IS_ADMIN, null);
        session.setAttribute(IConstants.LOGED_AS, null);
        return "redirect:/";
    }

    /**
     * If u click "chat" in menu u will redirect to page "chat.jsp"
     * @param session
     * @return String
     */
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String findWorker(HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        return "pages/chat";
    }
}
