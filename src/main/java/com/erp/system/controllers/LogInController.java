package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dto.LoginPassword;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import com.erp.system.validators.LoginPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by John on 16.06.2017
 */
@Controller
public class LogInController {
//Рома редиска

    @Autowired
    WorkerDao workerDao;

    @Autowired
    LoginPasswordValidator lpValidator;

    /**
     * return start page 'index.jsp'
     *
     * @param model
     * @return String
     */
    @RequestMapping(value = {"", "/", "/welcome"}, method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute(IConstants.LOG_PASS, new LoginPassword());
        return "pages/index";
    }

    /**
     * @param lp
     * @param result
     * @param model
     * @param request
     * @return String
     */
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String checkUserAuthorization(@ModelAttribute(IConstants.LOG_PASS) @Valid LoginPassword lp,
                                         BindingResult result, Model model, HttpServletRequest request) {
        lpValidator.validate(lp, result);
        if (result.hasErrors()) return "pages/index";
        String isAdmin = IConstants.ADMIN.equals(lp.getLogin()) ? IConstants.TRUE : IConstants.FALSE;
        String login = lp.getLogin();
        Worker workerByLogin = workerDao.getWorkerByLogin(login);
        model.addAttribute(IConstants.IS_ADMIN, isAdmin);
        model.addAttribute(IConstants.NAME_USER, workerByLogin.getNameWorker());
        HttpSession session = request.getSession();
        session.setAttribute(IConstants.LOGED_AS, login);
        session.setAttribute(IConstants.IS_ADMIN, isAdmin);
        return "pages/main";
    }

    /**
     * @param model
     * @param request
     * @return String
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(Model model, HttpServletRequest request) {
        if (!MethodsForControllers.isLogedIn(request)) return "redirect:/";
//        model.addAttribute(IS_ADMIN, MethodsForControllers.getCookieByName(IS_ADMIN, request.getCookies()));
        return "pages/main";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    // Это будет тестовый метод где будем пробовать новые фичи, чтобы не создавать всегда заново для проверки
    public String testMethod(HttpServletRequest request) {
        if (!MethodsForControllers.isLogedIn(request) || !MethodsForControllers.isAdmin(request))
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
}
