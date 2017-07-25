package com.erp.system.services.comments.ticket.impl;

import com.erp.system.dao.comments.ticket.CommentsTicketDao;
import com.erp.system.entity.CommentsTicket;
import com.erp.system.services.comments.ticket.CommentsTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
@Service("commentsTicketService")
public class CommentsTicketServiceImpl implements CommentsTicketService {
    @Autowired
    CommentsTicketDao commentsTicketDao;

    @Override
    @Transactional
    public void createCommentsTicket(CommentsTicket commentsTicket) {
        commentsTicketDao.createCommentsTicket(commentsTicket);
    }

    @Override
    @Transactional
    public void updateCommentsTicket(CommentsTicket commentsTicket) {
        commentsTicketDao.updateCommentsTicket(commentsTicket);
    }

    @Override
    @Transactional
    public void deleteCommentsTicket(CommentsTicket commentsTicket) {
        commentsTicketDao.deleteCommentsTicket(commentsTicket);
    }

    @Override
    @Transactional
    public CommentsTicket getCommentsTicketById(long commentsTicketId) {
        return commentsTicketDao.getCommentsTicketById(commentsTicketId);
    }

    @Override
    @Transactional
    public List getAllCommentsTickets() {
        return commentsTicketDao.getAllCommentsTickets();
    }

    @Override
    @Transactional
    public List getCommentsTicketByIdTicket(long idTicket) {
        return commentsTicketDao.getCommentsTicketByIdTicket(idTicket);
    }
}
