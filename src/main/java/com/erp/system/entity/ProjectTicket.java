package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by klinster on 25.06.2017
 */
@Entity()
@Table(name = "project_tickets")
public class ProjectTicket implements Serializable {
    @Id
    @Column(name = "id_project_ticket")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProjectTicket;

    @Column(name = "name", length = 100)
    private String nameProjectTicket;

    @Column(name = "specification", length = 500)
    private String specification;

    @Column(name = "status", length = 64)
    private String statusProjectTicket;

    @Column(name = "start_ticket_date")
    private String startTicketDate;

    @Column(name = "end_ticket_date")
    private String endTicketDate;

    @Column(name = "deadline")
    private String deadlineTicket;

    //    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    private Worker worker;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idProjectTicket")
    private List<CommentsTicket> projectCommentTickets;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ProjectTicket")
    private List<WorkLog> projectWorklog;

    public long getIdProjectTicket() {
        return idProjectTicket;
    }

    public void setIdProjectTicket(long idProjectTicket) {
        this.idProjectTicket = idProjectTicket;
    }

    public String getNameProjectTicket() {
        return nameProjectTicket;
    }

    public void setNameProjectTicket(String nameProjectTicket) {
        this.nameProjectTicket = nameProjectTicket;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getStatusProjectTicket() {
        return statusProjectTicket;
    }

    public void setStatusProjectTicket(String statusProjectTicket) {
        this.statusProjectTicket = statusProjectTicket;
    }

    public String getStartTicketDate() {
        return startTicketDate;
    }

    public void setStartTicketDate(String startTicketDate) {
        this.startTicketDate = startTicketDate;
    }

    public String getEndTicketDate() {
        return endTicketDate;
    }

    public void setEndTicketDate(String endTicketDate) {
        this.endTicketDate = endTicketDate;
    }

    public String getDeadlineTicket() {
        return deadlineTicket;
    }

    public void setDeadlineTicket(String deadlineTicket) {
        this.deadlineTicket = deadlineTicket;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public List<CommentsTicket> getProjectCommentTickets() {
        return projectCommentTickets;
    }

    public void setProjectCommentTickets(List<CommentsTicket> projectCommentTickets) {
        this.projectCommentTickets = projectCommentTickets;
    }

    public List<WorkLog> getProjectWorklog() {
        return projectWorklog;
    }

    public void setProjectWorklog(List<WorkLog> projectWorklog) {
        this.projectWorklog = projectWorklog;
    }

    @Override
    public String toString() {
        return "ProjectTicket{" +
                "idProjectTicket=" + idProjectTicket +
                ", nameProjectTicket='" + nameProjectTicket + '\'' +
                ", specification='" + specification + '\'' +
                ", statusProjectTicket='" + statusProjectTicket + '\'' +
                ", startTicketDate=" + startTicketDate +
                ", endTicketDate=" + endTicketDate +
                ", deadlineTicket=" + deadlineTicket +
                ", worker=" + worker +
                ", projectCommentTickets=" + projectCommentTickets +
                '}';
    }
}
