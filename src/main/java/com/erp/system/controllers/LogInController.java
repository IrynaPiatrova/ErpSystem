package com.erp.system.controllers;

import com.erp.system.constants.ModelConstants;
import com.erp.system.controllers.methods.MethodsForControllers;
import com.erp.system.dto.LoginPasswordDTO;
import com.erp.system.entity.Profile;
import com.erp.system.entity.ProjectTicket;
import com.erp.system.entity.TimeVocation;
import com.erp.system.entity.Worker;
import com.erp.system.services.profile.ProfileService;
import com.erp.system.services.project.ticket.ProjectTicketService;
import com.erp.system.services.time.vocation.TimeVocationService;
import com.erp.system.services.worker.WorkerService;
import com.erp.system.validators.LoginPasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by John on 16.06.2017
 */
@Controller
public class LogInController {
    @Autowired
    WorkerService workerService;
    @Autowired
    TimeVocationService timeVocationService;
    @Autowired
    ProfileService profileService;
    @Autowired
    ProjectTicketService projectTicketService;
    @Autowired
    LoginPasswordValidator lpValidator;

    /**
     * return start page 'index.jsp'
     *
     * @param model
     * @return String
     */
    @RequestMapping(value = {"", "/", "/welcome"}, method = RequestMethod.GET)
    public String indexPage(Model model) {
        model.addAttribute(ModelConstants.LOG_PASS, new LoginPasswordDTO());
        return "pages/index";
    }

    /**
     * @param lp
     * @param result
     * @param model
     * @param session
     * @return String
     */
    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String checkUserAuthorization(@ModelAttribute(ModelConstants.LOG_PASS) @Valid LoginPasswordDTO lp,
                                         BindingResult result, Model model, HttpSession session) {
        lpValidator.validate(lp, result);
        if (result.hasErrors()) return "pages/index";
        String isAdmin = ModelConstants.ADMIN.equals(lp.getLogin()) ? ModelConstants.TRUE : ModelConstants.FALSE;
        String login = lp.getLogin();
        Worker workerByLogin = workerService.getWorkerByLogin(login);
        Profile profileById = profileService.getProfileById(workerByLogin.getProfile().getIdProfile());
        byte[] photo = profileById.getPhoto();
        session.setAttribute(ModelConstants.PHOTO, photo != null && photo.length > 0 ? photo : null);
        model.addAttribute(ModelConstants.IS_ADMIN, isAdmin);
        session.setAttribute(ModelConstants.NAME_USER, workerByLogin.getNameWorker());
        session.setAttribute(ModelConstants.LOGED_AS, login);
        session.setAttribute(ModelConstants.IS_ADMIN, isAdmin);
        return "redirect: /main";
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String mainPage(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        timeVocationService.checkStatusWorkers();
        ArrayList<ProjectTicket> listOfTickets;
        ArrayList<TimeVocation> listOfTimeVacations;
        Worker workerByLogin = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
        if (MethodsForControllers.isAdmin(session)) {
            listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByStatus(ModelConstants.STATUS_READY_FOR_TESTING);
            listOfTickets.addAll(projectTicketService.getTicketsByStatus(ModelConstants.STATUS_OPENED));
            listOfTimeVacations = (ArrayList<TimeVocation>) timeVocationService.getAllNotConfirmedTimeVocations();
            model.addAttribute(ModelConstants.COLLECTION_TICKETS, listOfTickets);
            model.addAttribute(ModelConstants.COLLECTION_VACATION, listOfTimeVacations);
            return "pages/main";
        }
        listOfTickets = (ArrayList<ProjectTicket>) projectTicketService.getTicketsByIdWorkerAndStatus(workerByLogin, ModelConstants.STATUS_IN_PROGRESS);
        model.addAttribute(ModelConstants.COLLECTION_TICKETS, listOfTickets);
        model.addAttribute(ModelConstants.VACATION, new TimeVocation());
        return "pages/main";
    }

    /**
     * @param model
     * @return String
     */
    @RequestMapping(value = "/addNewWorker", method = RequestMethod.GET)
    public String addNewWorker(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(ModelConstants.PROFILE, new Profile());
        return "pages/addNewProfile";
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/addNewTicket", method = RequestMethod.GET)
    public String addNewTicket(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(ModelConstants.TICKET, new ProjectTicket());
        return "pages/addNewTicket";
    }
}
