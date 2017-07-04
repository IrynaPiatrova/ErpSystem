package com.erp.system.dao.time.vocation;

import com.erp.system.entity.TimeVocation;

import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
public interface TimeVocationDao {
    void createTimeVocation(TimeVocation timeVocation);

    void updateTimeVocation(TimeVocation timeVocation);

    List<TimeVocation> getTimeVocationsByIdWorker(long workerId);
}
