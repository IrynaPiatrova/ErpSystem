package com.erp.system.dao.project.ticket;

import com.erp.system.entity.ProjectTicket;

import java.util.List;

/**
 * Created by klinster on 25.06.2017
 */
public interface ProjectTicketDao {
    void createProjectTicket(ProjectTicket projectTicket);

    void updateProjectTicket(ProjectTicket projectTicket);

    ProjectTicket getProjectTicketById(long projectTicketId);

    List getAllProjectTickets();
}
