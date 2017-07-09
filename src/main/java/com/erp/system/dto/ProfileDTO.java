package com.erp.system.dto;

import com.erp.system.entity.Worker;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by klinster on 08.07.2017
 */
public class ProfileDTO {
    private long idProfile;
    private Date startDateProfile;
    private String telephone;
    private String email;
    private MultipartFile photo;
    private String position;
    private String employmentStatus;
    private String department;
    private Worker worker;

    public ProfileDTO() {
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

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
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

    public long getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(long idProfile) {
        this.idProfile = idProfile;
    }

    public Date getStartDateProfile() {
        return startDateProfile;
    }

    public void setStartDateProfile(Date startDateProfile) {
        this.startDateProfile = startDateProfile;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
