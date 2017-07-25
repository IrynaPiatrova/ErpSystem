package com.erp.system.dto;

/**
 * Created by John on 16.06.2017.
 */

public class LoginPasswordDTO {
    private String login;
    private String password;

    public LoginPasswordDTO() {
    }

    public LoginPasswordDTO(String login, String password) {
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
