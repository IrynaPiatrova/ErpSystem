package com.erp.system.controllers.methods;

import com.erp.system.constants.ModelConstants;
import com.erp.system.entity.Worker;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        return session.getAttribute(ModelConstants.LOGED_AS) != null;
    }

    public static Boolean isAdmin(HttpSession session) {
        return ModelConstants.TRUE.equals(session.getAttribute(ModelConstants.IS_ADMIN));
    }

    public static byte[] returnDefaultPhotoBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage image = ImageIO.read(inputStream);
        ImageIO.write(image,"png",byteArrayOutputStream);
        byteArrayOutputStream.flush();
        String base64String = Base64.encode(byteArrayOutputStream.toByteArray());
        byteArrayOutputStream.close();
        byte[] data = Base64.decode(base64String);
        return data;
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

    public static boolean isStatusNotFinish(String status){
        return !(status.equals(ModelConstants.STATUS_READY_FOR_TESTING) || status.equals(ModelConstants.STATUS_FINISHED));
    }
    public static boolean isWorkerNotChosen(Worker worker){
        return worker == null;
    }
}
