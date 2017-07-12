<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 11.07.2017
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Все тикеты</title>
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <style>
        body {
            background: #D3D3D3;
        }
        .tickets{
            margin-left: 6%;
            margin-top: 6%;
            width: 38%;
            height: 55%;
            overflow: scroll;
        }
        .tableOfTickets{
            font-size: medium;
            text-align: center;
            width: 100%;
        }
        .chooseTicket{
            margin-top: 6%;
            margin-right: 6%;
            width: 38%;
            height: 30%;
            border-color: #5379fa;
        }
    </style>
</head>
<body>
<%@include file="menu.jsp" %>
<spring:message code="ticket.status.choose" var="statusTicketChoose"/>
<spring:message code="ticket.status.opened" var="statusTicketOpened"/>
<spring:message code="ticket.status.inProgress" var="statusTicketInProgress"/>
<spring:message code="ticket.status.paused" var="statusTicketPaused"/>
<spring:message code="ticket.status.readyForTest" var="statusTicketReadyForTest"/>
<spring:message code="ticket.status.finished" var="statusTicketFinished"/>
<form:form action="allTickets" method="post" modelAttribute="ticket">
<select name="statusProject" path="statusProjectTicket">
    <option value="status" disabled selected>${statusTicketChoose}</option>
    <option value="opened">${statusTicketOpened}</option>
    <option value="in_progress">${statusTicketInProgress}</option>
    <option value="paused">${statusTicketPaused}</option>
    <option value="ready_for_testing">${statusTicketReadyForTest}</option>
    <option value="finished">${statusTicketFinished}</option>
    <div><form:errors path="statusProjectTicket" style="color:red"/></div>
    <%--<option value="all tickets">all tickets</option>показать все тикеты--%>
<select>
    <input type="submit" value="show">
</form:form>
<%--<li class="dropdown"><a href="allTickets" class="dropdown-toggle" data-toggle="dropdown">Статусы<b--%>
        <%--class="caret"></b></a>--%>
    <%--<ul class="dropdown-menu">--%>
        <%--<li><a href="">${statusTicketOpened}</a></li>--%>
        <%--<li><a href="#">${statusTicketInProgress}</a></li>--%>
        <%--<li><a href="#">${statusTicketPaused}</a></li>--%>
        <%--<li><a href="">${statusTicketReadyForTest}</a></li>--%>
        <%--<li><a href="">${statusTicketFinished}</a></li>--%>
    <%--</ul>--%>
<%--</li>--%>

<div class="tickets">
    <table class="tableOfTickets">
        <tr height="40">
            <td width="100">Название задачи</td>
            <td width="100">Статус задачи</td>
            <td width="100">Дедлайн задачи</td>
        </tr>
<c:forEach items="${collectionTickets}" var="listTickets">
    <tr height="40">
        <td hidden>${listTickets.idProjectTicket}</td>
    <td width="100"><a href="/chooseTicket/${listTickets.idProjectTicket}">${listTickets.nameProjectTicket}</a></td>
        <td width="100">${listTickets.statusProjectTicket}</td>
        <td width="100">${listTickets.deadlineTicket}</td>
    </tr>
</c:forEach>
    </table>
</div>
<br>
    <div class="chooseTicket">
        <table>
            <tr height="40">
                <td width="100">${chosenTicket.nameProjectTicket}</td>
                <td width="100">${chosenTicket.specification}</td>
                <td width="100">${chosenTicket.statusProjectTicket}</td>
                <td width="100">${chosenTicket.deadlineTicket}</td>
            </tr>
        </table>
    </div>
<%--<c:forEach items="${collectionWorkers}" var="listWorkers">--%>
    <%--<a href="#">${listWorkers.nameWorker}</a>--%>
    <%--<hr>--%>
<%--</c:forEach>--%>
</body>
</html>
