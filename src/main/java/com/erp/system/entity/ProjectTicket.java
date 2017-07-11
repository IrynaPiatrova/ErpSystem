package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by klinster on 25.06.2017.
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
    private Date startTicketDate;

    @Column(name = "end_ticket_date")
    private Date endTicketDate;

    @Column(name = "deadline")
    private Date deadlineTicket;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    private Worker idWorker;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "idProjectTicket")
    private List<CommentsTicket> projectCommentTickets;

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

    public Date getStartTicketDate() {
        return startTicketDate;
    }

    public void setStartTicketDate(Date startTicketDate) {
        this.startTicketDate = startTicketDate;
    }

    public Date getEndTicketDate() {
        return endTicketDate;
    }

    public void setEndTicketDate(Date endTicketDate) {
        this.endTicketDate = endTicketDate;
    }

    public Date getDeadlineTicket() {
        return deadlineTicket;
    }

    public void setDeadlineTicket(Date deadlineTicket) {
        this.deadlineTicket = deadlineTicket;
    }

    public Worker getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(Worker idWorker) {
        this.idWorker = idWorker;
    }

    public List<CommentsTicket> getProjectCommentTickets() {
        return projectCommentTickets;
    }

    public void setProjectCommentTickets(List<CommentsTicket> projectCommentTickets) {
        this.projectCommentTickets = projectCommentTickets;
    }
}
