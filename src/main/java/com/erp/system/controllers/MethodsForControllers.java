package com.erp.system.controllers;

import com.erp.system.constants.IConstants;
import com.erp.system.dto.ProfileDTO;
import com.erp.system.entity.Profile;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 04.07.2017
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
        return IConstants.TRUE.equals(session.getAttribute(IConstants.IS_ADMIN));
    }

    public static String convertToMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            while (hashtext.length() < 30) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
