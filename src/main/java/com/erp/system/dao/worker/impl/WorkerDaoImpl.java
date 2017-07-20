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
 * Created by klinster on 25.06.2017
 */
@Repository// дополнительно говорит, что транзакции должны быть
@Transactional
public class WorkerDaoImpl implements WorkerDao {

    protected static final Logger LOGGER = Logger.getLogger(WorkerDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;


    /**
     * create new Worker in DB
     *
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
     *
     * @param login
     * @param password
     * @return boolean
     */
    @Override
    public boolean isLoginPasswordValid(String login, String password) {
        LOGGER.info("WorkerDaoImpl isLoginPasswordValid start");
        Query query = sessionFactory.getCurrentSession().createQuery("from Worker where login = :login and password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        //sessionFactory.getCurrentSession().get(Worker.class, worker.getLogin(), worker.getPassword());
        LOGGER.info("WorkerDaoImpl isLoginPasswordValid end");
        return query.getResultList().size() != 0;
    }

    @Override
    public boolean isLoginUnique(String profileLogin) {
        LOGGER.info("ProfileDaoImpl getLogin start");
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Worker WHERE login = :login");
        query.setParameter("login", profileLogin);
        LOGGER.info("ProfileDaoImpl getLogin end");
        return query.getResultList().size() != 0;
    }


    /**
     * update Worker data
     *
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
     *
     * @param worker
     */
    @Override
    public void deleteWorker(Worker worker) {
        LOGGER.info("WorkerDaoImpl deleteWorker start");
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Worker where idWorker = :idWorker");
        query.setParameter("idWorker", worker.getIdWorker());
        query.executeUpdate();
        LOGGER.info("WorkerDaoImpl deleteWorker end");
    }

    /**
     * get Worker By Id
     *
     * @param workerId
     * @return Worker
     */
    @Override
    public Worker getWorkerById(long workerId) {
        LOGGER.info("WorkerDaoImpl getWorkerById start");
        Worker worker = sessionFactory.getCurrentSession().get(Worker.class, workerId);
        LOGGER.info("WorkerDaoImpl getWorkerById end");
        return worker;
    }

    /**
     * get Worker By login
     *
     * @param workerLogin
     * @return Worker
     */
    @Override
    public Worker getWorkerByLogin(String workerLogin) {
        LOGGER.info("WorkerDaoImpl getWorkerByLogin start");
        Query query = sessionFactory.getCurrentSession().createQuery("from Worker where login = :login");
        query.setParameter("login", workerLogin);
        if (query.getResultList().size()>0) {
            Worker worker = (Worker) query.getResultList().get(0);
            LOGGER.info("WorkerDaoImpl getWorkerByLogin end");
            return worker;
        }else {
            LOGGER.info("WorkerDaoImpl getWorkerByLogin end");
            return null;
        }
    }

    /**
     * get all Workers
     *
     * @return List<Worker>
     */
    @Override
    public List<Worker> getAllWorkers() {
        LOGGER.info("WorkerDaoImpl getAllWorkers start");
        Query query = sessionFactory.getCurrentSession().createQuery("from Worker");
        LOGGER.info("WorkerDaoImpl getAllWorkers end");
        return (List<Worker>) query.getResultList();
    }

}
