package com.erp.system.services.project.ticket;

import com.erp.system.entity.CommentsTicket;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.Worker;

import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
public interface ProjectTicketService {
    void createProjectTicket(ProjectTicket projectTicket);

    void updateProjectTicket(ProjectTicket projectTicket);

    ProjectTicket getProjectTicketById(long projectTicketId);

    List getAllProjectTickets();

    List getWorkerProjectTicketsPerfomance(Worker worker);

    List getTicketsByStatus(String status);

    List getTicketsByIdWorker(Worker worker);

    List getTicketsByIdWorkerAndStatus(Worker worker, String status);

    void performTicket(ProjectTicket projectTicket, Worker worker, CommentsTicket commentsTicket);

    void finishTicket(ProjectTicket projectTicket, Worker worker, CommentsTicket commentsTicket);

    void rejectFinishingTicket(ProjectTicket projectTicket, Worker worker, CommentsTicket commentsTicket);

    void appointWorker(Long idTicket, String nameWorker);
}
