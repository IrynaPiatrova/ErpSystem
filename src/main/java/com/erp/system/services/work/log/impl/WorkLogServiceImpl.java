package com.erp.system.services.work.log.impl;

import com.erp.system.dao.work.log.WorkLogDao;
import com.erp.system.entity.WorkLog;
import com.erp.system.services.work.log.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
@Service("workLogService")
public class WorkLogServiceImpl implements WorkLogService {
    @Autowired
    WorkLogDao workLogDao;

    @Override
    @Transactional
    public void createWorkLog(WorkLog workLog) {
        workLogDao.createWorkLog(workLog);
    }

    @Override
    @Transactional
    public void updateWorkLog(WorkLog workLog) {
        workLogDao.updateWorkLog(workLog);
    }

    @Override
    @Transactional
    public void deleteWorkLog(WorkLog workLog) {
        workLogDao.deleteWorkLog(workLog);
    }

    @Override
    @Transactional
    public WorkLog getWorkLogById(long workLogId) {
        return workLogDao.getWorkLogById(workLogId);
    }

    @Override
    @Transactional
    public List getAllWorkLogs() {
        return workLogDao.getAllWorkLogs();
    }
}
