package com.erp.system.services.project.ticket.impl;

import com.erp.system.constants.ModelConstants;
import com.erp.system.dao.comments.ticket.CommentsTicketDao;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.project.ticket.ProjectTicketDao;
import com.erp.system.dto.ProjectTicketDTO;
import com.erp.system.entity.CommentsTicket;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.Worker;
import com.erp.system.services.project.ticket.ProjectTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
@Service("projectTicketService")
public class ProjectTicketServiceImpl implements ProjectTicketService {
    @Autowired
    ProjectTicketDao projectTicketDao;
    @Autowired
    CommentsTicketDao commentsTicketDao;
    @Autowired
    ProfileDao profileDao;

    SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

    @Override
    @Transactional
    public List getWorkerProjectTicketsPerfomance(Worker worker) {
        ArrayList<ProjectTicket> listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getTicketsByIdWorker(worker);
        ArrayList<ProjectTicketDTO> list = new ArrayList<>();
        for (ProjectTicket pr : listOfTickets) {
            if (pr.getEndTicketDate() == null) {
                list.add(new ProjectTicketDTO(pr.getIdProjectTicket(), pr.getNameProjectTicket(), pr.getSpecification(), pr.getStatusProjectTicket(), "Not finished"));
            } else {
                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date1 = oldDateFormat.parse(pr.getEndTicketDate());
                    Date date2 = oldDateFormat.parse(pr.getDeadlineTicket());
                    if (date1.compareTo(date2) == 1) {
                        list.add(new ProjectTicketDTO(pr.getIdProjectTicket(), pr.getNameProjectTicket(), pr.getSpecification(), pr.getStatusProjectTicket(), "Unsuccessfully"));
                    } else
                        list.add(new ProjectTicketDTO(pr.getIdProjectTicket(), pr.getNameProjectTicket(), pr.getSpecification(), pr.getStatusProjectTicket(), "Successfully"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    @Transactional
    public List getTicketsByStatus(String status) {
        return projectTicketDao.getTicketsByStatus(status);
    }

    @Override
    @Transactional
    public List getTicketsByIdWorker(Worker worker) {
        return projectTicketDao.getTicketsByIdWorker(worker);
    }

    @Override
    @Transactional
    public List getTicketsByIdWorkerAndStatus(Worker worker, String status) {
        return projectTicketDao.getTicketsByIdWorkerAndStatus(worker, status);
    }

    @Override
    @Transactional
    public void performTicket(ProjectTicket projectTicket, Worker worker, CommentsTicket commentsTicket) {
        Date now = Calendar.getInstance().getTime();
        projectTicket.setEndTicketDate(oldDateFormat.format(now));
        projectTicket.setStatusProjectTicket(ModelConstants.STATUS_READY_FOR_TESTING);
        worker.getProfile().setEmploymentStatus(ModelConstants.STATUS_PROFILE_NOT_INVOLVED);
        commentsTicket.setComment(projectTicket.getNameProjectTicket() + " is " + ModelConstants.STATUS_READY_FOR_TESTING);
        commentsTicket.setCommentDate(now);
        commentsTicket.setIdProjectTicket(projectTicket);
        commentsTicket.setIdWorker(worker);
        profileDao.updateProfile(worker.getProfile());
        commentsTicketDao.createCommentsTicket(commentsTicket);
        projectTicketDao.updateProjectTicket(projectTicket);
    }

    @Override
    @Transactional
    public void appointWorker(ProjectTicket projectTicket, Worker worker, CommentsTicket commentsTicket) {
        Date now = Calendar.getInstance().getTime();
        projectTicket.setStatusProjectTicket(ModelConstants.STATUS_IN_PROGRESS);
        projectTicket.setStartTicketDate(oldDateFormat.format(now));
        projectTicket.setWorker(worker);
        worker.getProfile().setEmploymentStatus(ModelConstants.STATUS_PROFILE_INVOLVED);
        commentsTicket.setComment(projectTicket.getNameProjectTicket() + " is appointed to " + worker.getNameWorker());
        commentsTicket.setCommentDate(now);
        commentsTicket.setIdProjectTicket(projectTicket);
        commentsTicket.setIdWorker(worker);
        profileDao.updateProfile(worker.getProfile());
        commentsTicketDao.createCommentsTicket(commentsTicket);
        projectTicketDao.updateProjectTicket(projectTicket);
    }
}
