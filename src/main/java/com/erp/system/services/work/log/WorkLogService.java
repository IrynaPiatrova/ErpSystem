package com.erp.system.services.work.log;

import com.erp.system.entity.WorkLog;

import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
public interface WorkLogService {
    void createWorkLog(WorkLog workLog);

    void updateWorkLog(WorkLog workLog);

    void deleteWorkLog(WorkLog workLog);

    WorkLog getWorkLogById(long workLogId);

    List getAllWorkLogs();
}
