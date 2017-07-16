package com.erp.system.services.project.ticket.impl;

import com.erp.system.dao.project.ticket.ProjectTicketDao;
import com.erp.system.dto.ProjectTicketDTO;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.Worker;
import com.erp.system.services.project.ticket.ProjectTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by klinster on 05.07.2017
 */
@Service("projectTicketService")
public class ProjectTicketServiceImpl implements ProjectTicketService {
    @Autowired
    ProjectTicketDao projectTicketDao;

    @Override
    @Transactional
    public void createProjectTicket(ProjectTicket projectTicket) {
        projectTicketDao.createProjectTicket(projectTicket);
    }

    @Override
    @Transactional
    public void updateProjectTicket(ProjectTicket projectTicket) {
        projectTicketDao.updateProjectTicket(projectTicket);
    }

    @Override
    @Transactional
    public ProjectTicket getProjectTicketById(long projectTicketId) {
        return projectTicketDao.getProjectTicketById(projectTicketId);
    }

    @Override
    @Transactional
    public List getAllProjectTickets() {
        return projectTicketDao.getAllProjectTickets();
    }

    @Override
    @Transactional
    public List getWorkerProjectTicketsPerfomance(Worker worker) {
        ArrayList<ProjectTicket> listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getTicketsByIdWorker(worker);
        ArrayList<ProjectTicketDTO> list = new ArrayList<>();
        for(ProjectTicket pr: listOfTickets){
            if(pr.getEndTicketDate() == null){
                list.add(new ProjectTicketDTO(pr.getIdProjectTicket(),pr.getNameProjectTicket(),pr.getSpecification(),pr.getStatusProjectTicket(),"Not finished"));
            } else {
                SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date1 = oldDateFormat.parse(pr.getEndTicketDate());
                    Date date2 = oldDateFormat.parse(pr.getDeadlineTicket());
                    if(date1.compareTo(date2)==1){
                        list.add(new ProjectTicketDTO(pr.getIdProjectTicket(),pr.getNameProjectTicket(),pr.getSpecification(),pr.getStatusProjectTicket(),"Unsuccessfully"));
                    } else list.add(new ProjectTicketDTO(pr.getIdProjectTicket(),pr.getNameProjectTicket(),pr.getSpecification(),pr.getStatusProjectTicket(),"Successfully"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
