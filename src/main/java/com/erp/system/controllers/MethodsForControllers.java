package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.dto.ProfileDTO;
import com.erp.system.entity.Profile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 04.07.2017.
 */
public class MethodsForControllers {

    public static String getCookieByName(String cName, Cookie[] cookies) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie);
        }
        return cookieMap.get(cName).getValue();
    }

    public static Boolean isLogedIn(HttpSession session) {
        return session.getAttribute(IConstants.LOGED_AS) != null;
    }

    public static Boolean isAdmin(HttpSession session) {
        return IConstants.ADMIN.equals(session.getAttribute(IConstants.IS_ADMIN));
    }
    // работает без этого метода
//    public static MultipartFile getPhoto(byte[] photo) {
//        MultipartFile multipartFile = null;
//        if (photo != null) {
//            multipartFile = new MockMultipartFile("imageFile", "filename", "image/png", photo);
//        }
//        return multipartFile;
//    }

//    public static ProfileDTO convertProfileEntityToProfileDTO(Profile profile){
//        ProfileDTO profileDTO = new ProfileDTO();
//        profileDTO.setIdProfile(profile.getIdProfile());
//        profileDTO.setStartDateProfile(profile.getStartDateProfile());
//        profileDTO.setDepartment(profile.getDepartment());
//        profileDTO.setPosition(profile.getPosition());
//        profileDTO.setEmploymentStatus(profile.getEmploymentStatus());
//        profileDTO.setTelephone(profile.getTelephone());
//        profileDTO.setWorker(profile.getWorker());
//        if (profile.getEmail()!=null) profileDTO.setEmail(profile.getEmail()); // нужно решить будет ли эмейл у нас NOT NULL, если нет то тогда эта строчка нужна, также надо поменять в регистрации нового профиля валидацию емейла
//        if (profile.getPhoto()!=null) {
//            MultipartFile multipartFile = new MockMultipartFile("imageFile", "filename", "image/png", profile.getPhoto());
//            profileDTO.setPhoto(multipartFile);
//        }
//        return profileDTO;
//    }
}
