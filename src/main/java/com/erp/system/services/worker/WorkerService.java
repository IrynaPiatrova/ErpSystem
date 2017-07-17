package com.erp.system.services.worker;

import com.erp.system.entity.Worker;

import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
public interface WorkerService {
    void createWorker(Worker worker);

    void updateWorker(Worker worker);

    void deleteWorker(Worker worker);

    Worker getWorkerById(long workerId);

    Worker getWorkerByLogin(String workerLogin);

    List getAllWorkers();

    boolean isLoginPasswordValid(String login, String passowrd);

    boolean isLoginUnique(String profileLogin);

    List getWorkersNotInvolved();
}
