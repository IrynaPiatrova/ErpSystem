package com.erp.system.dao.worker;

import com.erp.system.entity.Worker;

import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
public interface WorkerDao {
    void createWorker(Worker worker);

    void updateWorker(Worker worker);

    void deleteWorker(Worker worker);

    Worker getWorkerById(long workerId);

    Worker getWorkerByLogin(String workerLogin);

    List getAllWorkers();

    boolean isLoginPasswordValid(String login, String passowrd);

    boolean isLoginUnique(String profileLogin);

}