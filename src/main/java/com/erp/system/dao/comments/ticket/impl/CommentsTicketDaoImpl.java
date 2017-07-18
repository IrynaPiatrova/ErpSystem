package com.erp.system.dao.comments.ticket.impl;

import com.erp.system.dao.comments.ticket.CommentsTicketDao;
import com.erp.system.entity.CommentsTicket;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by klinster on 25.06.2017
 */
@Repository
@Transactional
public class CommentsTicketDaoImpl implements CommentsTicketDao {
    protected static final Logger LOGGER = Logger.getLogger(CommentsTicketDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public void createCommentsTicket(CommentsTicket commentsTicket) {
        LOGGER.info("CommentsTicketDaoImpl createCommentsTicket start");
        sessionFactory.getCurrentSession().save(commentsTicket);
        LOGGER.info("CommentsTicketDaoImpl createCommentsTicket end");
    }

    @Override
    public void updateCommentsTicket(CommentsTicket commentsTicket) {
        LOGGER.info("CommentsTicketDaoImpl updateCommentsTicket start");
        sessionFactory.getCurrentSession().update(commentsTicket);
        LOGGER.info("CommentsTicketDaoImpl updateCommentsTicket end");
    }

    @Override
    public void deleteCommentsTicket(CommentsTicket commentsTicket) {
        LOGGER.info("CommentsTicketDaoImpl deleteCommentsTicket start");
        Query query = sessionFactory.getCurrentSession().createQuery("delete from CommentsTicket where idCommentTicket = :idCommentTicket");
        query.setParameter("idCommentTicket", commentsTicket.getIdCommentTicket());
        query.executeUpdate();
        LOGGER.info("CommentsTicketDaoImpl deleteCommentsTicket end");
    }

    @Override
    public CommentsTicket getCommentsTicketById(long commentsTicketId) {
        LOGGER.info("CommentsTicketDaoImpl getCommentsTicketById start");
        CommentsTicket commentsTicket = sessionFactory.getCurrentSession().get(CommentsTicket.class, commentsTicketId);
        LOGGER.info("CommentsTicketDaoImpl getCommentsTicketById end");
        return commentsTicket;
    }

    @Override
    public List<CommentsTicket> getAllCommentsTickets() {
        LOGGER.info("WorkerDaoImpl getAllWorkers start");
        Query query = sessionFactory.getCurrentSession().createQuery("from CommentsTicket ");
        LOGGER.info("WorkerDaoImpl getAllWorkers end");
        return query.getResultList();
    }

    @Override
    public List getCommentsTicketByIdTicket(long idTicket) {
        LOGGER.info("WorkerDaoImpl getAllCommentsById start");
        Query query = sessionFactory.getCurrentSession().createQuery("FROM CommentsTicket WHERE id_project_ticket = :idTicket");
        query.setParameter("idTicket", idTicket);
        LOGGER.info("WorkerDaoImpl getAllCommentsById end");
        return query.getResultList();
    }
}
