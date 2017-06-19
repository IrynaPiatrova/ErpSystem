package controllers;

import entity.LoginPassword;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by John on 16.06.2017.
 */
@Controller
public class HelloComtroller {

    /**
     * return start page 'index.jsp'
     * @param model
     * @return
     */
    @RequestMapping(value = {"", "/", "/welcome"}, method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute("logPass", new LoginPassword());
        return "index";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public String checkUserAuthorization(@ModelAttribute ("logPass") LoginPassword lp,Model model) {
        /*, @RequestParam("username") String username,
        @RequestParam("password") String password,*/
        if((lp.getLogin() == null || lp.getPussword() == null)){
            return "redirect:/welcome";
        }
        return "next";
    }
}
