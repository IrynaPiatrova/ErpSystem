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
import java.util.Date;

/**
 * Created by klinster on 18.06.2017
 */
@Controller
public class TicketsController extends ExceptionsController {
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

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/isSuccessAddNewTicket", method = RequestMethod.GET)
    public String isSuccessAddNewTicket(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        model.addAttribute(ModelConstants.TICKET, new ProjectTicket());
        return "pages/addNewTicket";
    }

    /**
     * For creating new ticket
     *
     * @param projectTicket
     * @param result
     * @param deadlineDate
     * @param session
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/isSuccessAddNewTicket", method = RequestMethod.POST)
    public String isSuccessAddNewTicket(@ModelAttribute(ModelConstants.TICKET) @Valid ProjectTicket projectTicket, BindingResult result,
                                        @RequestParam(ModelConstants.DEADLINE_DATE) String deadlineDate, HttpSession session) throws ParseException {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        newTicketValidator.validate(projectTicket, result);
        if (deadlineDate.length() == 0) result.rejectValue("deadlineTicket", "empty.ticket.deadlineDate");
        if (result.hasErrors()) return "pages/addNewTicket";
        Date todayDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOfDeadline = simpleDateFormat.parse(deadlineDate);
        if (dateOfDeadline.compareTo(todayDate) == -1) result.rejectValue("deadlineTicket", "before.ticket.deadlineDate");
        if (result.hasErrors()) return "pages/addNewTicket";
        projectTicket.setDeadlineTicket(deadlineDate);
        projectTicketService.createProjectTicket(projectTicket);
        return "redirect: /main";
    }

    /**
     * For appointing worker to choosen ticket
     *
     * @param nameWorker
     * @param idTicket
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/chooseWorkerOnTicket", method = RequestMethod.POST)
    public String chooseWorkerOnTicket(@RequestParam(ModelConstants.WORKER_NAME) String nameWorker,
                                       @RequestParam(ModelConstants.TICKET_ID) long idTicket, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        projectTicketService.appointWorker(idTicket, nameWorker);
        return "redirect: /main";
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/allTickets", method = RequestMethod.GET)
    public String allTickets(HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        ArrayList<ProjectTicket> listOfTickets;
        if (MethodsForControllers.isAdmin(session)) {
            model.addAttribute(ModelConstants.TICKET, new ProjectTicket());
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getAllProjectTickets();
            model.addAttribute(ModelConstants.COLLECTION_TICKETS, listOfTickets);
            return "pages/allTickets";
        } else {
            Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByIdWorker(worker);
            model.addAttribute(ModelConstants.COLLECTION_TICKETS, listOfTickets);
            model.addAttribute(ModelConstants.TICKET, new ProjectTicket());
            return "pages/allTickets";
        }
    }

    /**
     * For initialization list of tickets
     *
     * @param model
     * @param status
     * @param session
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "/allTickets", method = RequestMethod.POST)
    public String allTickets(Model model, @RequestParam(ModelConstants.STATUS_PROJECT_TICKET) String status, HttpSession session) throws ServletException, IOException {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        ArrayList<ProjectTicket> listOfTickets;
        boolean isStatusEquals = ModelConstants.ALL_TICKETS.equals(status);
        if (MethodsForControllers.isAdmin(session)) {
            if (isStatusEquals) {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getAllProjectTickets();
            } else {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByStatus(status);
            }
        } else {
            Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
            if (isStatusEquals) {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByIdWorker(worker);
            } else {
                listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByIdWorkerAndStatus(worker, status);
            }
        }
        model.addAttribute(ModelConstants.COLLECTION_TICKETS, listOfTickets);
        return "pages/allTickets";
    }

    /**
     * For redirect to page of choosen ticket
     *
     * @param var
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/chooseTicket{var}")
    public String chooseTicket(@PathVariable("var") long var, Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        ArrayList<CommentDTO> listOfDTOComments = new ArrayList<>();
        ArrayList<CommentsTicket> listOfComments = (ArrayList<CommentsTicket>) commentsTicketService.getCommentsTicketByIdTicket(var);
        ProjectTicket projectTicket = projectTicketService.getProjectTicketById(var);
        for (CommentsTicket m : listOfComments) {
            Profile profile = profileService.getProfileById(m.getIdWorker().getProfile().getIdProfile());
            listOfDTOComments.add(new CommentDTO(m.getIdWorker().getNameWorker(), m.getComment(), profile.getPhoto(), m.getCommentDate()));
        }
        model.addAttribute(ModelConstants.IS_WORKER_ON_TICKET_NOT_CHOOSEN, MethodsForControllers.isWorkerNotChosen(projectTicket.getWorker()));
        model.addAttribute(ModelConstants.IS_TICKET_NOT_FINISHED, MethodsForControllers.isStatusNotFinish(projectTicket.getStatusProjectTicket()));
        model.addAttribute(ModelConstants.COLLECTION_WORKERS, workerService.getWorkersNotInvolved());
        model.addAttribute(ModelConstants.CHOOSEN_TICKET, projectTicket);
        model.addAttribute(ModelConstants.COLLECTION_COMMENTS, listOfDTOComments);
        return "pages/chooseTicket";
    }

    /**
     * For writing comment for choosen ticket
     *
     * @param comment
     * @param idTicket
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/writeComment", method = RequestMethod.POST)
    public String writeComment(@RequestParam(ModelConstants.COMMENT_TEXT) String comment,
                               @RequestParam(ModelConstants.TICKET_ID) long idTicket, HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
        commentsTicketService.createCommentsTicket(idTicket, comment, worker);
        ArrayList<ProjectTicket> listOfTickets;
        if (MethodsForControllers.isAdmin(session)) {
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getAllProjectTickets();
        } else {
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByIdWorker(worker);
        }
        model.addAttribute(ModelConstants.COLLECTION_TICKETS, listOfTickets);
        return "pages/allTickets";
    }

    /**
     * For finishing of ticket
     *
     * @param idTicket
     * @param session
     * @return
     */
    @RequestMapping(value = "/finishTicket", method = RequestMethod.POST)
    public String endTicket(@RequestParam(ModelConstants.TICKET_ID) long idTicket, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
        ProjectTicket projectTicket = projectTicketService.getProjectTicketById(idTicket);
        CommentsTicket commentsTicket = new CommentsTicket();
        if (MethodsForControllers.isAdmin(session)) {
            projectTicketService.finishTicket(projectTicket, worker, commentsTicket);
            return "redirect: /main";
        }
        projectTicketService.performTicket(projectTicket, worker, commentsTicket);
        return "redirect: /main";
    }

    /**
     * For rejecting finishing of ticket by admin
     *
     * @param idTicket
     * @param session
     * @return
     */
    @RequestMapping(value = "/rejectFinishingTicket", method = RequestMethod.POST)
    public String rejectFinishingTicket(@RequestParam(ModelConstants.TICKET_ID) long idTicket, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
        ProjectTicket projectTicket = projectTicketService.getProjectTicketById(idTicket);
        CommentsTicket commentsTicket = new CommentsTicket();
        projectTicketService.rejectFinishingTicket(projectTicket, worker, commentsTicket);
        return "redirect: /main";
    }
}
