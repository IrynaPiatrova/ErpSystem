package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by klinster on 25.06.2017
 */
@Entity
@Table(name = "comments_ticket")
public class CommentsTicket implements Serializable {
    @Id
    @Column(name = "id_comment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idCommentTicket;

    //    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project_ticket")
    private ProjectTicket idProjectTicket;

    //    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    private Worker idWorker;

    @Column(name = "comment", length = 500)
    private String comment;

    @Column(name = "comment_date")
    private Date commentDate;

    public long getIdCommentTicket() {
        return idCommentTicket;
    }

    public void setIdCommentTicket(long idCommentTicket) {
        this.idCommentTicket = idCommentTicket;
    }

    public ProjectTicket getIdProjectTicket() {
        return idProjectTicket;
    }

    public void setIdProjectTicket(ProjectTicket idProjectTicket) {
        this.idProjectTicket = idProjectTicket;
    }

    public Worker getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(Worker idWorker) {
        this.idWorker = idWorker;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
