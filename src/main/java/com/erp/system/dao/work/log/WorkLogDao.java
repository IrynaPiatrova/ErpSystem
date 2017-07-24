package com.erp.system.dao.work.log;

import com.erp.system.entity.WorkLog;
import com.erp.system.entity.Worker;

import java.util.List;

/**
 * Created by klinster on 25.06.2017
 */
public interface WorkLogDao {
    void createWorkLog(WorkLog workLog);

    void updateWorkLog(WorkLog workLog);

    void deleteWorkLog(WorkLog workLog);

    WorkLog getWorkLogById(long workLogId);

    List getAllWorkLogs();

    List getWorklogsByWorker(Worker worker);

    WorkLog getWorklogByWorkerStarted(Worker worker);
}
