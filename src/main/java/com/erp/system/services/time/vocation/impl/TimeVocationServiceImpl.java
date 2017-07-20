package com.erp.system.services.time.vocation.impl;

import com.erp.system.constants.ModelConstants;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.time.vocation.TimeVocationDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.entity.TimeVocation;
import com.erp.system.entity.Worker;
import com.erp.system.services.time.vocation.TimeVocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Override
    @Transactional
    public void createTimeVocation(TimeVocation timeVocation, Worker worker) {
        if ("".equals(timeVocation.getEndVocDate())) {
            timeVocation.setEndVocDate(null);
        }
        timeVocation.setWorker(worker);
//        if(ModelConstants.SICK_LEAVE.equals(timeVocation.getType())){    // надо подумать как сделать обновление статуса не сразу а именно в тот день с которого стоит начало... - может через инициализацию на странице main админа
//            worker.getProfile().setEmploymentStatus(ModelConstants.STATUS_PROFILE_ON_SICK_LEAVE);
//        } else {
//            worker.getProfile().setEmploymentStatus(ModelConstants.STATUS_PROFILE_ON_VOCATION);
//        }
//        profileDao.updateProfile(worker.getProfile());
        timeVocationDao.createTimeVocation(timeVocation);
    }

    @Override
    @Transactional
    public void updateTimeVocation(TimeVocation timeVocation) {
        timeVocationDao.updateTimeVocation(timeVocation);
    }

    @Override
    @Transactional
    public List<TimeVocation> getTimeVocationsByIdWorker(long workerId) {
        return timeVocationDao.getTimeVocationsByIdWorker(workerId);
    }
}
