package com.erp.system.dto;

/**
 * Created by Roma on 13.07.2017.
 */
public class CommentDTO {
    private String nameWorker;
    private String comment;
    private byte[] photo;

    public CommentDTO(String nameWorker, String comment, byte[] photo) {
        this.nameWorker = nameWorker;
        this.comment = comment;
        this.photo = photo;
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
}
