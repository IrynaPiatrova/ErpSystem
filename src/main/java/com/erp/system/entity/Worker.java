package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
@Entity()
@Table(name = "workers")
public class Worker implements Serializable {
    @Id
    @Column(name = "id_worker")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idWorker;

    @Column(name = "name", length = 64)
    private String nameWorker;

    @Column(name = "login", length = 64)
    private String login;

    @Column(name = "password", length = 64)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile")
    private Profile idProfile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idWorker")
    private List<ProjectTicket> workerProjectTickets;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idWorker")
    private List<CommentsTicket> workerCommentsTickets;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idWorker")
    private List<TimeVocation> workerTimeVocation;

    public Worker() {
    }

    public Worker(String nameWorker, String login, String password, Profile idProfile) {
        this.nameWorker = nameWorker;
        this.login = login;
        this.password = password;
        this.idProfile = idProfile;
    }

    public long getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(long idWorker) {
        this.idWorker = idWorker;
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

    public void setIdProfile(Profile idProfile) {
        this.idProfile = idProfile;
    }

    public long getIdProfile() {
        return idProfile.getIdProfile();
    }

    public List<ProjectTicket> getWorkerProjectTickets() {
        return workerProjectTickets;
    }

    public void setWorkerProjectTickets(List<ProjectTicket> workerProjectTickets) {
        this.workerProjectTickets = workerProjectTickets;
    }

    public List<CommentsTicket> getWorkerCommentsTickets() {
        return workerCommentsTickets;
    }

    public void setWorkerCommentsTickets(List<CommentsTicket> workerCommentsTickets) {
        this.workerCommentsTickets = workerCommentsTickets;
    }

    public List<TimeVocation> getWorkerTimeVocation() {
        return workerTimeVocation;
    }

    public void setWorkerTimeVocation(List<TimeVocation> workerTimeVocation) {
        this.workerTimeVocation = workerTimeVocation;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "idWorker=" + idWorker +
                ", name='" + nameWorker + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
