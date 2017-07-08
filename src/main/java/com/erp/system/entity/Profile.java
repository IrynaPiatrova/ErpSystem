package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by klinster on 25.06.2017.
 */
//Поменял дату на тип String, нужно подумать можно ли без изменения
@Entity()
@Table(name = "profiles")
public class Profile implements Serializable {
    @Id
    @Column(name = "id_profile")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProfile;

    @Column(name = "start_date")
    private Date startDateProfile;

    @Column(name = "position", length = 64)
    private String position;

    @Column(name = "employment_status", length = 64)
    private String employmentStatus;

    @Column(name = "department", length = 64)
    private String department;

    @Column(name = "telephone", length = 30)
    private String telephone;

    @Column(name = "email", length = 30)
    private String email;

    @Column(name = "photo")
    private byte[] photo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "profile")
    private Worker worker;

    public Profile() {
    }

    public Profile(Date startDateProfile, String position, String employmentStatus, String department, String telephone, String email, byte[] photo, Worker worker) {
        this.startDateProfile = startDateProfile;
        this.position = position;
        this.employmentStatus = employmentStatus;
        this.department = department;
        this.telephone = telephone;
        this.email = email;
        this.photo = photo;
        this.worker = worker;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }


    @Override
    public String toString() {
        return "Profile{" +
                "idProfile=" + idProfile +
                ", startDateProfile=" + startDateProfile +
                ", position='" + position + '\'' +
                ", employmentStatus='" + employmentStatus + '\'' +
                ", department='" + department + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", worker=" + worker +
                '}';
    }
}
