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
    <%@include file="bootstrapLinks.jsp" %>
    <title>Создание новой задачи</title>
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
        input,select{
            border-radius: 10px
        }
        #textzone{
            border-radius: 10px;
        }
    </style>
</head>
<body>
<%@include file="menu.jsp" %>
<%--<span style="float: right">--%>
             <%--<a href="?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png" width="10%"--%>
                                     <%--height="10%"></a>--%>
            <%--<a href="?lang=ru"><img--%>
                    <%--src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg=="--%>
                    <%--width="10%" height="10%"></a>--%>
    <%--</span>--%>
<spring:message code="ticket.name" var="nameTicket"/>
<spring:message code="ticket.specification" var="specificationTicket"/>
<spring:message code="ticket.status" var="statusTicket"/>
<spring:message code="ticket.status.choose" var="statusTicketChoose"/>
<spring:message code="ticket.status.opened" var="statusTicketOpened"/>
<spring:message code="ticket.status.inProgress" var="statusTicketInProgress"/>
<spring:message code="ticket.status.paused" var="statusTicketPaused"/>
<spring:message code="ticket.status.readyForTest" var="statusTicketReadyForTest"/>
<spring:message code="ticket.status.finished" var="statusTicketFinished"/>
<spring:message code="ticket.startDate" var="startDateTicket"/>
<spring:message code="ticket.endDate" var="endDateTicket"/>
<spring:message code="ticket.deadline" var="deadlineTicket"/>
<spring:message code="label.complete" var="labelComplete"/>
<spring:message code="label.back" var="labelBack"/>

<form:form action="isSuccessAddNewTicket" method="post" modelAttribute="ticket">
    <div id="centerLayer">
    <table>
        <tr height="40">
            <td>${nameTicket}</td>
            <td><form:input type="text" name="nameProjectTicket" path="nameProjectTicket"/>
                <div><form:errors path="nameProjectTicket" style="color:red"/></div></td>
        </tr>
        <tr>
            <td>${specificationTicket}</td>
            <td><form:textarea id="textzone" name="specification" path="specification" border-radius = "10" cols="50" rows="4"/>
                <div><form:errors path="specification" style="color:red"/></div></td>
        </tr>
        <%--<tr>--%>
            <%--<td>${specificationTicket}</td>--%>
            <%--<td><form:input type="text" name="specification" path="specification"/>--%>
                <%--<div><form:errors path="specification" style="color:red"/></div></td>--%>
        <%--</tr>--%>
        <tr height="40">
            <td>${statusTicket}</td>
            <td><form:select path="statusProjectTicket">
                <option value="status" disabled selected>${statusTicketChoose}</option>
                <option value="opened">${statusTicketOpened}</option>
                <option value="in_progress">${statusTicketInProgress}</option>
                <option value="paused">${statusTicketPaused}</option>
                <option value="ready_for_testing">${statusTicketReadyForTest}</option>
                <option value="finished">${statusTicketFinished}</option>
            </form:select>
                <div><form:errors path="statusProjectTicket" style="color:red"/></div></td>
        </tr>
        <%--<tr>--%>
            <%--<td>${startDateTicket}</td>--%>
            <%--<td><form:input type="date" name="startDate" path="startTicketDate"/>--%>
                <%--<div><form:errors path="startTicketDate" style="color:red"/></div></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>${endDateTicket}</td>--%>
            <%--<td><form:input type="date" name="endTicketDate" path="endTicketDate"/>--%>
                <%--<div><form:errors path="endTicketDate" style="color:red"/></div></td>--%>
        <%--</tr>--%>
        <tr height="40">
            <td>${deadlineTicket}</td>
            <td height="30"><input type="date" name="deadlineDate" path="deadlineTicket"/>
                <div><form:errors path="deadlineTicket" style="color:red"/></div></td>
        </tr>
        <tr>
            <td><input type="submit" value="${labelComplete}"></td>
        </tr>
    </table>
</form:form>
        <br>
<form margin-top="20px" action="/" method="get">
    <input type="submit" value="${labelBack}">
</form>
    </div>
</body>
</html>
