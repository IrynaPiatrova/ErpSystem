package com.erp.system.dao.chat.impl;

import com.erp.system.dao.chat.ChatDao;
import com.erp.system.entity.Chat;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by klinster on 16.07.2017
 */
@Repository
@Transactional
public class ChatDaoImpl implements ChatDao{
    protected static final Logger LOGGER = Logger.getLogger(ChatDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public void createComment(Chat chat) {
        LOGGER.info("ChatDaoImpl create start");
        sessionFactory.getCurrentSession().save(chat);
        LOGGER.info("ChatDaoImpl createWorker end");
    }

    @Override
    public Chat getCommentById(long idComment) {
        LOGGER.info("ChatDaoImpl getCommentById start");
        Chat comment = sessionFactory.getCurrentSession().get(Chat.class, idComment);
        LOGGER.info("ChatDaoImpl getCommentById end");
        return comment;
    }

    @Override
    public List<Chat> getAllComments() {
        LOGGER.info("ChatDaoImpl getCommentById start");
        Query query = sessionFactory.getCurrentSession().createQuery("from Chat");
        LOGGER.info("ChatDaoImpl getCommentById end");
        return (List<Chat>) query.getResultList();
    }
}
