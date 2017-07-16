package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.controllers.methods.MethodsForControllers;
import com.erp.system.dao.comments.ticket.CommentsTicketDao;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.project.ticket.ProjectTicketDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dto.CommentDTO;
import com.erp.system.entity.CommentsTicket;
import com.erp.system.entity.Profile;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.Worker;
import com.erp.system.validators.NewTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
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
    ProfileDao profileDao;
    @Autowired
    CommentsTicketDao commentsTicketDao;
    @Autowired
    NewTicketValidator newTicketValidator;

    private ArrayList<Worker> listOfWorkers = new ArrayList<>();
    private SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date date = new Date();
    private ArrayList<CommentsTicket> listOfComments = new ArrayList<>();
    private ArrayList<CommentDTO> listOfDTOComments = new ArrayList<>();

    @RequestMapping(value = "/isSuccessAddNewTicket", method = RequestMethod.GET)
    public String isSuccessAddNewTicket(Model model, HttpSession session){
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        model.addAttribute("ticket", new ProjectTicket());
        return "pages/addNewTicket";
    }

    @RequestMapping(value = "/isSuccessAddNewTicket", method = RequestMethod.POST)
    public String isSuccessAddNewTicket(@ModelAttribute("ticket")@Valid ProjectTicket projectTicket,
                                        BindingResult result, @RequestParam("deadlineDate") String deadlineDate, HttpSession session) throws ParseException {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
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
        String idWorker = nameWorker.substring(0,nameWorker.indexOf("."));
        date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Worker worker = workerDao.getWorkerById(Long.parseLong(idWorker));
        ProjectTicket projectTicket = projectTicketDao.getProjectTicketById(idTicket);
        projectTicket.setStatusProjectTicket("in_progress");
        projectTicket.setStartTicketDate(sqlDate);
        projectTicket.setWorker(worker);
        System.out.println(projectTicket);
        projectTicketDao.updateProjectTicket(projectTicket);
        return "pages/main";
    }
    @RequestMapping(value = "/allTickets", method = RequestMethod.GET)
    public String allTickets(Model model,HttpSession session){
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        ArrayList<ProjectTicket> listOfTickets;
        if (MethodsForControllers.isAdmin(session)) {
            model.addAttribute("ticket", new ProjectTicket());
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getAllProjectTickets();
            model.addAttribute("collectionTickets", listOfTickets);
            return "pages/allTickets";
        }else {
            Worker worker = workerDao.getWorkerByLogin((String) session.getAttribute(IConstants.LOGED_AS));
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getTicketsByIdWorker(worker);
            model.addAttribute(IConstants.COLLECTION_TICKETS, listOfTickets);
            model.addAttribute("ticket", new ProjectTicket());
            return "pages/allTickets";
        }
    }
    @RequestMapping(value = "/allTickets", method = RequestMethod.POST)
    public String allTickets(Model model,@RequestParam("statusProject") String status,HttpSession session) throws ServletException, IOException {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        ArrayList<ProjectTicket> listOfTickets;
        if (MethodsForControllers.isAdmin(session)){//если админ то отобразить все тикеты
            if (status.equals("all tickets")) {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getAllProjectTickets();
            }else{
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getTicketsByStatus(status);
            }
        }else {//если не админ, то через сессию получаем логин,
            // а через логин получаем все тикеты того кто авторизовался
            Worker worker = workerDao.getWorkerByLogin((String) session.getAttribute(IConstants.LOGED_AS));
            if (status.equals("all tickets")) {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getTicketsByIdWorker(worker);
            }else{
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getTicketsByIdWorkerAndStatus(worker,status);
            }
        }
        model.addAttribute("collectionTickets",listOfTickets);
        return "pages/allTickets";
    }
    @RequestMapping(value = "/chooseTicket{var}")
    public String chooseTicket(@PathVariable("var") long var, Model model, HttpSession session){
        listOfDTOComments.clear();//здесь очищаются список с комментариями,
        // потому что при обновлении страницы они дублируются
        listOfWorkers = (ArrayList<Worker>) workerDao.getAllWorkers();
        listOfComments = (ArrayList<CommentsTicket>) commentsTicketDao.getCommentsTicketByIdTicket(var);
        ProjectTicket projectTicket = projectTicketDao.getProjectTicketById(var);
        for (CommentsTicket m: listOfComments){
            Profile profile = profileDao.getProfileById(m.getIdWorker().getProfile().getIdProfile());
            listOfDTOComments.add(new CommentDTO(m.getIdWorker().getNameWorker(),m.getComment(),profile.getPhoto()));
        }
        model.addAttribute(IConstants.IS_WORKER_ON_TICKET_CHOOSE,isWorkerChosen(projectTicket.getWorker()));
        model.addAttribute(IConstants.IS_TICKET_FINISHED,isStatusNotFinish(projectTicket.getStatusProjectTicket()));
        //две модели выше реализуют методы(они лежат сразу после этого метода), которые возвращают
        //boolean, и в зависимости от того, что вернется рисуется chooseTicket.jsp
        model.addAttribute("collectionWorkers", listOfWorkers);
        model.addAttribute("chosenTicket",projectTicketDao.getProjectTicketById(var));
        model.addAttribute("workerPhoto",session.getAttribute(IConstants.PHOTO));
        model.addAttribute("collectionOfComments",listOfDTOComments);
        return "pages/chooseTicket";
    }

    public boolean isStatusNotFinish(String status){
        return !(status.equals("ready_for_testing") || status.equals("finished"));
    }
    public boolean isWorkerChosen(Worker worker){
        return worker == null;
    }

    @RequestMapping(value = "/writeComment", method = RequestMethod.POST)
    public String writeComment(@RequestParam("text_comment") String comment,
                               @RequestParam("idTicket") long idTicket,HttpSession session,Model model){
        date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Worker worker = workerDao.getWorkerByLogin((String) session.getAttribute(IConstants.LOGED_AS));
        ProjectTicket projectTicket = projectTicketDao.getProjectTicketById(idTicket);
        CommentsTicket commentsTicket = new CommentsTicket();
        commentsTicket.setComment(comment);
        commentsTicket.setCommentDate(sqlDate);
        commentsTicket.setIdProjectTicket(projectTicket);
        commentsTicket.setIdWorker(worker);
        commentsTicketDao.createCommentsTicket(commentsTicket);
        ArrayList<ProjectTicket> listOfTickets;
        if (MethodsForControllers.isAdmin(session)) {
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getAllProjectTickets();
        }else {
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketDao.getTicketsByIdWorker(worker);
        }
        model.addAttribute("collectionTickets",listOfTickets);
        return "pages/allTickets";//надо придумать как сделать return на эту же страницу
    }

    @RequestMapping(value = "/workerEndTicket", method = RequestMethod.POST)
    public String endTicket(@RequestParam("idTicket") long idTicket,HttpSession session){
        date = new Date();
        ProjectTicket projectTicket = (ProjectTicket) projectTicketDao.getProjectTicketById(idTicket);
        projectTicket.setEndTicketDate(date);
        projectTicket.setStatusProjectTicket("ready_for_testing");
        projectTicketDao.updateProjectTicket(projectTicket);
        return "pages/main";
    }

}
