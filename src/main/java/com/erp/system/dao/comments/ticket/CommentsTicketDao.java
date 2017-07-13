package com.erp.system.dao.comments.ticket;

import com.erp.system.entity.CommentsTicket;

import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
public interface CommentsTicketDao {
    void createCommentsTicket(CommentsTicket commentsTicket);

    void updateCommentsTicket(CommentsTicket commentsTicket);

    void deleteCommentsTicket(CommentsTicket commentsTicket);

    CommentsTicket getCommentsTicketById(long commentsTicketId);

    List getAllCommentsTickets();

    List getCommentsTicketByIdTicket(long idTicket);

}
