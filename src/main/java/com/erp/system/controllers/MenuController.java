package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dto.LoginPassword;
import com.erp.system.dto.ProfileDTO;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import com.erp.system.validators.EditProfileValidator;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
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


    @RequestMapping(value = {"/edit", "/editAdmin"}, method = RequestMethod.GET)
    public String editProfileInit(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(IConstants.PROFILE, session.getAttribute(IConstants.PROFILE_DATA));
        return "pages/editProfile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    //это метод для изменения СВОЕГО профиля (или пользователь меняет свой профиль, или админ свой)
    public String editProfile(@ModelAttribute(IConstants.PROFILE) @Valid ProfileDTO profileDTO,
                              @RequestParam("photo") MultipartFile photo,
                              @RequestParam("answerOnKeyWord") String answerOnKeyWord, HttpSession session, BindingResult result) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        Profile profile = (Profile) session.getAttribute(IConstants.PROFILE_DATA);
        String profileDTOLogin = profileDTO.getWorker().getLogin();
        profileDTO.setEmploymentStatus(profile.getEmploymentStatus());
        profileDTO.setPosition(profile.getPosition());
        profileDTO.setDepartment(profile.getDepartment());
        profileDTO.setWorker(profile.getWorker());
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
        profile.setKeyWord(profileDTO.getKeyWord());
        profile.setAnswerOnKeyWord(answerOnKeyWord);
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

    @RequestMapping(value = "/isLoginExist", method = RequestMethod.GET)
    public String isLoginExist(Model model){
        model.addAttribute("worker", new Worker());
        return "pages/changePassword";
    }

    @RequestMapping(value = "/isLoginExist", method = RequestMethod.POST)
    public String isLoginExist(@ModelAttribute("worker")@Valid Worker worker, BindingResult result, Model model, HttpSession session){
        if (worker.getLogin().isEmpty()){
            result.rejectValue("login","empty.login");
            return "pages/changePassword";
        }
        worker = workerDao.getWorkerByLogin(worker.getLogin());
        if (worker == null) result.rejectValue("login","not.used.login");
        if (result.hasErrors()) return "pages/changePassword";
        if (worker.getProfile().getKeyWord() == null || worker.getProfile().getAnswerOnKeyWord() == null) result.rejectValue("login","exist.keyWord");
        if (result.hasErrors()) return "pages/changePassword";
        model.addAttribute(IConstants.PROFILE, worker.getProfile());
        model.addAttribute("keyWord", worker.getProfile().getKeyWord());
        model.addAttribute(IConstants.LOGED_AS, IConstants.TRUE);
        session.setAttribute("login", worker.getLogin());
        return "pages/changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model, HttpSession session){
        Profile profile = (Profile) session.getAttribute(IConstants.PROFILE_DATA);
        model.addAttribute(IConstants.PROFILE, profile);
        model.addAttribute(IConstants.LOGED_AS, session.getAttribute(IConstants.LOGED_AS));
        if (session.getAttribute(IConstants.LOGED_AS) != null) model.addAttribute("keyWord", profile.getKeyWord());
        return "pages/changePassword";
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String change(@ModelAttribute(IConstants.PROFILE)@Valid ProfileDTO profileDTO,
                         BindingResult result, @RequestParam("repeatNewPassword") String repeatNewPassword,
                         @RequestParam("newPassword") String newPassword, HttpSession session, Model model){
        if (session.getAttribute(IConstants.LOGED_AS) == null) model.addAttribute(IConstants.LOGED_AS, IConstants.TRUE);
        if (newPassword.length()<= 0)result.rejectValue("worker.password", "empty.password");
        if (repeatNewPassword.length() <= 0)result.rejectValue("worker.password", "empty.newPassword");
        if (profileDTO.getAnswerOnKeyWord().length()<= 0) result.rejectValue("answerOnKeyWord","empty.answerOnKeyWord");
        Profile profile;
        if (session.getAttribute(IConstants.LOGED_AS) != null) {
            profile = (Profile) session.getAttribute(IConstants.PROFILE_DATA);
        }else {
            Worker worker = workerDao.getWorkerByLogin((String) session.getAttribute("login"));
            profile = worker.getProfile();
        }
        if (result.hasErrors()) return "pages/changePassword";
        if (!profileDTO.getAnswerOnKeyWord().equals(profile.getAnswerOnKeyWord())) result.rejectValue("answerOnKeyWord", "incorrect.keyWord");
        if (!newPassword.equals(repeatNewPassword)) result.rejectValue("worker.password","notsame.password");
        if (result.hasErrors()) return "pages/changePassword";
        profile.getWorker().setPassword(newPassword);
        profileDao.updateProfile(profile);
        model.addAttribute("successChangePassword", IConstants.TRUE);
        if (session.getAttribute(IConstants.LOGED_AS) != null) {
            return "pages/profile";
        }else {
            model.addAttribute("logPass", new LoginPassword());
            return "pages/index";
        }
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
    public String logOut(HttpSession session) {
        session.setAttribute(IConstants.IS_ADMIN, null);
        session.setAttribute(IConstants.LOGED_AS, null);
        return "redirect:/";
    }
}
