package com.erp.system.dao.work.log.impl;

import com.erp.system.dao.work.log.WorkLogDao;
import com.erp.system.entity.WorkLog;
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
public class WorkLogDaoImpl implements WorkLogDao {

    protected static final Logger LOGGER = Logger.getLogger(WorkLogDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     *  create new WorkLog in DB
     * @param workLog
     */
    @Override
    public void createWorkLog(WorkLog workLog) {
        LOGGER.info("WorkLogDaoImpl createWorkLog start");
        sessionFactory.getCurrentSession().save(workLog);
        LOGGER.info("WorkLogDaoImpl createWorkLog end");
    }

    /**
     * update WorkLog data
     * @param workLog
     */
    @Override
    public void updateWorkLog(WorkLog workLog) {
        LOGGER.info("WorkLogDaoImpl updateWorkLog start");
        sessionFactory.getCurrentSession().update(workLog);
        LOGGER.info("WorkLogDaoImpl updateWorkLog end");
    }

    /**
     * delete WorkLog data
     * @param workLog
     */
    @Override
    public void deleteWorkLog(WorkLog workLog) {
        LOGGER.info("WorkLogDaoImpl updateWorkLog start");
        Query query = sessionFactory.getCurrentSession().createQuery("delete from WorkLog where idWorkLog = :idWorkLog");
        query.setParameter("idWorkLog", workLog.getIdWorkLog());
        query.executeUpdate();
        LOGGER.info("WorkLogDaoImpl updateWorkLog end");
    }

    /**
     * get WorkLog By Id
     * @param workLogId
     * @return WorkLog
     */
    @Override
    public WorkLog getWorkLogById(long workLogId) {
        LOGGER.info("WorkLogDaoImpl getWorkLogById start");
        WorkLog workLog = sessionFactory.getCurrentSession().get(WorkLog.class, workLogId);
        LOGGER.info("WorkLogDaoImpl getWorkLogById end");
        return workLog;
    }

    /**
     * get all WorkLog
     * @return List<WorkLog>
     */
    @Override
    public List getAllWorkLogs() {
        LOGGER.info("WorkerDaoImpl getAllWorkers start");
        Query query = sessionFactory.getCurrentSession().createQuery("from WorkLog");
        LOGGER.info("WorkerDaoImpl getAllWorkers end");
        return query.getResultList();
    }

    @Override
    public List getWorklogsByWorker(Worker worker) {
        LOGGER.info("WorkerDaoImpl getWorklogsByWorker start");
        Query query = sessionFactory.getCurrentSession().createQuery("from WorkLog where id_worker =:worker");
        query.setParameter("worker", worker);
        LOGGER.info("WorkerDaoImpl getWorklogsByWorker end");
        return query.getResultList();
    }

    @Override
    public WorkLog getWorklogByWorkerStarted(Worker worker) {
        LOGGER.info("WorkLogDaoImpl getWorklogByWorkerStarted start");
        Query query = sessionFactory.getCurrentSession().createQuery("from WorkLog where id_worker =:worker AND spent_time = null");
        query.setParameter("worker", worker);
        LOGGER.info("WorkLogDaoImpl getWorklogByWorkerStarted end");
        WorkLog workLog = null;
        if(query.getResultList().size()!=0){
            return (WorkLog) query.getResultList().get(0);
        } else {
            return workLog;
        }
    }

}
