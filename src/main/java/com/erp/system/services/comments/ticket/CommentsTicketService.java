package com.erp.system.services.comments.ticket;

import com.erp.system.entity.CommentsTicket;
import com.erp.system.entity.Worker;

import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
public interface CommentsTicketService {
    void createCommentsTicket(Long idTicket, String comment, Worker worker);

    void updateCommentsTicket(CommentsTicket commentsTicket);

    void deleteCommentsTicket(CommentsTicket commentsTicket);

    CommentsTicket getCommentsTicketById(long commentsTicketId);

    List getAllCommentsTickets();

    List getCommentsTicketByIdTicket(long idTicket);
}
