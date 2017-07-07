package com.erp.system.dto;

/**
 * Created by klinster on 08.07.2017.
 */
public class ProfileUserDTO {
    private String position;
    private String employmentStatus;
    private String department;
    private String telephone;
    private String email;

    public ProfileUserDTO(String position, String employmentStatus, String department, String telephone, String email) {
        this.position = position;
        this.employmentStatus = employmentStatus;
        this.department = department;
        this.telephone = telephone;
        this.email = email;
    }

    public ProfileUserDTO() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
