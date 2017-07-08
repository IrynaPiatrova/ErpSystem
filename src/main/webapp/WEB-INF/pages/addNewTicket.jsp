<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 07.07.2017
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <title>Создание новой задачи</title>
</head>
<body>
<span style="float: right">
             <a href="?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png" width="10%"
                                     height="10%"></a>
            <a href="?lang=ru"><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg=="
                    width="10%" height="10%"></a>
    </span>
<spring:message code="nameTicket" var="nameTicket"/>
<spring:message code="specificationTicket" var="specificationTicket"/>
<spring:message code="statusTicket" var="statusTicket"/>
<spring:message code="statusTicketOpened" var="statusTicketOpened"/>
<spring:message code="statusTicketInProgress" var="statusTicketInProgress"/>
<spring:message code="statusTicketPaused" var="statusTicketPaused"/>
<spring:message code="statusTicketReadyForTest" var="statusTicketReadyForTest"/>
<spring:message code="statusTicketFinished" var="statusTicketFinished"/>
<spring:message code="startDateTicket" var="startDateTicket"/>
<spring:message code="endDateTicket" var="endDateTicket"/>
<spring:message code="deadlineTicket" var="deadlineTicket"/>
<spring:message code="label.endRegistration" var="labelEnd"/>
<form:form id="form" action="isSuccessAddNewTicket" method="post" modelAttribute="ticket">
    <table>
        <tr>
            <td>${nameTicket}</td>
            <td><form:input type="text" name="nameProjectTicket" path="nameProjectTicket"/>
                <div><form:errors path="nameProjectTicket" style="color:red"/></div></td>
        </tr>
        <tr>
            <td>${specificationTicket}</td>
            <td><form:input type="text" name="specification" path="specification"/>
                <div><form:errors path="specification" style="color:red"/></div></td>
        </tr>
        <tr>
            <td>${statusTicket}</td>
            <td><form:select path="statusProjectTicket">
                <option value="opened">${statusTicketOpened}</option>
                <option value="in_progress">${statusTicketInProgress}</option>
                <option value="paused">${statusTicketPaused}</option>
                <option value="ready_for_testing">${statusTicketReadyForTest}</option>
                <option value="finished">${statusTicketFinished}</option>
            </form:select>
                <div><form:errors path="statusProjectTicket" style="color:red"/></div></td>
        </tr>
        <tr>
            <td>${startDateTicket}</td>
            <td><form:input type="date" name="startTicketDate" path="startTicketDate"/>
                <div><form:errors path="startTicketDate" style="color:red"/></div></td>
        </tr>
        <tr>
            <td>${endDateTicket}</td>
            <td><form:input type="date" name="endTicketDate" path="endTicketDate"/>
                <div><form:errors path="endTicketDate" style="color:red"/></div></td>
        </tr>
        <tr>
            <td>${deadlineTicket}</td>
            <td><form:input type="date" name="deadlineTicket" path="deadlineTicket"/>
                <div><form:errors path="deadlineTicket" style="color:red"/></div></td>
        </tr>
        <tr>
            <td><input type="submit" value="${labelEnd}"></td>
        </tr>
    <%--<form:input type="text" name="statusProjectTicket" placeholder="${statusTicket}" path="statusProjectTicket"/><!--Нужно сделать compobox-->--%>
    </table>
</form:form>
</body>
</html>
