package com.erp.system.services.comments.ticket;

import com.erp.system.entity.CommentsTicket;

import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
public interface CommentsTicketService {
    void createCommentsTicket(CommentsTicket commentsTicket);

    void updateCommentsTicket(CommentsTicket commentsTicket);

    void deleteCommentsTicket(CommentsTicket commentsTicket);

    CommentsTicket getCommentsTicketById(long commentsTicketId);

    List getAllCommentsTickets();
}
