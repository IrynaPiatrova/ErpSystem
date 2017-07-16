package com.erp.system.dto;

import java.util.Date;

/**
 * Created by Roma on 13.07.2017.
 */
public class CommentDTO {
    private String nameWorker;
    private String comment;
    private byte[] photo;
    private Date commentDate;

    public CommentDTO() {
    }

    public CommentDTO(String nameWorker, String comment, byte[] photo, Date commentDate) {
        this.nameWorker = nameWorker;
        this.comment = comment;
        this.photo = photo;
        this.commentDate = commentDate;
    }

    public String getNameWorker() {
        return nameWorker;
    }

    public void setNameWorker(String nameWorker) {
        this.nameWorker = nameWorker;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
