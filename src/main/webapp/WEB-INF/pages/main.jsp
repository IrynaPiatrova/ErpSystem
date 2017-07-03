<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 16.06.2017
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome Page</title>
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <style>
        <%@include file='../css/style_for_up_body.css' %>
        <%--<%@include file='../css/style_for_welcome_page.css' %>--%>
        body {
            background: url(http://komionline.ru/files/media/image/preview/rab00_1.jpg) no-repeat;
            -moz-background-size: 100%; /* Firefox 3.6+ */
            -webkit-background-size: 100%; /* Safari 3.1+ и Chrome 4.0+ */
            -o-background-size: 100%; /* Opera 9.6+ */
            background-size: 100%; /* Современные браузеры */
        }
    </style>
</head>
<body>
<c:if test = "${isAdmin == 'false'}">
    <%@include file="menuUser.jsp" %>
</c:if>
<c:if test = "${isAdmin == 'true'}">
    <%@include file="menuAdmin.jsp" %>
</c:if>
</body>
</html>
