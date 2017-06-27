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
public class WorkerDaoImpl implements WorkerDao<Worker> {

    protected static final Logger LOGGER = Logger.getLogger(WorkerDaoImpl.class);

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<Worker> getAll() {
        LOGGER.info("WorkerDaoImpl getAll start");
        Query query = sessionFactory.getCurrentSession().createQuery("from Worker");
        LOGGER.info("WorkerDaoImpl getAll end");
        return query.getResultList();
    }

    public void add(Worker worker) {
        LOGGER.info("WorkerDaoImpl add start");
        sessionFactory.getCurrentSession().save(worker);
        LOGGER.info("WorkerDaoImpl add end");
    }

    public boolean isLoginPasswordValid(Worker worker) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Worker where login = :login and password = :password");
        query.setParameter("login", worker.getLogin());
        query.setParameter("password", worker.getPassword());
        return query.getResultList() != null;
    }
   /* @PersistenceContext
    protected EntityManager emf;
    @Override
    public List<Worker> findAll() {
        return emf.createQuery("from Worker w").getResultList();
    }*/
}
