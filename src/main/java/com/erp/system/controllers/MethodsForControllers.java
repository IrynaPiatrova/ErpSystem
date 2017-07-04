package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
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

    public static Boolean isLogedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(IConstants.LOGED_AS) != null;
    }

    public static Boolean isAdmin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return IConstants.ADMIN.equals(session.getAttribute(IConstants.IS_ADMIN));
    }

    public static MultipartFile getPhoto(byte[] photo) {
        MultipartFile multipartFile = null;
        if (photo != null) {
            multipartFile  = new MockMultipartFile("imageFile", "filename", "image/png", photo);
        }
        return multipartFile;
    }
}
