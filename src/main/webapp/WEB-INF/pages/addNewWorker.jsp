<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 06.07.2017
  Time: 16:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <title>Добавление нового сотрудника</title>
</head>
<body>
<span style="float: right">
             <a href="?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png" width="10%"
                                     height="10%"></a>
            <a href="?lang=ru"><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg=="
                    width="10%" height="10%"></a>
    </span>
<form:form id="form" action="isSuccessAddNewWorker" method="post" modelAttribute="worker">
    <spring:message code="nameWorker" var="nameWorker"/>
    <form:input type="text" name="nameWorker" placeholder="${nameWorker}" path="nameWorker"/>
    <div><form:errors path="nameWorker" style="color:red"/></div>
    <br>
    <spring:message code="loginRegistration" var="logReg"/>
    <form:input type="text" name="login" placeholder="${logReg}" path="login"/>
    <div><form:errors path="login" style="color:red"/></div>
    <br>
    <spring:message code="passwordRegistration" var="pasReg"/>
    <form:input type="password" name="password" placeholder="${pasReg}" path="password"/>
    <div><form:errors path="password" style="color:red"/></div>
    <br>
    <spring:message code="label.endRegistration" var="labelEnd"/>
    <input type="submit" value="${labelEnd}">
    <br>
</form:form>
</body>
</html>
