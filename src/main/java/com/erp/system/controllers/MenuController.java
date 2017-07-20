package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.dao.profile.ProfileDao;
import com.erp.system.dao.worker.WorkerDao;
import com.erp.system.dto.ProfileDTO;
import com.erp.system.entity.Profile;
import com.erp.system.entity.Worker;
import com.erp.system.validators.EditProfileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;


/**
 * Created by John on 18.06.2017
 */
@Controller
public class MenuController {
    @Autowired
    WorkerService workerService;
    @Autowired
    ProfileService profileService;
    @Autowired
    ChatDao chatDao;
    @Autowired
    ProjectTicketService projectTicketService;
    @Autowired
    EditProfileValidator editProfileValidator;
    private List<ChatDTO> chatArrayList = new ArrayList<>();

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
        session.setAttribute(ModelConstants.PROFILE_DATA, profileById);
        session.removeAttribute(ModelConstants.ADMIN_EDIT_PROFILE);
        return "pages/profile";
    }


    @RequestMapping(value = {"/edit", "/editAdmin"}, method = RequestMethod.GET)
    public String editProfileInit(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        model.addAttribute(ModelConstants.PROFILE, session.getAttribute(ModelConstants.PROFILE_DATA));
        return "pages/editProfile";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    //это метод для изменения СВОЕГО профиля (или пользователь меняет свой профиль, или админ свой)
    public String editProfile(@ModelAttribute(ModelConstants.PROFILE) @Valid ProfileDTO profileDTO, @RequestParam("photo") MultipartFile photo,@RequestParam("answerOnKeyWord") String answerOnKeyWord, HttpSession session, BindingResult result) {
        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
        Profile profile = (Profile) session.getAttribute(ModelConstants.PROFILE_DATA);
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
            e.printStackTrace();
        }
        profile.setTelephone(profileDTO.getTelephone());
        profile.setEmail(profileDTO.getEmail());
        profile.getWorker().setLogin(profileDTOLogin);
        profile.setKeyWord(profileDTO.getKeyWord());
        profile.setAnswerOnKeyWord(answerOnKeyWord);
        profileService.updateProfile(profile);
        session.setAttribute(ModelConstants.LOGED_AS, profile.getWorker().getLogin());
        return "redirect:/profile";
    }

    @RequestMapping(value = "/editAdmin", method = RequestMethod.POST)
    //этот метод для изменения других полей профиля - меняет ТОЛЬКО админ после выбора из списка пользователей
    public String editProfileByAdmin(@ModelAttribute(ModelConstants.PROFILE) @Valid ProfileDTO profileDTO, HttpSession session, BindingResult result) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Profile profile = (Profile) session.getAttribute(ModelConstants.PROFILE_DATA);
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

    @RequestMapping(value = "/findByIdAndEditWorker", method = RequestMethod.POST)
    public String findByIdAndEditWorker(@RequestParam("idWorker") Long idWorker, HttpSession session, Model model) {
    @RequestMapping(value = "/isLoginExist", method = RequestMethod.GET)
    public String isLoginExist(Model model){
        model.addAttribute("worker", new Worker());
        return "pages/changePassword";
    }

    @RequestMapping(value = "/isLoginExist", method = RequestMethod.POST)
    public String isLoginExist(@ModelAttribute("worker")@Valid Worker worker, BindingResult result, Model model, HttpSession session){
        if (worker.getLogin().isEmpty()){
            result.rejectValue("login","empty.login");
            return "pages/changePassword";
        }
        worker = workerDao.getWorkerByLogin(worker.getLogin());
        if (worker == null) result.rejectValue("login","not.used.login");
        if (result.hasErrors()) return "pages/changePassword";
        if (worker.getProfile().getKeyWord() == null || worker.getProfile().getAnswerOnKeyWord() == null) result.rejectValue("login","exist.keyWord");
        if (result.hasErrors()) return "pages/changePassword";
        model.addAttribute(IConstants.PROFILE, worker.getProfile());
        model.addAttribute("keyWord", worker.getProfile().getKeyWord());
        model.addAttribute(IConstants.LOGED_AS, IConstants.TRUE);
        session.setAttribute("login", worker.getLogin());
        return "pages/changePassword";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(Model model, HttpSession session){
        Profile profile = (Profile) session.getAttribute(IConstants.PROFILE_DATA);
        model.addAttribute(IConstants.PROFILE, profile);
        model.addAttribute(IConstants.LOGED_AS, session.getAttribute(IConstants.LOGED_AS));
        if (session.getAttribute(IConstants.LOGED_AS) != null) model.addAttribute("keyWord", profile.getKeyWord());
        return "pages/changePassword";
    }

    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String change(@ModelAttribute(IConstants.PROFILE)@Valid ProfileDTO profileDTO,
                         BindingResult result, @RequestParam("repeatNewPassword") String repeatNewPassword,
                         @RequestParam("newPassword") String newPassword, HttpSession session, Model model){
        if (session.getAttribute(IConstants.LOGED_AS) == null) model.addAttribute(IConstants.LOGED_AS, IConstants.TRUE);
        if (newPassword.length()<= 0)result.rejectValue("worker.password", "empty.password");
        if (repeatNewPassword.length() <= 0)result.rejectValue("worker.password", "empty.newPassword");
        if (profileDTO.getAnswerOnKeyWord().length()<= 0) result.rejectValue("answerOnKeyWord","empty.answerOnKeyWord");
        Profile profile;
        if (session.getAttribute(IConstants.LOGED_AS) != null) {
            profile = (Profile) session.getAttribute(IConstants.PROFILE_DATA);
        }else {
            Worker worker = workerDao.getWorkerByLogin((String) session.getAttribute("login"));
            profile = worker.getProfile();
        }
        if (result.hasErrors()) return "pages/changePassword";
        if (!profileDTO.getAnswerOnKeyWord().equals(profile.getAnswerOnKeyWord())) result.rejectValue("answerOnKeyWord", "incorrect.keyWord");
        if (!newPassword.equals(repeatNewPassword)) result.rejectValue("worker.password","notsame.password");
        if (result.hasErrors()) return "pages/changePassword";
        profile.getWorker().setPassword(newPassword);
        profileDao.updateProfile(profile);
        model.addAttribute("successChangePassword", IConstants.TRUE);
        if (session.getAttribute(IConstants.LOGED_AS) != null) {
            return "pages/profile";
        }else {
            model.addAttribute("logPass", new LoginPassword());
            return "pages/index";
        }
    }
//    @RequestMapping(value = "/findWorker", method = RequestMethod.GET)
//    public String findWorker(HttpSession session) {
//        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
//        return "pages/findWorker";
//    }


//    @RequestMapping(value = "/findWorkerById", params = "id", method = RequestMethod.GET)
//    @ResponseBody
//    public Worker findWorkerByValue(@RequestParam("id") Long id, HttpSession session, Model model, HttpServletResponse response) {
//        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // эту ошибку потом надо отловить и отправить...
//            return null;
//        }
//        Worker worker = workerDao.getWorkerById(id);
//
//        return workerDao.getWorkerById(id);
//    }

    @RequestMapping(value = "/findWorkerByLogin", method = RequestMethod.POST)
    public String findWorkerByValue(@RequestParam("login") String login, HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Worker worker = workerService.getWorkerById(idWorker);
        Profile profile = profileService.getProfileById(worker.getProfile().getIdProfile());
        session.setAttribute(ModelConstants.PROFILE_DATA, profile);
        session.setAttribute(ModelConstants.ADMIN_EDIT_PROFILE, true);
        return "redirect:/edit";
    }

    @RequestMapping(value = "/findByIdAndShowInfo", method = RequestMethod.POST)
    public String findByIdAndShowInfo(@RequestParam("idWorker") Long idWorker, HttpSession session, Model model) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        Worker worker = workerService.getWorkerById(idWorker);
        session.setAttribute(ModelConstants.COLLECTION_TICKETS, projectTicketService.getWorkerProjectTicketsPerfomance(worker));
        session.setAttribute(ModelConstants.TEMP_WORKER, worker.getNameWorker());
        return "redirect:/showWorkerInfo";
    }

    @RequestMapping(value = "/showWorkerInfo", method = RequestMethod.GET)
    public String showWorkerInfo(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session))
            return "redirect:/";// Надо подумать будет ли доступна обычному пользователю инфо о его успеваемости
        model.addAttribute(ModelConstants.COLLECTION_TICKETS, session.getAttribute(ModelConstants.COLLECTION_TICKETS));
        return "pages/workerInfo";
    }

    @RequestMapping(value = "/allWorkers", method = RequestMethod.GET)
    public String getAllWorker(Model model, HttpSession session) {
        if (!MethodsForControllers.isLogedIn(session) || !MethodsForControllers.isAdmin(session)) return "redirect:/";
        model.addAttribute(ModelConstants.ALL_WORKERS, workerService.getAllWorkers());
        return "pages/allWorkers";
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public String logOut(HttpSession session) {
        session.removeAttribute(ModelConstants.IS_ADMIN);
        session.removeAttribute(ModelConstants.LOGED_AS);
        session.removeAttribute(ModelConstants.NAME_USER);
        session.removeAttribute(ModelConstants.PHOTO);
        session.removeAttribute(ModelConstants.PROFILE_DATA);
        session.removeAttribute(ModelConstants.ADMIN_EDIT_PROFILE);
        session.removeAttribute(ModelConstants.TEMP_WORKER);
        session.removeAttribute(ModelConstants.COLLECTION_TICKETS);

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
    public List<ChatDTO> getAllMessages(HttpSession session) throws IOException {
//        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/"; надо подумать как установить ограничение на вход в этот метод тем кто не авторизовалсяя
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
     * @param textMessage
     * @param session
     * @return String
     */
    @RequestMapping(value = "/addMessage", method = RequestMethod.GET)
    public String addMessage(@RequestParam("textMessage") String textMessage, Model model, HttpSession session){
        if(textMessage != null){
            String userLogin = (String) session.getAttribute(ModelConstants.LOGED_AS);
            chatDao.createComment(new Chat(new Date(),textMessage, workerService.getWorkerByLogin(userLogin)));
        }
        return "redirect:/chat";
    }

//    @RequestMapping(value = "/createRequestVacation", method = RequestMethod.POST)
//    public String createRequestVacation(  Param1, Param2     , HttpSession session){
//        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
//        // сохранение в БД информации предварительного запроса
//        // который после подтверждения админом будет записан в БД окончательно
//
//        return "redirect: /main";
//    }
//
//    @RequestMapping(value = "/createRequestSickLeave", method = RequestMethod.POST)
//    public String createRequestSickLeave( Param1, Param2          , HttpSession session){
//        if (!MethodsForControllers.isLogedIn(session)) return "redirect:/";
//        // сохранение в БД информации предварительного запроса
//        // который после подтверждения админом будет записан в БД окончательно
//
//        return "redirect: /main";
//    }
}
