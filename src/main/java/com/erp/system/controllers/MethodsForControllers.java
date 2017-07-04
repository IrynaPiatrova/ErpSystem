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
    public static String getCookieByName(String cName, Cookie[] cookies) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie);
        }
        return cookieMap.get(cName).getValue();
    }

    public static Boolean isLogedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String str = (String) session.getAttribute("isLogedIn");
        return str == null ? false : true;
    }
}
