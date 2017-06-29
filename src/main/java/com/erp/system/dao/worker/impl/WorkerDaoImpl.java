package com.erp.system.dao.worker.impl;

import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.entity.Worker;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
@Repository(/*"workerDaoImpl"*/)// дополнительно говорит, что транзакции должны быть
@Transactional
public class WorkerDaoImpl implements WorkerDao {

    protected static final Logger LOGGER = Logger.getLogger(WorkerDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * get all Workers
     * @return List<Worker>
     */
    @Override
    public List<Worker> getAllWorkers() {
        LOGGER.info("WorkerDaoImpl getAllWorkers start");
        Query query = sessionFactory.getCurrentSession().createQuery("from Worker");
        LOGGER.info("WorkerDaoImpl getAllWorkers end");
        return query.getResultList();
    }

    /**
     * create new Worker in DB
     * @param worker
     */
    @Override
    public void createWorker(Worker worker) {
        LOGGER.info("WorkerDaoImpl createWorker start");
        sessionFactory.getCurrentSession().save(worker);
        LOGGER.info("WorkerDaoImpl createWorker end");
    }

    /**
     * cheak Worker Login and Password
     * @param worker
     * @return boolean
     */
    @Override
    public boolean isLoginPasswordValid(Worker worker) {
        LOGGER.info("WorkerDaoImpl isLoginPasswordValid start");
        Query query = sessionFactory.getCurrentSession().createQuery("from Worker where login = :login and password = :password");
        query.setParameter("login", worker.getLogin());
        query.setParameter("password", worker.getPassword());
        //sessionFactory.getCurrentSession().get(Worker.class, worker.getLogin(), worker.getPassword());
        LOGGER.info("WorkerDaoImpl isLoginPasswordValid end");
        return query.getResultList().size() != 0;
    }

    /**
     * update Worker data
     * @param worker
     */
    @Override
    public void updateWorker(Worker worker) {
        LOGGER.info("WorkerDaoImpl updateWorker start");
        sessionFactory.getCurrentSession().update(worker);
        LOGGER.info("WorkerDaoImpl updateWorker end");
    }

    /**
     * delete Worker data
     * @param worker
     */
    @Override
    public void deleteWorker(Worker worker) {
        LOGGER.info("WorkerDaoImpl deleteWorker start");
        Worker loadWorker = sessionFactory.getCurrentSession().load(Worker.class, worker);
        sessionFactory.getCurrentSession().delete(worker);
        sessionFactory.getCurrentSession().flush();
        //sessionFactory.getCurrentSession().delete(worker);
        LOGGER.info("WorkerDaoImpl deleteWorker end");
    }

    /**
     * get Worker By Id
     * @param workerId
     * @return Worker
     */
    @Override
    public Worker getWorkerById(long workerId) {
        LOGGER.info("WorkerDaoImpl getProfileById start");
        Worker worker = sessionFactory.getCurrentSession().get(Worker.class, workerId);
        LOGGER.info("WorkerDaoImpl getProfileById end");
        return worker;
    }
}
