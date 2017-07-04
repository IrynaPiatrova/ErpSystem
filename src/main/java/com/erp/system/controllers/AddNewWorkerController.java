package com.erp.system.controllers;

import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.profile.impl.ProfileDaoImpl;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dao.worker.impl.WorkerDaoImpl;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Roma on 18.06.2017.
 */
@Controller
public class AddNewWorkerController {


    //ApplicationContext ctx = (ApplicationContext) new FileSystemXmlApplicationContext("E:\\Загрузки\\M B G\\TMS\\GitHub\\ErpSystem\\src\\main\\webapp\\WEB-INF\\dispatcher-servlet.xml");
    @Autowired
    WorkerDaoImpl workerDao;
    @Autowired
    ProfileDaoImpl profileDao;
//    Worker worker = new Worker();
//    Profile profile = new Profile();

    @RequestMapping(value ="/isSuccessAddNewWorker", method = RequestMethod.POST)
    public String isSuccessAddNewWorker(@ModelAttribute("profile") Profile profile, Worker worker){
        profileDao.createProfile(profile);
        //worker.setIdProfile(profile.getIdProfile());
        System.out.println(worker);
        workerDao.createWorker(worker);
        return "pages/next";
    }
}
