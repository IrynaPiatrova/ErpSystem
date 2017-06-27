import com.erp.system.dao.worker.impl.WorkerDaoImpl;
import com.erp.system.entity.Worker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

/**
 * Created by klinster on 25.06.2017.
 */

public class MainTest {

    public static void main(String[] args) {
        ApplicationContext ctx = (ApplicationContext) new FileSystemXmlApplicationContext("D:\\GitHub\\ErpSystem\\src\\main\\webapp\\WEB-INF\\dispatcher-servlet.xml");
        WorkerDaoImpl workerDao = (WorkerDaoImpl) ctx.getBean("workerDaoImple");
        List<Worker> workers = workerDao.getAll();
        for (Worker w : workers) {
            System.out.println(w.toString());
        }
    }
}
