package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.dao.project.ticket.ProjectTicketDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.Worker;
import com.erp.system.validators.NewTicketValidator;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by klinster on 18.06.2017.
 */
@Controller
public class TicketsController {
    @Autowired
    ProjectTicketDao projectTicketDao;
    @Autowired
    WorkerDao workerDao;
    @Autowired
    NewTicketValidator newTicketValidator;

    Worker worker = new Worker();
    ProjectTicket projectTicket = new ProjectTicket();
    private ArrayList<ProjectTicket> listOfTickets = new ArrayList<>();
    private ArrayList<Worker> listOfWorkers = new ArrayList<>();
    private SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();

    @RequestMapping(value = "/isSuccessAddNewTicket", method = RequestMethod.GET)
    public String isSuccessAddNewTicket(Model model){
        model.addAttribute("ticket", new ProjectTicket());
        return "pages/addNewTicket";
    }

    @RequestMapping(value = "/isSuccessAddNewTicket", method = RequestMethod.POST)
    public String isSuccessAddNewTicket(@ModelAttribute("ticket")@Valid ProjectTicket projectTicket,
                                        BindingResult result, @RequestParam("deadlineDate") String deadlineDate) throws ParseException {
        newTicketValidator.validate(projectTicket,result);
        if (deadlineDate.length() == 0) result.rejectValue("deadlineTicket", "empty.ticket.deadlineDate");
        if (result.hasErrors()) return "pages/addNewTicket";
        date = oldDateFormat.parse(deadlineDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        projectTicket.setDeadlineTicket(sqlDate);
        projectTicketDao.createProjectTicket(projectTicket);
        return "pages/main";
    }
    @RequestMapping(value = "/chooseWorkerOnTicket", method = RequestMethod.POST)
    public String chooseWorkerOnTicket(@RequestParam("nameWorker") String nameWorker,
                                       @RequestParam("idTicket") long idTicket) throws ParseException {
        //if (nameWorker.length() == 0) result.rejectValue("nameWorker","empty.nameWorker");//Нужно сделать проверку на пустое имя
        String idWorker = nameWorker.substring(0,nameWorker.indexOf("."));
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        worker = workerDao.getWorkerById(Long.parseLong(idWorker));
        projectTicket = projectTicketDao.getProjectTicketById(idTicket);
        projectTicket.setStatusProjectTicket("in_progress");
        projectTicket.setStartTicketDate(sqlDate);
        projectTicket.setIdWorker(worker);
        System.out.println(projectTicket);
        projectTicketDao.updateProjectTicket(projectTicket);
        return "pages/main";
    }
    @RequestMapping(value = "/allTickets", method = RequestMethod.GET)
    public String allTickets(Model model){
        model.addAttribute("ticket", new ProjectTicket());
        listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getAllProjectTickets();
        model.addAttribute("collectionTickets",listOfTickets);
        return "pages/allTickets";
    }
    @RequestMapping(value = "/allTickets", method = RequestMethod.POST)
    public String allTickets(Model model,@RequestParam("statusProject") String status) throws ServletException, IOException {
        listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getTicketsByStatus(status);
        model.addAttribute("collectionTickets",listOfTickets);
        return "pages/allTickets";
    }
    @RequestMapping(value = "/chooseTicket{var}")
    public String chooseTicket(@PathVariable("var") long var, Model model){
        String isChooseTicket = IConstants.TRUE;
        listOfWorkers = (ArrayList<Worker>) workerDao.getAllWorkers();
        model.addAttribute("collectionWorkers", listOfWorkers);
        model.addAttribute(IConstants.IS_CHOOSE_TICKET, isChooseTicket);
        model.addAttribute("collectionTickets",listOfTickets);
        model.addAttribute("chosenTicket",projectTicketDao.getProjectTicketById(var));
        return "pages/allTickets";
    }

}
