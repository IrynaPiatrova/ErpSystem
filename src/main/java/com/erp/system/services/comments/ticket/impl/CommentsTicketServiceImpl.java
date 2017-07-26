package com.erp.system.services.comments.ticket.impl;

import com.erp.system.dao.comments.ticket.CommentsTicketDao;
import com.erp.system.entity.CommentsTicket;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.Worker;
import com.erp.system.services.comments.ticket.CommentsTicketService;
import com.erp.system.services.project.ticket.ProjectTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
@Service("commentsTicketService")
public class CommentsTicketServiceImpl implements CommentsTicketService {
    @Autowired
    CommentsTicketDao commentsTicketDao;
    @Autowired
    ProjectTicketService projectTicketService;

    @Override
    @Transactional
    public void createCommentsTicket(Long idTicket, String comment, Worker worker) {
        Date date = new Date();
        ProjectTicket projectTicket = projectTicketService.getProjectTicketById(idTicket);
        CommentsTicket commentsTicket = new CommentsTicket();
        commentsTicket.setComment(comment);
        commentsTicket.setCommentDate(date);
        commentsTicket.setIdProjectTicket(projectTicket);
        commentsTicket.setIdWorker(worker);
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
