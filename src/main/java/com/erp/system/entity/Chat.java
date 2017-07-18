package com.erp.system.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by klinster on 16.07.2017
 */
@Entity
@Table(name = "chat")
public class Chat implements Serializable {
    @Id
    @Column(name = "id_comment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idComment;

    @Column(name = "comment_date")
    private Date commentDate;

    @Column(name = "comment", length = 500)
    private String comment;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_worker")
    private Worker worker;

    public Chat() {
    }

    public Chat(Date commentDate, String comment, Worker worker) {
        this.commentDate = commentDate;
        this.comment = comment;
        this.worker = worker;
    }

    public long getIdComment() {
        return idComment;
    }

    public void setIdComment(long idComment) {
        this.idComment = idComment;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
