package com.erp.system.services.time.vocation.impl;

import com.erp.system.dao.time.vocation.TimeVocationDao;
import com.erp.system.entity.TimeVocation;
import com.erp.system.services.time.vocation.TimeVocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
@Service("timeVocationService")
public class TimeVocationServiceImpl implements TimeVocationService{
    @Autowired
    TimeVocationDao timeVocationDao;

    @Override
    @Transactional
    public void createTimeVocation(TimeVocation timeVocation) {
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
