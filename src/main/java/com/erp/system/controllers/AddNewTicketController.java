package com.erp.system.controllers;

import com.erp.system.entity.ProjectTicket;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by klinster on 18.06.2017.
 */
@Controller
public class AddNewTicketController {

    @RequestMapping(value = "/isSuccessAddNewTicket", method = RequestMethod.POST)
    public String isSuccessAddNewTicket(@ModelAttribute("ticket")@Valid ProjectTicket projectTicket){
        return "pages/main";
    }
}
