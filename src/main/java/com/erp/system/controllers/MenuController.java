package com.erp.system.controllers;

import com.erp.system.constants.ModelConstants;
import com.erp.system.controllers.methods.MethodsForControllers;
import com.erp.system.dto.ChatDTO;
import com.erp.system.dto.LoginPasswordDTO;
import com.erp.system.dto.ProfileDTO;
import com.erp.system.entity.*;
import com.erp.system.services.chat.ChatService;
import com.erp.system.services.profile.ProfileService;
import com.erp.system.services.project.ticket.ProjectTicketService;
import com.erp.system.services.time.vocation.TimeVocationService;
import com.erp.system.services.worker.WorkerService;
import com.erp.system.validators.EditProfileValidator;
import com.erp.system.validators.TimeVocationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by John on 18.06.2017
 */
@Controller
public class MenuController extends ExceptionsController {
    @Autowired
    WorkerService workerService;
    @Autowired
    ProfileService profileService;
    @Autowired
    ChatService chatDao;
    @Autowired
    TimeVocationService timeVocationService;
    @Autowired
    ProjectTicketService projectTicketService;
    @Autowired
    EditProfileValidator editProfileValidator;
    @Autowired
    TimeVocationValidator timeVocationValidator;

    private List<ChatDTO> chatArrayList = new ArrayList<>();

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String mainPage(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        String login = (String) session.getAttribute(ModelConstants.LOGED_AS);
        Worker workerByLogin = workerService.getWorkerByLogin(login);
        Profile profileById = profileService.getProfileById(workerByLogin.getProfile().getIdProfile());
        byte[] photo = profileById.getPhoto();
        session.setAttribute(ModelConstants.PHOTO, photo);
        model.addAttribute(ModelConstants.NAME_USER, workerByLogin.getNameWorker());
        model.addAttribute(ModelConstants.PROFILE, profileById);
        session.setAttribute(ModelConstants.PROFILE_ID, profileById.getIdProfile());
        session.removeAttribute(ModelConstants.ADMIN_EDIT_PROFILE);
        return "pages/profile";
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = {"/edit", "/editAdmin"}, method = RequestMethod.GET)
    public String editProfileInit(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(ModelConstants.PROFILE, profileService.getProfileById((Long) session.getAttribute(ModelConstants.PROFILE_ID)));
        return "pages/editProfile";
    }

    /**
     * это метод для изменения СВОЕГО профиля (или пользователь меняет свой профиль, или админ свой)
     *
     * @param profileDTO
     * @param photo
     * @param session
     * @param result
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute(ModelConstants.PROFILE) @Valid ProfileDTO profileDTO,
                              @RequestParam(ModelConstants.PHOTO) MultipartFile photo, HttpSession session, BindingResult result) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        Profile profile = profileService.getProfileById((Long) session.getAttribute(ModelConstants.PROFILE_ID));
        String profileDTOLogin = profileDTO.getWorker().getLogin();
        profileDTO.setEmploymentStatus(profile.getEmploymentStatus());
        profileDTO.setPosition(profile.getPosition());
        profileDTO.setDepartment(profile.getDepartment());
        profileDTO.setWorker(profile.getWorker());
        editProfileValidator.validate(profileDTO, result);
        if (workerService.isLoginUnique(profileDTOLogin) && !profile.getWorker().getLogin().equals(profileDTOLogin))
            result.rejectValue("worker.login", "exist.login");
        if (result.hasErrors()) return "pages/editProfile";
        try {
            if (photo.getBytes().length > 0) profile.setPhoto(photo.getBytes());
        } catch (IOException e) {
            e.printStackTrace(); // залоггировать
        }
        profile.setTelephone(profileDTO.getTelephone());
        profile.setEmail(profileDTO.getEmail());
        profile.getWorker().setLogin(profileDTOLogin);
        profileService.updateProfile(profile);
        session.setAttribute(ModelConstants.LOGED_AS, profile.getWorker().getLogin());
        return "redirect:/profile";
    }

    /**
     * этот метод для изменения других полей профиля - меняет ТОЛЬКО админ после выбора из списка пользователей
     *
     * @param profileDTO
     * @param session
     * @param result
     * @return
     */
    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    public String editProfileByAdmin(@ModelAttribute(ModelConstants.PROFILE) @Valid ProfileDTO profileDTO, HttpSession session, BindingResult result) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Profile profile = profileService.getProfileById((Long) session.getAttribute(ModelConstants.PROFILE_ID));
        profileDTO.setTelephone(profile.getTelephone());
        profileDTO.setEmail(profile.getEmail());
        profileDTO.getWorker().setLogin(profile.getWorker().getLogin());
        profileDTO.getWorker().setPassword(profile.getWorker().getPassword());
        editProfileValidator.validate(profileDTO, result);
        if (result.hasErrors()) return "pages/editProfile";
        profile.setPosition(profileDTO.getPosition());
        profile.setEmploymentStatus(profileDTO.getEmploymentStatus());
        profile.setDepartment(profileDTO.getDepartment());
        profile.getWorker().setNameWorker(profileDTO.getWorker().getNameWorker());
        profileService.updateProfile(profile);
        return "redirect:/allWorkers";
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/isLoginExist", method = RequestMethod.GET)
    public String isLoginExist(HttpSession session, Model model) {
        //if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(ModelConstants.WORKER, new Worker());
        return "pages/changePassword";
    }

    /**
     * @param worker
     * @param result
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/isLoginExist", method = RequestMethod.POST)
    public String isLoginExist(@ModelAttribute(ModelConstants.WORKER) @Valid Worker worker, BindingResult result, Model model, HttpSession session) {
        //if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        if (worker.getLogin().isEmpty()) {
            result.rejectValue("login", "empty.login");
            return "pages/changePassword";
        }
        worker = workerService.getWorkerByLogin(worker.getLogin());
        if (worker == null) result.rejectValue("login", "not.used.login");
        if (result.hasErrors()) return "pages/changePassword";
        if (worker.getProfile().getKeyWord() == null || worker.getProfile().getAnswerOnKeyWord() == null)
            result.rejectValue("login", "exist.keyWord");
        if (result.hasErrors()) return "pages/changePassword";
        model.addAttribute(ModelConstants.PROFILE, worker.getProfile());
        model.addAttribute(ModelConstants.KEY_WORD, worker.getProfile().getKeyWord());
        model.addAttribute(ModelConstants.LOGED_AS, ModelConstants.TRUE);
        session.setAttribute(ModelConstants.LOGIN, worker.getLogin());
        return "pages/changePassword";
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(HttpSession session, Model model) {
       // if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        Profile profile = profileService.getProfileById((Long) session.getAttribute(ModelConstants.PROFILE_ID));
        model.addAttribute(ModelConstants.PROFILE, profile);
        model.addAttribute(ModelConstants.LOGED_AS, session.getAttribute(ModelConstants.LOGED_AS));
        if (session.getAttribute(ModelConstants.LOGED_AS) != null)
            model.addAttribute(ModelConstants.KEY_WORD, profile.getKeyWord());
        return "pages/changePassword";
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/changePas", method = RequestMethod.GET)
    public String change(HttpSession session, Model model) {
        //if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        Profile profile;
        if (session.getAttribute(ModelConstants.LOGED_AS) != null) {
            profile = profileService.getProfileById((Long) session.getAttribute(ModelConstants.PROFILE_ID));
        } else {
            Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGIN));
            profile = worker.getProfile();
        }
        model.addAttribute(ModelConstants.KEY_WORD, profile.getKeyWord());
        return "pages/changePassword";
    }

    /**
     * @param profileDTO
     * @param result
     * @param repeatNewPassword
     * @param newPassword
     * @param session
     * @param model
     * @return
     */
    @RequestMapping(value = "/changePas", method = RequestMethod.POST)
    public String change(@ModelAttribute(ModelConstants.PROFILE) @Valid ProfileDTO profileDTO,
                         BindingResult result, @RequestParam(ModelConstants.REPEAT_NEW_PASSWORD) String repeatNewPassword,
                         @RequestParam(ModelConstants.NEW_PASSWORD) String newPassword, HttpSession session, Model model) {
        //if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        if (session.getAttribute(ModelConstants.LOGED_AS) == null)
            model.addAttribute(ModelConstants.LOGED_AS, ModelConstants.TRUE);
        if (newPassword.isEmpty()) result.rejectValue("worker.password", "empty.password");
        if (repeatNewPassword.isEmpty()) result.rejectValue("worker.password", "empty.newPassword");
        if (profileDTO.getAnswerOnKeyWord().isEmpty()) result.rejectValue("answerOnKeyWord", "empty.answerOnKeyWord");
        Profile profile;
        if (session.getAttribute(ModelConstants.LOGED_AS) != null) {
            profile = profileService.getProfileById((Long) session.getAttribute(ModelConstants.PROFILE_ID));
        } else {
            Worker worker = workerService.getWorkerByLogin((String) session.getAttribute("login"));
            profile = worker.getProfile();
        }
        model.addAttribute(ModelConstants.KEY_WORD, profile.getKeyWord());
        if (result.hasErrors()) return "pages/changePassword";
        if (!profileDTO.getAnswerOnKeyWord().equals(profile.getAnswerOnKeyWord()))
            result.rejectValue("answerOnKeyWord", "incorrect.keyWord");
        if (!newPassword.equals(repeatNewPassword)) result.rejectValue("worker.password", "notsame.password");
        if (result.hasErrors()) return "pages/changePassword";
        profile.getWorker().setPassword(newPassword);
        profileService.updateProfile(profile);
        model.addAttribute(ModelConstants.SUCCESS_CHANGE_PASSWORD, ModelConstants.TRUE);
        if (session.getAttribute(ModelConstants.LOGED_AS) != null) {
            return "pages/profile";
        } else {
            model.addAttribute(ModelConstants.LOG_PASS, new LoginPasswordDTO());
            return "pages/index";
        }
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/changeKeyWord", method = RequestMethod.GET)
    public String changeKeyWord(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(ModelConstants.PROFILE, profileService.getProfileById((Long) session.getAttribute(ModelConstants.PROFILE_ID)));
        return "pages/changeKeyWord";
    }

    /**
     * @param profileDTO
     * @param result
     * @param password
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/changeKeyWord", method = RequestMethod.POST)
    public String changeKey(@ModelAttribute(ModelConstants.PROFILE) @Valid ProfileDTO profileDTO, BindingResult result,
                            @RequestParam(ModelConstants.PASSWORD) String password, Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        if (password.isEmpty()) result.rejectValue("worker.password", "empty.password");
        if (profileDTO.getKeyWord() == null) result.rejectValue("keyWord", "empty.keyWord");
        if (profileDTO.getAnswerOnKeyWord().isEmpty()) result.rejectValue("answerOnKeyWord", "empty.answerOnKeyWord");
        if (result.hasErrors()) return "pages/changeKeyWord";
        Profile profile = profileService.getProfileById((Long) session.getAttribute(ModelConstants.PROFILE_ID));
        if (!password.equals(profile.getWorker().getPassword())) result.rejectValue("worker.password", "err.password");
        if (result.hasErrors()) return "pages/changeKeyWord";
        profile.setKeyWord(profileDTO.getKeyWord());
        profile.setAnswerOnKeyWord(profileDTO.getAnswerOnKeyWord());
        profileService.updateProfile(profile);
        model.addAttribute(ModelConstants.SUCCESS_CHANGE_KEY_WORD, ModelConstants.TRUE);
        return "pages/profile";
    }

    /**
     * @param idWorker
     * @param session
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/findByIdAndEditWorker", method = RequestMethod.POST)
    public String findByIdAndEditWorker(@RequestParam(ModelConstants.WORKER_ID) Long idWorker, HttpSession session) throws IOException {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Worker worker = workerService.getWorkerById(idWorker);
        session.setAttribute(ModelConstants.PROFILE_ID, worker.getProfile().getIdProfile());
        session.setAttribute(ModelConstants.ADMIN_EDIT_PROFILE, true);
        return "redirect:/edit";

    }

    /**
     * @param idWorker
     * @param session
     * @return
     */
    @RequestMapping(value = "/findByIdAndShowInfo", method = RequestMethod.POST)
    public String findByIdAndShowInfo(@RequestParam(ModelConstants.WORKER_ID) Long idWorker, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Worker worker = workerService.getWorkerById(idWorker);
        session.setAttribute(ModelConstants.TEMP_WORKER_ID, idWorker);
        session.setAttribute(ModelConstants.TEMP_WORKER, worker.getNameWorker());
        return "redirect:/showWorkerInfo";
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/showWorkerInfo", method = RequestMethod.GET)
    public String showWorkerInfo(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(ModelConstants.COLLECTION_TICKETS, projectTicketService.getWorkerProjectTicketsPerfomance(workerService.getWorkerById((Long) session.getAttribute(ModelConstants.TEMP_WORKER_ID))));
        return "pages/workerInfo";
    }

    /**
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/allWorkers", method = RequestMethod.GET)
    public String getAllWorker(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        model.addAttribute(ModelConstants.ALL_WORKERS, workerService.getAllWorkers());
        return "pages/allWorkers";
    }

    /**
     * For log out and clear session
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(HttpSession session) {
        session.removeAttribute(ModelConstants.PHOTO);
        session.removeAttribute(ModelConstants.NAME_USER);
        session.removeAttribute(ModelConstants.LOGED_AS);
        session.removeAttribute(ModelConstants.IS_ADMIN);
        session.removeAttribute(ModelConstants.PROFILE_ID);
        session.removeAttribute(ModelConstants.LOGIN);
        session.removeAttribute(ModelConstants.ADMIN_EDIT_PROFILE);
        session.removeAttribute(ModelConstants.TEMP_WORKER_ID);
        session.removeAttribute(ModelConstants.TEMP_WORKER);
        session.removeAttribute(ModelConstants.VACATION_VALIDATION);
        session.removeAttribute(ModelConstants.PROFILE);
        return "redirect:/";
    }

    /**
     * If u click "chat" in menu u will redirect to page "chat.jsp"
     *
     * @param session
     * @return String
     */
    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String findWorker(HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        return "pages/chat";
    }

    /**
     * return all Workers messages
     *
     * @return List<ChatDTO>
     */
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    @ResponseBody
    public List<ChatDTO> getAllMessages(HttpSession session) throws IOException, SecurityException {
        if (!MethodsForControllers.isLogedIn(session)) throw new SecurityException();
        chatArrayList.clear();
        for (Chat chat : chatDao.getAllComments()) {
            String userLogin = (String) session.getAttribute(ModelConstants.LOGED_AS);
            String nameWorker = chat.getWorker().getNameWorker();
            byte[] photo = chat.getWorker().getProfile().getPhoto();
            if (photo == null) {
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("photo/me-flat.png");
                photo = MethodsForControllers.returnDefaultPhotoBytes(inputStream);
            }
            chatArrayList.add(new ChatDTO(nameWorker, chat.getComment(), photo, userLogin.equals(chat.getWorker().getLogin())));
        }
        return chatArrayList;
    }

    /**
     * For add message
     *
     * @param textMessage
     * @param session
     * @return String
     */
    @RequestMapping(value = "/addMessage", method = RequestMethod.GET)
    public String addMessage(@RequestParam(ModelConstants.TEXT_MESSAGE) String textMessage, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        if (textMessage != null) {
            String userLogin = (String) session.getAttribute(ModelConstants.LOGED_AS);
            chatDao.createComment(new Chat(new Date(), textMessage, workerService.getWorkerByLogin(userLogin)));
        }
        return "redirect:/chat";
    }

    /**
     * For create vacation/sick leave request
     *
     * @param timeVocation
     * @param result
     * @param session
     * @return
     */
    @RequestMapping(value = "/createRequestVacation", method = RequestMethod.POST)
    public String createRequestVacation(@ModelAttribute(ModelConstants.VACATION) @Valid TimeVocation timeVocation, BindingResult result,
                                        HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        timeVocationValidator.validate(timeVocation, result);
        if (result.hasErrors()) {
            if (result.hasFieldErrors("startVocDate")) {
                session.setAttribute(ModelConstants.VACATION_VALIDATION, "Please choose start date");
            } else {
                if (result.hasFieldErrors("endVocDate")) {
                    session.setAttribute(ModelConstants.VACATION_VALIDATION, "Please choose correct end date");
                }
            }
            return "redirect: /main";
        }
        Worker worker = workerService.getWorkerByLogin((String) session.getAttribute(ModelConstants.LOGED_AS));
        timeVocationService.createTimeVocation(timeVocation, worker);
        return "redirect: /main";
    }

    /**
     * For response to request by admin
     *
     * @param choice
     * @param idTimeVacation
     * @param session
     * @return
     */
    @RequestMapping(value = "/vacationResponse", method = RequestMethod.POST)
    public String vacationResponse(@RequestParam(ModelConstants.CHOICE) String choice,
                                   @RequestParam(ModelConstants.TIME_VACATION_ID) long idTimeVacation, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        TimeVocation timeVocation = timeVocationService.getTimeVacationById(idTimeVacation);
        if ("accept".equals(choice)) {
            timeVocation.setConfirmed(1);
            timeVocationService.updateTimeVocation(timeVocation);
            return "redirect: /main";
        } else {
            timeVocation.setConfirmed(0);
            timeVocationService.updateTimeVocation(timeVocation);
        }
        return "redirect: /main";
    }
}
