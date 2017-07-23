package com.erp.system.dao.time.vocation.impl;

import com.erp.system.dao.time.vocation.TimeVocationDao;
import com.erp.system.entity.TimeVocation;
import com.erp.system.entity.Worker;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by klinster on 25.06.2017
 */
@Repository
@Transactional
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
     * @param worker
     * @return
     */
    @Override
    public List getTimeVocationsByWorker(Worker worker) {
        LOGGER.info("TimeVocationDaoImpl getTimeVocationsByWorker start");
        Query query = sessionFactory.getCurrentSession().createQuery("from TimeVocation where id_worker = :Worker");
        query.setParameter("Worker", worker);
        LOGGER.info("TimeVocationDaoImpl getTimeVocationsByWorker end");
        return query.getResultList();
    }

    @Override
    public List getNotConfirmedTimeVocationsByWorker(Worker worker) {
        LOGGER.info("TimeVocationDaoImpl getNotConfirmedTimeVocationsByWorker start");
        Query query = sessionFactory.getCurrentSession().createQuery("from TimeVocation where id_worker = :Worker AND is_confirmed = null");
        query.setParameter("Worker", worker);
        LOGGER.info("TimeVocationDaoImpl getNotConfirmedTimeVocationsByWorker end");
        return query.getResultList();
    }

    @Override
    public List getAllNotConfirmedTimeVocations() {
        LOGGER.info("TimeVocationDaoImpl getAllNotConfirmedTimeVocations start");
        Query query = sessionFactory.getCurrentSession().createQuery("from TimeVocation where is_confirmed = null");
        LOGGER.info("TimeVocationDaoImpl getAllNotConfirmedTimeVocations end");
        return query.getResultList();
    }

    @Override
    public List getAllConfirmedTimeVocations() {
        LOGGER.info("TimeVocationDaoImpl getAllConfirmedTimeVocations start");
        Query query = sessionFactory.getCurrentSession().createQuery("from TimeVocation where is_confirmed = 1");
        LOGGER.info("TimeVocationDaoImpl getAllConfirmedTimeVocations end");
        return query.getResultList();
    }

    @Override
    public TimeVocation getTimeVacationById(long idTimeVacation) {
        LOGGER.info("TimeVocationDaoImpl getTimeVacationById start");
        TimeVocation timeVocation = sessionFactory.getCurrentSession().get(TimeVocation.class, idTimeVacation);
        LOGGER.info("TimeVocationDaoImpl getTimeVacationById end");
        return timeVocation;
    }
}
