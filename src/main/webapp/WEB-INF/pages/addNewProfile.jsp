<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 02.07.2017
  Time: 17:16
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
<style>
    #form{
        align: center;
    }
</style>
<body>
<span style="float: right">
             <a href="?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png" width="10%"
                                     height="10%"></a>
            <a href="?lang=ru"><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg=="
                    width="10%" height="10%"></a>
    </span>
<form:form id="form" action="isSuccessAddNewProfile" method="post" modelAttribute="profile">
    <spring:message code="position" var="labelPosition"/>
    <form:input type="text" name="position" placeholder="${labelPosition}" path="position"/>
    <div><form:errors path="position" style="color:red"/></div>
    <br>
    <spring:message code="department" var="department"/>
    <form:input type="text" name="department" placeholder="${department}" path="department"/>
    <div><form:errors path="department" style="color:red"/></div>
    <br>
    <spring:message code="employmentStatus" var="employmentStatus"/>
    <form:input type="text" name="employmentStatus" placeholder="${employmentStatus}" path="employmentStatus"/><!--Нужно сделать compobox-->
    <div><form:errors path="employmentStatus" style="color:red"/></div>
    <br>
    <spring:message code="telephone" var="telephone"/>
    <form:input type="text" name="telephone" placeholder="${telephone}" path="telephone"/>
    <div><form:errors path="telephone" style="color:red"/></div>
    <br>
    <spring:message code="email" var="email"/>
    <form:input type="text" name="email" placeholder="${email}" path="email"/>
    <div><form:errors path="email" style="color:red"/></div>
    <br>
    <input type="date" name="Date">
    <br>
    <%--<form:input type="date" name="startDate" path="startDate"/>--%>
    <%--<div><form:errors path="startDate" style="color:red"/></div>--%>
    <br>
    <form:input type="file" name="photo" path="photo"/>
    <br>
    <spring:message code="label.next" var="labelNext"/>
    <input type="submit" value="${labelNext}">
    <br>
</form:form>
</body>
</html>
