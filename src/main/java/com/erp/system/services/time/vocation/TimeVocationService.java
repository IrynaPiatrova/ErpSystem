package com.erp.system.services.time.vocation;

import com.erp.system.entity.TimeVocation;

import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
public interface TimeVocationService {
    void createTimeVocation(TimeVocation timeVocation);

    void updateTimeVocation(TimeVocation timeVocation);

    List<TimeVocation> getTimeVocationsByIdWorker(long workerId);
}
