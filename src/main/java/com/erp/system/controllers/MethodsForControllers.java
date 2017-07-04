package com.erp.system.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by John on 04.07.2017.
 */
public class MethodsForControllers {
    private static final String LOGED_AS = "logedAs";
    private static final String IS_ADMIN = "isAdmin";
    private static final String ADMIN = "admin";

    public static String getCookieByName(String cName, Cookie[] cookies) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie);
        }
        return cookieMap.get(cName).getValue();
    }

    public static Boolean isLogedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(LOGED_AS) != null;
    }
    public static Boolean isAdmin(HttpServletRequest request){
        HttpSession session = request.getSession();
        return ADMIN.equals(session.getAttribute(IS_ADMIN));
    }
}
