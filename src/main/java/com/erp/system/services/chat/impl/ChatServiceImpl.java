package com.erp.system.services.chat.impl;

import com.erp.system.dao.chat.ChatDao;
import com.erp.system.entity.Chat;
import com.erp.system.services.chat.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by klinster on 16.07.2017
 */
@Service("chatService")
public class ChatServiceImpl implements ChatService{
    @Autowired
    ChatDao chatDao;

    @Override
    @Transactional
    public void createComment(Chat chat) {
        chatDao.createComment(chat);
    }

    @Override
    @Transactional
    public Chat getCommentById(long idComment) {
        return chatDao.getCommentById(idComment);
    }

    @Override
    @Transactional
    public List<Chat> getAllComments() {
        return chatDao.getAllComments();
    }
}
