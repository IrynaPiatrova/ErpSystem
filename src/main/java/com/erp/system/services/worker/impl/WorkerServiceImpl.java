package com.erp.system.services.worker.impl;

import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.entity.Worker;
import com.erp.system.services.worker.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
@Service("workerService")
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    WorkerDao workerDao;

    @Override
    @Transactional
    public void createWorker(Worker worker) {
        workerDao.createWorker(worker);
    }

    @Override
    @Transactional
    public void updateWorker(Worker worker) {
        workerDao.updateWorker(worker);
    }

    @Override
    @Transactional
    public void deleteWorker(Worker worker) {
        workerDao.deleteWorker(worker);
    }

    @Override
    @Transactional
    public Worker getWorkerById(long workerId) {
        return workerDao.getWorkerById(workerId);
    }

    @Override
    @Transactional
    public Worker getWorkerByLogin(String workerLogin) {
        return workerDao.getWorkerByLogin(workerLogin);
    }

    @Override
    @Transactional
    public List getAllWorkers() {
        return workerDao.getAllWorkers();
    }

    @Override
    @Transactional
    public boolean isLoginPasswordValid(String login, String passowrd) {
        return workerDao.isLoginPasswordValid(login, passowrd);
    }
}
