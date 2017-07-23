package com.erp.system.services.time.vocation.impl;

import com.erp.system.constants.ModelConstants;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.project.ticket.ProjectTicketDao;
import com.erp.system.dao.time.vocation.TimeVocationDao;
import com.erp.system.dao.work.log.WorkLogDao;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.TimeVocation;
import com.erp.system.entity.WorkLog;
import com.erp.system.entity.Worker;
import com.erp.system.services.time.vocation.TimeVocationService;
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
//        timeVocation.setConfirmed(0); // это будет если админ отказал в запросе
        timeVocationDao.createTimeVocation(timeVocation);
    }

    @Override
    @Transactional
    public void updateTimeVocation(TimeVocation timeVocation) {
        timeVocationDao.updateTimeVocation(timeVocation);
    }

    @Override
    @Transactional
    public List<TimeVocation> getTimeVocationsByWorker(Worker worker) {
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
                if (start.compareTo(nowDate) == 0) {
                    if (ModelConstants.SICK_LEAVE.equals(timeVocation.getType())) {
                        timeVocation.getWorker().getProfile().setEmploymentStatus(ModelConstants.STATUS_PROFILE_ON_SICK_LEAVE);
                    } else {
                        timeVocation.getWorker().getProfile().setEmploymentStatus(ModelConstants.STATUS_PROFILE_ON_VOCATION);
                    }
                    profileDao.updateProfile(timeVocation.getWorker().getProfile());
                    WorkLog workLog;
                    if(workLogDao.getWorklogByWorkerStarted(timeVocation.getWorker())!=null){
                        workLog = workLogDao.getWorklogByWorkerStarted(timeVocation.getWorker());
                        long dateStartedLog = oldDateFormat.parse(workLog.getStartLogDate()).getTime();
                        long nowDateLog = nowDate.getTime();
                        workLog.setSpentTime(String.valueOf((nowDateLog-dateStartedLog)/86400000));
                        workLogDao.updateWorkLog(workLog);
                    }
                    ProjectTicket projectTicket;
                    if(projectTicketDao.getTicketsByWorkerAndStatus(timeVocation.getWorker(), ModelConstants.STATUS_IN_PROGRESS).size()!=0){
                        projectTicket = (ProjectTicket) projectTicketDao.getTicketsByWorkerAndStatus(timeVocation.getWorker(), ModelConstants.STATUS_IN_PROGRESS).get(0);
                        projectTicket.setStatusProjectTicket(ModelConstants.STATUS_PAUSED);
                        projectTicketDao.updateProjectTicket(projectTicket);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace(); // залоггировать
            }

        }
        // написать про инициализацию если дата окончания отпуска/болезни закончилась то меняются статусы работника и тикета, записывается в work_log запись новая о раотнике, тикете и времени старта если у него был активный тикет до этого
    }
}
