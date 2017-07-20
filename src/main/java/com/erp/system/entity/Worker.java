package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by klinster on 25.06.2017
 */
@Entity()
@Table(name = "workers")
public class Worker implements Serializable {
    @Id
    @Column(name = "id_worker")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idWorker;

    @Column(name = "name", length = 50)
    private String nameWorker;

    @Column(name = "login", length = 64)
    private String login;

    @Column(name = "password", length = 64)
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profile")
    private Profile profile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
    private List<ProjectTicket> workerProjectTickets;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idWorker")
    private List<CommentsTicket> workerCommentsTickets;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
    private List<TimeVocation> workerTimeVocation;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
    private List<Chat> workerChat;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "worker")
    private List<Chat> workerWorklog;

    public Worker() {
    }

    public Worker(String nameWorker, String login, String password, Profile profile) {
        this.nameWorker = nameWorker;
        this.login = login;
        this.password = password;
        this.profile = profile;
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

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
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

    public List<Chat> getWorkerChat() {
        return workerChat;
    }

    public void setWorkerChat(List<Chat> workerChat) {
        this.workerChat = workerChat;
    }

    public List<Chat> getWorkerWorklog() {
        return workerWorklog;
    }

    public void setWorkerWorklog(List<Chat> workerWorklog) {
        this.workerWorklog = workerWorklog;
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
