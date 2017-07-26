package com.erp.system.services.time.vocation.impl;

import com.erp.system.constants.ModelConstants;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.project.ticket.ProjectTicketDao;
import com.erp.system.dao.time.vocation.TimeVocationDao;
import com.erp.system.dao.work.log.WorkLogDao;
import com.erp.system.entity.*;
import com.erp.system.services.time.vocation.TimeVocationService;
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
@Service("timeVocationService")
public class TimeVocationServiceImpl implements TimeVocationService {
    @Autowired
    TimeVocationDao timeVocationDao;
    @Autowired
    ProfileDao profileDao;
    @Autowired
    WorkLogDao workLogDao;
    @Autowired
    ProjectTicketDao projectTicketDao;

    SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    @Transactional
    public void createTimeVocation(TimeVocation timeVocation, Worker worker) {
        if ("".equals(timeVocation.getEndVocDate())) {
            timeVocation.setEndVocDate(null);
        }
        timeVocation.setWorker(worker);
        timeVocation.setConfirmed(null);
        timeVocationDao.createTimeVocation(timeVocation);
    }

    @Override
    @Transactional
    public void updateTimeVocation(TimeVocation timeVocation) {
        timeVocationDao.updateTimeVocation(timeVocation);
    }

    @Override
    @Transactional
    public List getTimeVocationsByWorker(Worker worker) {
        return timeVocationDao.getTimeVocationsByWorker(worker);
    }

    @Override
    @Transactional
    public void checkStatusWorkers() {
        ArrayList<TimeVocation> listAllConfirmedTimeVacations = (ArrayList<TimeVocation>) timeVocationDao.getAllConfirmedTimeVocations();
        for (TimeVocation timeVocation : listAllConfirmedTimeVacations) {
            try {
                Date start = oldDateFormat.parse(timeVocation.getStartVocDate());
                Date now = Calendar.getInstance().getTime();
                Date nowDate = oldDateFormat.parse(oldDateFormat.format(now));
                Profile profile = timeVocation.getWorker().getProfile();
                if (start.compareTo(nowDate) == 0) { // если наступила дата начала отпуска/болезни
                    if (ModelConstants.SICK_LEAVE.equals(timeVocation.getType())) {
                        profile.setEmploymentStatus(ModelConstants.STATUS_PROFILE_ON_SICK_LEAVE);
                    } else {
                        profile.setEmploymentStatus(ModelConstants.STATUS_PROFILE_ON_VOCATION);
                    }
                    profileDao.updateProfile(timeVocation.getWorker().getProfile());
                    if (workLogDao.getWorklogByWorkerStarted(timeVocation.getWorker()) != null) {
                        WorkLog workLog = workLogDao.getWorklogByWorkerStarted(timeVocation.getWorker());
                        long dateStartedLog = oldDateFormat.parse(workLog.getStartLogDate()).getTime();
                        long nowDateLog = nowDate.getTime();
                        workLog.setSpentTime(String.valueOf((nowDateLog - dateStartedLog) / 86400000));
                        workLogDao.updateWorkLog(workLog);
                    }
                    if (projectTicketDao.getTicketsByWorkerAndStatus(timeVocation.getWorker(), ModelConstants.STATUS_IN_PROGRESS).size() != 0) {
                        ProjectTicket projectTicket = (ProjectTicket) projectTicketDao.getTicketsByWorkerAndStatus(timeVocation.getWorker(), ModelConstants.STATUS_IN_PROGRESS).get(0);
                        projectTicket.setStatusProjectTicket(ModelConstants.STATUS_PAUSED);
                        projectTicketDao.updateProjectTicket(projectTicket);
                    }
                }
                Date end = oldDateFormat.parse(timeVocation.getEndVocDate());
                if (end.compareTo(nowDate) == 0) { // если наступила дата окончания отпуска/болезни
                    if (projectTicketDao.getTicketsByWorkerAndStatus(timeVocation.getWorker(), ModelConstants.STATUS_PAUSED).size() != 0) {
                        profile.setEmploymentStatus(ModelConstants.STATUS_PROFILE_INVOLVED);
                        ProjectTicket projectTicket = (ProjectTicket) projectTicketDao.getTicketsByWorkerAndStatus(timeVocation.getWorker(), ModelConstants.STATUS_PAUSED).get(0);
                        projectTicket.setStatusProjectTicket(ModelConstants.STATUS_IN_PROGRESS);
                        WorkLog workLog = new WorkLog();
                        workLog.setWorker(timeVocation.getWorker());
                        workLog.setProjectTicket(projectTicket);
                        workLog.setStartLogDate(oldDateFormat.format(now));
                        profileDao.updateProfile(profile);
                        projectTicketDao.updateProjectTicket(projectTicket);
                        workLogDao.createWorkLog(workLog);
                    } else {
                        if (projectTicketDao.getTicketsByWorker(timeVocation.getWorker()).size() == 0) {
                            profile.setEmploymentStatus(ModelConstants.STATUS_PROFILE_NOT_INVOLVED);
                            profileDao.updateProfile(profile);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace(); // залоггировать
            }

        }
    }

    @Override
    @Transactional
    public List getAllNotConfirmedTimeVocations() {
        return timeVocationDao.getAllNotConfirmedTimeVocations();
    }

    @Override
    @Transactional
    public TimeVocation getTimeVacationById(long idTimeVacation) {
        return timeVocationDao.getTimeVacationById(idTimeVacation);
    }
}
