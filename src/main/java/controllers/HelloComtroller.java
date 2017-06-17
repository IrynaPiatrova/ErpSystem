package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by John on 16.06.2017.
 */
@Controller
public class HelloComtroller {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(){
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String getIndexPage(){

        return "redirect:/";
    }
    @RequestMapping(value = "/helloPost", method = RequestMethod.GET)
    public String getIndexPost(){

        return "redirect:/";
    }



    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTestGETPage(){
        return "index";
    }
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String getTestPage(HttpServletRequest request, Model model){
        int arg1 = Integer.valueOf(request.getParameter("arg1"));
        int arg2 = Integer.valueOf(request.getParameter("arg2"));
        model.addAttribute("sum", arg1 + arg2);
        return "index";
    }

}
