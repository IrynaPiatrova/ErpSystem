package controllers;

import entity.LoginPassword;
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
public class LogInComtroller {

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
       // if ((lp.getLogin().equals("") || lp.getPussword().equals(""))) {
            if(result.hasErrors()){
                return "index";
            }
        //}
        return "next";
    }
}
