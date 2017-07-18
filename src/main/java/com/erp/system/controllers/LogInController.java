package com.erp.system.controllers;

import com.erp.system.constants.ModelConstants;
import com.erp.system.controllers.methods.MethodsForControllers;
import com.erp.system.dto.LoginPassword;
import com.erp.system.entity.Profile;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.Worker;
import com.erp.system.services.profile.ProfileService;
import com.erp.system.services.worker.WorkerService;
import com.erp.system.validators.LoginPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by John on 16.06.2017
 */
@Controller
public class LogInController {
    @Autowired
    WorkerService workerService;
    @Autowired
    LoginPasswordValidator lpValidator;
    @Autowired
    ProfileService profileService;

    /**
     * return start page 'index.jsp'
     *
     * @param model
     * @return String
     */
    @RequestMapping(value = {"", "/", "/welcome"}, method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute(ModelConstants.LOG_PASS, new LoginPassword());
        return "pages/index";
    }

    /**
     * @param lp
     * @param result
     * @param model
     * @param session
     * @return String
     */
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String checkUserAuthorization(@ModelAttribute(ModelConstants.LOG_PASS) @Valid LoginPassword lp,
                                         BindingResult result, Model model, HttpSession session) {
        lpValidator.validate(lp, result);
        if (result.hasErrors()) return "pages/index";
        String isAdmin = ModelConstants.ADMIN.equals(lp.getLogin()) ? ModelConstants.TRUE : ModelConstants.FALSE;
        String login = lp.getLogin();
        Worker workerByLogin = workerService.getWorkerByLogin(login);
        Profile profileById = profileService.getProfileById(workerByLogin.getProfile().getIdProfile());
        byte[] photo = profileById.getPhoto();
        session.setAttribute(ModelConstants.PHOTO, photo != null && photo.length > 0 ? photo : null);
        model.addAttribute(ModelConstants.IS_ADMIN, isAdmin);
        session.setAttribute(ModelConstants.NAME_USER, workerByLogin.getNameWorker());
        session.setAttribute(ModelConstants.LOGED_AS, login);
        session.setAttribute(ModelConstants.IS_ADMIN, isAdmin);
        return "pages/main";
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        return "pages/main";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    // Это будет тестовый метод где будем пробовать новые фичи, чтобы не создавать всегда заново для проверки
    public String testMethod(HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session))
            return "redirect:/";//только админ

        //каждый наш метод должен начинаться с проверки на осуществление авторизации (пять строк выше), а дальше логика метода
        return "";
    }

    /**
     * @param model
     * @return String
     */
    @RequestMapping(value = "/addNewWorker", method = RequestMethod.GET)
    public String addNewWorker(Model model) {
        model.addAttribute("profile", new Profile());
        return "pages/addNewProfile";
    }

    @RequestMapping(value = "/addNewTicket", method = RequestMethod.GET)
    public String addNewTicket(Model model) {
        model.addAttribute("ticket", new ProjectTicket());
        return "pages/addNewTicket";
    }
}
