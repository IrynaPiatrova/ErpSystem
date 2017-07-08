package com.erp.system.dto;

/**
 * Created by klinster on 08.07.2017
 */
public class ProfileAdminDTO {
    private String position;
    private String employmentStatus;
    private String department;

    public ProfileAdminDTO(String position, String employmentStatus, String department) {
        this.position = position;
        this.employmentStatus = employmentStatus;
        this.department = department;
    }

    public ProfileAdminDTO() {
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
}
