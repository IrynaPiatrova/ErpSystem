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
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <style>
        #centerLayer {
            position: absolute;
            margin-left: 30%;
            margin-top: 10%;
            padding: 10px;
            overflow: auto;
        }
        body {
            background: #D3D3D3;
        }
        input{
            width: 200px;
            border-radius: 10px;
        }
        select{
            border-radius: 10px;
        }
    </style>
</head>
<body>
<%@include file="menu.jsp" %>
<span style="float: right">
             <a href="/isSuccessAddNewWorker?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png" width="10%"
                                     height="10%"></a>
            <a href="/isSuccessAddNewWorker?lang=ru"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg==" width="10%" height="10%"></a>
    </span>
<spring:message code="worker.name" var="workerName"/>
<spring:message code="worker.login" var="workerLogin"/>
<spring:message code="worker.password" var="workerPassword"/>
<spring:message code="label.complete" var="labelComplete"/>
<spring:message code="label.back" var="labelBack"/>
<div id="centerLayer">
<form:form id="form" action="isSuccessAddNewWorker" method="post" modelAttribute="worker">
    <table>
        <tr height="40">
            <td>${workerName}</td>
            <td><form:input type="text" name="nameWorker" path="nameWorker"/>
            <div><form:errors path="nameWorker" style="color:red"/></div>
            </td>
        </tr>
        <tr height="40">
            <td>${workerLogin}</td>
            <td><form:input type="text" name="login" path="login"/>
                <div><form:errors path="login" style="color:red"/></div>
            </td>
        </tr>
        <tr height="40">
            <td>${workerPassword}</td>
            <td><form:input type="password" name="password" path="password"/>
                <div><form:errors path="password" style="color:red"/></div>
            </td>
        </tr>
        <tr height="40">
            <td><input type="submit" value="${labelComplete}"></td>
        </tr>
    </table>
</form:form>
<form action="/addNewWorker" method="get">
    <input type="submit" value="${labelBack}" width="100">
</form>
</div>
</body>
</html>
