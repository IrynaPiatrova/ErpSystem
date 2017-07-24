package com.erp.system.dao.time.vocation;

import com.erp.system.entity.TimeVocation;
import com.erp.system.entity.Worker;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
public interface TimeVocationDao {
    void createTimeVocation(TimeVocation timeVocation);

    void updateTimeVocation(TimeVocation timeVocation);

    List getTimeVocationsByWorker(Worker worker);

    List getNotConfirmedTimeVocationsByWorker(Worker worker);

    List getAllNotConfirmedTimeVocations();

    List getAllConfirmedTimeVocations();
    TimeVocation getTimeVacationById(long idTimeVacation);
}
