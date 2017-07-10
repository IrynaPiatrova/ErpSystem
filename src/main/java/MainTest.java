import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.profile.impl.ProfileDaoImpl;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dao.worker.impl.WorkerDaoImpl;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;

/**
 * Created by klinster on 25.06.2017.
 */

public class MainTest {

    public static void main(String[] args) {
        ApplicationContext ctx = (ApplicationContext) new FileSystemXmlApplicationContext("E:\\Загрузки\\M B G\\TMS\\GitHub\\ErpSystem\\src\\main\\webapp\\WEB-INF\\dispatcher-servlet.xml");
        WorkerDaoImpl workerDao = (WorkerDaoImpl) ctx.getBean("workerDaoImple");
        ProfileDao profileDao = (ProfileDaoImpl) ctx.getBean("profDaoImpl");
//        Worker worker = new Worker();
//        worker.setPassword("111");
//        worker.setLogin("111");
//        worker.setNameWorker("Ирина");
        Profile byId = profileDao.getProfileById(2L);
        System.out.println(byId);
//        worker.setProfile(byId);
//        workerDao.createWorker(worker);
        Worker worker = workerDao.getWorkerById(2);
        System.out.println(worker);
        //worker.setNameWorker("Irina");
       // workerDao.updateWorker(worker);
        workerDao.deleteWorker(worker);
    }
}
