package com.erp.system.dao.worker.impl;

import com.erp.system.entity.Worker;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static junit.framework.TestCase.assertEquals;


/**
 * Created by John on 04.07.2017.
 */
@Component
public class WorkerDaoImplTest{
    @Autowired
    WorkerDaoImpl workerDao;

    @Test
    public void getWorkerByLogin() throws Exception {
        Worker workerByLogin = workerDao.getWorkerByLogin("aaa");
        assertEquals("resoult: asd", "asd", workerByLogin.getPassword());
    }

}