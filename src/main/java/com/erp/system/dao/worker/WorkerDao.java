package com.erp.system.dao.worker;

import com.erp.system.entity.Worker;

import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */
public interface WorkerDao {
    void createWorker(Worker worker);//проверила работает

    void updateWorker(Worker worker);//проверила работает

    void deleteWorker(Worker worker);//не работает :(

    Worker getWorkerById(long workerId);//проверила работает

    List getAllWorkers();//проверила работает

    boolean isLoginPasswordValid(Worker worker);//проверила работает
}