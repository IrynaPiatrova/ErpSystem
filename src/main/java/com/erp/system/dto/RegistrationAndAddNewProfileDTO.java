package com.erp.system.dto;

/**
 * Created by Roma on 04.07.2017.
 */
public class RegistrationAndAddNewProfileDTO {

    private String nameWorker;
    private String login;
    private String password;
    private String StartDateProfile;
    private String position;
    private String department;
    private String employment_status;
    private String telephone;
    private String email;
    private String photo;

    public RegistrationAndAddNewProfileDTO(String nameWorker, String login, String password, String startDateProfile, String position, String department, String employment_status, String telephone, String email, String photo) {
        this.nameWorker = nameWorker;
        this.login = login;
        this.password = password;
        StartDateProfile = startDateProfile;
        this.position = position;
        this.department = department;
        this.employment_status = employment_status;
        this.telephone = telephone;
        this.email = email;
        this.photo = photo;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    public void setNameWorker(String nameWorker) {
        this.nameWorker = nameWorker;
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

    public String getStartDateProfile() {
        return StartDateProfile;
    }

    public void setStartDateProfile(String startDateProfile) {
        StartDateProfile = startDateProfile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getEmployment_status() {
        return employment_status;
    }

    public void setEmployment_status(String employment_status) {
        this.employment_status = employment_status;
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
