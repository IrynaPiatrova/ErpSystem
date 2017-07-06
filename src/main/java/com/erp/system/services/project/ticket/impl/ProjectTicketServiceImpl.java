package com.erp.system.services.project.ticket.impl;

import com.erp.system.dao.project.ticket.ProjectTicketDao;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.services.project.ticket.ProjectTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
@Service("projectTicketService")
public class ProjectTicketServiceImpl implements ProjectTicketService {
    @Autowired
    ProjectTicketDao projectTicketDao;

    @Override
    @Transactional
    public void createProjectTicket(ProjectTicket projectTicket) {
        projectTicketDao.createProjectTicket(projectTicket);
    }

    @Override
    @Transactional
    public void updateProjectTicket(ProjectTicket projectTicket) {
        projectTicketDao.updateProjectTicket(projectTicket);
    }

    @Override
    @Transactional
    public ProjectTicket getProjectTicketById(long projectTicketId) {
        return projectTicketDao.getProjectTicketById(projectTicketId);
    }

    @Override
    @Transactional
    public List getAllProjectTickets() {
        return projectTicketDao.getAllProjectTickets();
    }
}
