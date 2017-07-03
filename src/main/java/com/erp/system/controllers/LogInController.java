package com.erp.system.controllers;

import com.erp.system.dto.LoginPassword;
import com.erp.system.validators.LoginPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 16.06.2017.
 */
@Controller
public class LogInController {
    private static final String ADMIN = "admin";
    private static final String TRUE = "true";
    private static final String FALSE = "false";
    private static final String IS_ADMIN = "isAdmin";

    @Autowired
    LoginPasswordValidator lpValidator;

    /**
     * return start page 'index.jsp'
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"", "/", "/welcome"}, method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("logPass", new LoginPassword());
        return "pages/index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String checkUserAuthorization(@ModelAttribute("logPass") @Valid LoginPassword lp,
                                         BindingResult result, Model model
                                        , HttpServletResponse response) {
        lpValidator.validate(lp, result);
        if(result.hasErrors()){
            return "pages/index";
        }
        String isAdmin = ADMIN.equals(lp.getLogin()) ? TRUE : FALSE;
        response.addCookie(new Cookie(IS_ADMIN, isAdmin));
        model.addAttribute(IS_ADMIN, isAdmin);
        return "pages/main";
    }
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(Model model, HttpServletRequest request ) {
        Cookie[] cookies = request.getCookies();
        model.addAttribute(IS_ADMIN, getCookieByName(IS_ADMIN, cookies));
        return "pages/main";
    }

    //надо придумать как его вынести, чт могли использовать в каждлм контроллере:)
    private String getCookieByName(String cName, Cookie[] cookies) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie);
        }
        Cookie firstRequiredCookie = cookieMap.get(cName);
        return firstRequiredCookie.getValue();
    }
}
