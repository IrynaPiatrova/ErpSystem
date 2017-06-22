package com.erp.system.dto;

import org.springframework.stereotype.Controller;

/**
 * Created by John on 16.06.2017.
 */
//нужно будет переделать в dto
@Controller
public class LoginPassword {
    private String login;
    private String password;

    public LoginPassword() {
    }

    public LoginPassword(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
