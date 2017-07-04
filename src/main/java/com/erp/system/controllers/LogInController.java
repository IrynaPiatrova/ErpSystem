package com.erp.system.controllers;

import com.erp.system.dto.LoginPassword;
import com.erp.system.entity.Profile;
import com.erp.system.validators.LoginPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by John on 16.06.2017.
 */
@Controller
public class LogInController {
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
        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.POST)
    public String checkUserAuthorization(@ModelAttribute("logPass") @Valid LoginPassword lp, BindingResult result, Model model) {
       // if ((lp.getLogin().equals("") || lp.getPassword().equals(""))) {
        lpValidator.validate(lp, result);
            if(result.hasErrors()){
                return "index";
            }
        //}
        return "pages/next";
    }

    @RequestMapping(value = "/addNewWorker", method = RequestMethod.GET)
    public String addNewWorker(){
        return "pages/addNewWorker";
    }
}
