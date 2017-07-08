package com.erp.system.dto;

/**
 * Created by klinster on 08.07.2017
 */
public class ProfileUserDTO {
    private String telephone;
    private String email;
    //надо еще добавить фото сюда

    public ProfileUserDTO(String telephone, String email) {
        this.telephone = telephone;
        this.email = email;
    }

    public ProfileUserDTO() {
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
