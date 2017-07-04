package com.erp.system.dao.time.vocation.impl;

import com.erp.system.dao.time.vocation.TimeVocationDao;
import com.erp.system.entity.TimeVocation;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
public class TimeVocationDaoImpl implements TimeVocationDao {
    protected static final Logger LOGGER = Logger.getLogger(TimeVocationDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * create new Time Vocation in DB
     * @param timeVocation
     */
    @Override
    public void createTimeVocation(TimeVocation timeVocation) {
        LOGGER.info("TimeVocationDaoImpl createTimeVocation start");
        sessionFactory.getCurrentSession().save(timeVocation);
        LOGGER.info("TimeVocationDaoImpl createTimeVocation end");
    }

    /**
     * update Time Vocation data
     * @param timeVocation
     */
    @Override
    public void updateTimeVocation(TimeVocation timeVocation) {
        LOGGER.info("TimeVocationDaoImpl updateTimeVocation start");
        sessionFactory.getCurrentSession().update(timeVocation);
        LOGGER.info("TimeVocationDaoImpl updateTimeVocation end");
    }

    /**
     *
     * @param workerId
     * @return
     */
    @Override
    public List<TimeVocation> getTimeVocationsByIdWorker(long workerId) {
        LOGGER.info("TimeVocationDaoImpl getTimeVocationsByIdWorker start");
        Query query = sessionFactory.getCurrentSession().createQuery("from TimeVocation where idWorker = :idWorker");
        query.setParameter("idWorker", workerId);
        LOGGER.info("TimeVocationDaoImpl getTimeVocationsByIdWorker end");
        return query.getResultList();
    }
}
