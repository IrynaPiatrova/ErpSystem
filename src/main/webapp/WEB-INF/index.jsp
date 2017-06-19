<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 15.06.2017
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Page</title>
    <link rel="icon" href="http://rylik.ru/uploads/posts/2010-08/1282727228_4.jpg" type="image/x-icon">
    <link rel="shortcut icon" href="http://rylik.ru/uploads/posts/2010-08/1282727228_4.jpg" type="image/x-icon">
    <link rel="stylesheet" href="css/style_for_icon.css" media="screen" type="text/css"/>
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>

    <style>
        <%@include file='css/style_for_authorization.css' %>
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>

<body>

<div class="body"></div>
<div class="grad"></div>
<div class="header">
    <div>ERP<span>System</span></div>
</div>
<br>
<div class="login">
    <span style="float: right">
             <a href="?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png" width="10%"
                                     height="10%"></a>
            <a href="?lang=ru"><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg=="
                    width="10%" height="10%"></a>
        </span>
    <br><br>
    <form:form class="form-inline" action="welcome" method="post" modelAttribute="logPass">
        <form:input type="text" placeholder="username" autocomplete="off" path="login"/>
        <form:input type="password" placeholder="password" autocomplete="off" path="pussword"/>
        <spring:message code="label.authorization" var="labelAuthorization"/>
        <br><br>
        <input type="submit" value="${labelAuthorization}"/>
    </form:form>
</div>
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
</body>
</html>
