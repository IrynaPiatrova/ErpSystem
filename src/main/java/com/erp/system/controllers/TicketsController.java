package com.erp.system.controllers;

import com.erp.system.constants.ModelConstants;
import com.erp.system.controllers.methods.MethodsForControllers;
import com.erp.system.dto.CommentDTO;
import com.erp.system.entity.CommentsTicket;
import com.erp.system.entity.Profile;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.Worker;
import com.erp.system.services.comments.ticket.CommentsTicketService;
import com.erp.system.services.profile.ProfileService;
import com.erp.system.services.project.ticket.ProjectTicketService;
import com.erp.system.services.worker.WorkerService;
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
import java.util.Calendar;
import java.util.Date;

/**
 * Created by klinster on 18.06.2017
 */
@Controller
public class TicketsController {
    @Autowired
    ProjectTicketService projectTicketService;
    @Autowired
    WorkerService workerService;
    @Autowired
    ProfileService profileService;
    @Autowired
    CommentsTicketService commentsTicketService;
    @Autowired
    NewTicketValidator newTicketValidator;

    private ArrayList<Worker> listOfWorkers = new ArrayList<>();
    private SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date date;
    private ArrayList<CommentsTicket> listOfComments = new ArrayList<>();
    private ArrayList<CommentDTO> listOfDTOComments = new ArrayList<>();

    @RequestMapping(value = "/isSuccessAddNewTicket", method = RequestMethod.GET)
    public String isSuccessAddNewTicket(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        model.addAttribute("ticket", new ProjectTicket());
        return "pages/addNewTicket";
    }

    @RequestMapping(value = "/isSuccessAddNewTicket", method = RequestMethod.POST)
    public String isSuccessAddNewTicket(@ModelAttribute("ticket") @Valid ProjectTicket projectTicket,
                                        BindingResult result, @RequestParam("deadlineDate") String deadlineDate, HttpSession session) throws ParseException {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        newTicketValidator.validate(projectTicket, result);
        if (deadlineDate.length() == 0) result.rejectValue("deadlineTicket", "empty.ticket.deadlineDate");
        if (result.hasErrors()) return "pages/addNewTicket";
        projectTicket.setDeadlineTicket(deadlineDate);
        projectTicketService.createProjectTicket(projectTicket);
        return "pages/main";
    }

    @RequestMapping(value = "/chooseWorkerOnTicket", method = RequestMethod.POST)
    public String chooseWorkerOnTicket(@RequestParam("nameWorker") String nameWorker,
                                       @RequestParam("idTicket") long idTicket) throws ParseException {
        String idWorker = nameWorker.substring(0, nameWorker.indexOf("."));
        Worker worker = workerService.getWorkerById(Long.parseLong(idWorker));
        ProjectTicket projectTicket = projectTicketService.getProjectTicketById(idTicket);
        projectTicket.setStatusProjectTicket("in_progress");
        Date now = Calendar.getInstance().getTime();
        projectTicket.setStartTicketDate(oldDateFormat.format(now));
        projectTicket.setWorker(worker);
        System.out.println(projectTicket);
        projectTicketService.updateProjectTicket(projectTicket);
        return "pages/main";
    }

    @RequestMapping(value = "/allTickets", method = RequestMethod.GET)
    public String allTickets(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        ArrayList<ProjectTicket> listOfTickets;
        if (MethodsForControllers.isAdmin(session)) {
            model.addAttribute("ticket", new ProjectTicket());
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getAllProjectTickets();
            model.addAttribute("collectionTickets", listOfTickets);
            return "pages/allTickets";
        } else {
            Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByIdWorker(worker);
            model.addAttribute(ModelConstants.COLLECTION_TICKETS, listOfTickets);
            model.addAttribute("ticket", new ProjectTicket());
            return "pages/allTickets";
        }
    }

    @RequestMapping(value = "/allTickets", method = RequestMethod.POST)
    public String allTickets(Model model, @RequestParam("statusProject") String status, HttpSession session) throws ServletException, IOException {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        ArrayList<ProjectTicket> listOfTickets;
        if (MethodsForControllers.isAdmin(session)) {//если админ то отобразить все тикеты
            if (status.equals("all tickets")) {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getAllProjectTickets();
            } else {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByStatus(status);
            }
        } else {//если не админ, то через сессию получаем логин,
            // а через логин получаем все тикеты того кто авторизовался
            Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
            if (status.equals("all tickets")) {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByIdWorker(worker);
            } else {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByIdWorkerAndStatus(worker, status);
            }
        }
        model.addAttribute("collectionTickets", listOfTickets);
        return "pages/allTickets";
    }

    @RequestMapping(value = "/chooseTicket{var}")
    public String chooseTicket(@PathVariable("var") long var, Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        listOfDTOComments.clear();//здесь очищаются список с комментариями,
        // потому что при обновлении страницы они дублируются
        listOfWorkers = (ArrayList<Worker>) workerService.getAllWorkers();
        listOfComments = (ArrayList<CommentsTicket>) commentsTicketService.getCommentsTicketByIdTicket(var);
        ProjectTicket projectTicket = projectTicketService.getProjectTicketById(var);
        for (CommentsTicket m : listOfComments) {
            Profile profile = profileService.getProfileById(m.getIdWorker().getProfile().getIdProfile());
            listOfDTOComments.add(new CommentDTO(m.getIdWorker().getNameWorker(), m.getComment(), profile.getPhoto(), m.getCommentDate()));
        }
        model.addAttribute(ModelConstants.IS_WORKER_ON_TICKET_NOT_CHOOSEN, isWorkerNotChosen(projectTicket.getWorker()));
        model.addAttribute(ModelConstants.IS_TICKET_NOT_FINISHED, isStatusNotFinish(projectTicket.getStatusProjectTicket()));
        //две модели выше реализуют методы(они лежат сразу после этого метода), которые возвращают
        //boolean, и в зависимости от того, что вернется рисуется chooseTicket.jsp
        model.addAttribute("collectionWorkers", listOfWorkers);
        model.addAttribute("chosenTicket", projectTicket);
        model.addAttribute("workerPhoto", session.getAttribute(ModelConstants.PHOTO));
        model.addAttribute("collectionOfComments", listOfDTOComments);
        return "pages/chooseTicket";
    }

    public boolean isStatusNotFinish(String status) {
        return !(status.equals("ready_for_testing") || status.equals("finished"));
    }

    public boolean isWorkerNotChosen(Worker worker) {
        return worker == null;
    }

    @RequestMapping(value = "/writeComment", method = RequestMethod.POST)
    public String writeComment(@RequestParam("text_comment") String comment,
                               @RequestParam("idTicket") long idTicket, HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        date = new Date();
        Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
        ProjectTicket projectTicket = projectTicketService.getProjectTicketById(idTicket);
        CommentsTicket commentsTicket = new CommentsTicket();
        commentsTicket.setComment(comment);
        commentsTicket.setCommentDate(date);
        commentsTicket.setIdProjectTicket(projectTicket);
        commentsTicket.setIdWorker(worker);
        commentsTicketService.createCommentsTicket(commentsTicket);
        ArrayList<ProjectTicket> listOfTickets;
        if (MethodsForControllers.isAdmin(session)) {
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getAllProjectTickets();
        } else {
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByIdWorker(worker);
        }
        model.addAttribute("collectionTickets", listOfTickets);
        return "pages/allTickets";//надо придумать как сделать return на эту же страницу
    }

    @RequestMapping(value = "/workerEndTicket", method = RequestMethod.POST)
    public String endTicket(@RequestParam("idTicket") long idTicket, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        ProjectTicket projectTicket = projectTicketService.getProjectTicketById(idTicket);
        Date now = Calendar.getInstance().getTime();
        projectTicket.setEndTicketDate(oldDateFormat.format(now));
        projectTicket.setStatusProjectTicket("ready_for_testing");
        projectTicketService.updateProjectTicket(projectTicket);
        return "pages/main";
    }

}
