package com.erp.system.dao.chat;

import com.erp.system.entity.Chat;

import java.util.List;

/**
 * Created by klinster on 16.07.2017
 */
public interface ChatDao {
    void createComment(Chat chat);

    Chat getCommentById(long idComment);

    List<Chat> getAllComments();
}
