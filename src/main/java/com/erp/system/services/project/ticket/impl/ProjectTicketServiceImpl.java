package com.erp.system.services.project.ticket.impl;

import com.erp.system.constants.ModelConstants;
import com.erp.system.dao.comments.ticket.CommentsTicketDao;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.project.ticket.ProjectTicketDao;
import com.erp.system.dao.work.log.WorkLogDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dto.ProjectTicketDTO;
import com.erp.system.entity.CommentsTicket;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.WorkLog;
import com.erp.system.entity.Worker;
import com.erp.system.services.project.ticket.ProjectTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    WorkLogDao workLogDao;
    @Autowired
    WorkerDao workerDao;

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
    @Transactional // метод получения таблицы тикетов сотрудника с указанием успешности выполнения
    public List getWorkerProjectTicketsPerfomance(Worker worker) {
        ArrayList<ProjectTicket> listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getTicketsByWorker(worker);
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
        return projectTicketDao.getTicketsByWorker(worker);
    }

    @Override
    @Transactional
    public List getTicketsByIdWorkerAndStatus(Worker worker, String status) {
        return projectTicketDao.getTicketsByWorkerAndStatus(worker, status);
    }

    @Override
    @Transactional // метод завершения тикета сотрудником
    public void performTicket(ProjectTicket projectTicket, Worker worker, CommentsTicket commentsTicket) {
        Date now = Calendar.getInstance().getTime();
        projectTicket.setEndTicketDate(oldDateFormat.format(now));
        projectTicket.setStatusProjectTicket(ModelConstants.STATUS_READY_FOR_TESTING);
        worker.getProfile().setEmploymentStatus(ModelConstants.STATUS_PROFILE_NOT_INVOLVED);
        commentsTicket.setComment(projectTicket.getNameProjectTicket() + " is " + ModelConstants.STATUS_READY_FOR_TESTING);
        commentsTicket.setCommentDate(now);
        commentsTicket.setIdProjectTicket(projectTicket);
        commentsTicket.setIdWorker(worker);
        WorkLog workLog = workLogDao.getWorklogByWorkerStarted(worker);
        try {
            Date nowDate = oldDateFormat.parse(oldDateFormat.format(now));
            long dateStartedLog = oldDateFormat.parse(workLog.getStartLogDate()).getTime();
            long nowDateLog = nowDate.getTime();
            workLog.setSpentTime(String.valueOf((nowDateLog - dateStartedLog) / 86400000));
            workLogDao.updateWorkLog(workLog);
        } catch (ParseException e) {
            e.printStackTrace(); // залоггировать
        }
        profileDao.updateProfile(worker.getProfile());
        commentsTicketDao.createCommentsTicket(commentsTicket);
        projectTicketDao.updateProjectTicket(projectTicket);

    }

    @Override // метод окончательного завершения тикета админом
    @Transactional
    public void finishTicket(ProjectTicket projectTicket, Worker worker, CommentsTicket commentsTicket) {
        Date now = Calendar.getInstance().getTime();
        projectTicket.setStatusProjectTicket(ModelConstants.STATUS_FINISHED);
        commentsTicket.setComment(projectTicket.getNameProjectTicket() + " is " + ModelConstants.STATUS_FINISHED);
        commentsTicket.setCommentDate(now);
        commentsTicket.setIdProjectTicket(projectTicket);
        commentsTicket.setIdWorker(worker);
        commentsTicketDao.createCommentsTicket(commentsTicket);
        projectTicketDao.updateProjectTicket(projectTicket);
    }

    @Override // метод отклонения завершения тикета аадмином
    @Transactional
    public void rejectFinishingTicket(ProjectTicket projectTicket, Worker admin, CommentsTicket commentsTicket) {
        Date now = Calendar.getInstance().getTime();
        projectTicket.setStatusProjectTicket(ModelConstants.STATUS_IN_PROGRESS);
        Worker worker = projectTicket.getWorker();
        commentsTicket.setComment(projectTicket.getNameProjectTicket() + " is not " + ModelConstants.STATUS_FINISHED + ". It sent for revision, "+worker.getNameWorker()+"!");
        commentsTicket.setCommentDate(now);
        commentsTicket.setIdProjectTicket(projectTicket);
        commentsTicket.setIdWorker(admin);
        worker.getProfile().setEmploymentStatus(ModelConstants.STATUS_PROFILE_INVOLVED);
        WorkLog workLog = new WorkLog();
        workLog.setProjectTicket(projectTicket);
        workLog.setWorker(worker);
        workLog.setStartLogDate(oldDateFormat.format(now));
        profileDao.updateProfile(worker.getProfile());
        commentsTicketDao.createCommentsTicket(commentsTicket);
        projectTicketDao.updateProjectTicket(projectTicket);
        workLogDao.createWorkLog(workLog);
    }

    @Override
    @Transactional // метод назначения на тикет сотрудника
    public void appointWorker(Long idTicket, String nameWorker) {
        Date now = Calendar.getInstance().getTime();
        Worker worker = workerDao.getWorkerByLogin(nameWorker);
        ProjectTicket projectTicket = projectTicketDao.getProjectTicketById(idTicket);
        CommentsTicket commentsTicket = new CommentsTicket();
        projectTicket.setStatusProjectTicket(ModelConstants.STATUS_IN_PROGRESS);
        projectTicket.setStartTicketDate(oldDateFormat.format(now));
        projectTicket.setWorker(worker);
        worker.getProfile().setEmploymentStatus(ModelConstants.STATUS_PROFILE_INVOLVED);
        commentsTicket.setComment(projectTicket.getNameProjectTicket() + " is appointed to " + worker.getNameWorker());
        commentsTicket.setCommentDate(now);
        commentsTicket.setIdProjectTicket(projectTicket);
        commentsTicket.setIdWorker(workerDao.getWorkerById(1));
        WorkLog workLog = new WorkLog();
        workLog.setProjectTicket(projectTicket);
        workLog.setWorker(worker);
        workLog.setStartLogDate(oldDateFormat.format(now));
        profileDao.updateProfile(worker.getProfile());
        commentsTicketDao.createCommentsTicket(commentsTicket);
        projectTicketDao.updateProjectTicket(projectTicket);
        workLogDao.createWorkLog(workLog);
    }
}
