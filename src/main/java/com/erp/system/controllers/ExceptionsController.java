package com.erp.system.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by klinster on 24.07.2017
 */
@EnableWebMvc
@ControllerAdvice
public abstract class ExceptionsController {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model){
        model.addAttribute("exception", exception);
        return "pages/error/exceptionPage";
    }
}
