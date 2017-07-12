package com.erp.system.dao.project.ticket.impl;

import com.erp.system.dao.project.ticket.ProjectTicketDao;
import com.erp.system.entity.ProjectTicket;
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
public class ProjectTicketDaoImpl implements ProjectTicketDao {

    protected static final Logger LOGGER = Logger.getLogger(ProjectTicketDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * create new ProjectTicket in DB
     * @param projectTicket
     */
    @Override
    public void createProjectTicket(ProjectTicket projectTicket) {
        LOGGER.info("ProjectTicketDaoImpl createProjectTicket start");
        sessionFactory.getCurrentSession().save(projectTicket);
        LOGGER.info("ProjectTicketDaoImpl createProjectTicket end");
    }

    /**
     * update ProjectTicket data
     * @param projectTicket
     */
    @Override
    public void updateProjectTicket(ProjectTicket projectTicket) {
        LOGGER.info("ProjectTicketDaoImpl updateProjectTicket start");
        sessionFactory.getCurrentSession().update(projectTicket);
        LOGGER.info("ProjectTicketDaoImpl updateProjectTicket end");
    }

    /**
     * get ProjectTicket by Id
     * @param projectTicketId
     * @return ProjectTicket
     */
    @Override
    public ProjectTicket getProjectTicketById(long projectTicketId) {
        LOGGER.info("ProjectTicketDaoImpl getProjectTicketById start");
        ProjectTicket projectTicket = sessionFactory.getCurrentSession().get(ProjectTicket.class, projectTicketId);
        LOGGER.info("ProjectTicketDaoImpl getProjectTicketById end");
        return projectTicket;
    }

    /**
     * get all ProjectTickets
     * @return List<ProjectTicket>
     */
    @Override
    public List getAllProjectTickets() {
        LOGGER.info("ProjectTicketDaoImpl getAllProjectTicket start");
        Query query = sessionFactory.getCurrentSession().createQuery("from ProjectTicket");
        LOGGER.info("ProjectTicketDaoImpl getAllProjectTicket end");
        return query.getResultList();
    }

    @Override
    public List getTicketsByStatus(String status) {
        LOGGER.info("ProjectTicketDaoImpl geticketsByStatus start");
        Query query = sessionFactory.getCurrentSession().createQuery("from ProjectTicket where status =:statusTicket");
        query.setParameter("statusTicket", status);
        LOGGER.info("ProjectTicketDaoImpl geticketsByStatus end");
        return query.getResultList();
    }

}
