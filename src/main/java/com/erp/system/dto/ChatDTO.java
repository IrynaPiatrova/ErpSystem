package com.erp.system.dto;

/**
 * Created by John on 17.07.2017.
 */
public class ChatDTO {
    private String nameWorker;
    private String comment;
    private byte[] photo;
    private boolean isCurentUser;

    public ChatDTO() {
    }

    public ChatDTO(String nameWorker, String comment, byte[] photo, boolean isCurentUser) {
        this.nameWorker = nameWorker;
        this.comment = comment;
        this.photo = photo;
        this.isCurentUser = isCurentUser;
    }

    public boolean isCurentUser() {
        return isCurentUser;
    }

    public void setCurentUser(boolean curentUser) {
        isCurentUser = curentUser;
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
