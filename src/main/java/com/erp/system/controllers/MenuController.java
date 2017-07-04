package com.erp.system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Roma on 18.06.2017.
 */
@Controller
public class MenuController {

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String mainPage(Model model, HttpServletRequest request) {
        Boolean isLogedIn = MethodsForControllers.isLogedIn(request);
        if (!isLogedIn) {
            return "redirect:/";
        }
        return "pages/profile";
    }
}
