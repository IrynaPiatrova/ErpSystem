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
        #tickets{
            margin-left: 5%;
            margin-top: 0%;
            float: left;
            width: 40%;
            height: 55%;
            overflow: scroll;
        }
        .tableOfTickets{
            font-size: medium;
            width: 100%;
        }
        #chooseTicket{
            margin-top: 0%;
            margin-right: 0%;
            float: right;
            width: 50%;
            height: 30%;
            border-color: #5379fa;
        }
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
            table-layout: fixed;
            font-size: 14px;
            text-align: center;
            border-collapse: collapse;
            border-radius: 10px;
            box-shadow: 0 0 0 10px #F2906B;
            color: #452F21;
        }
        th {
            padding: 10px 8px;
            background: white;
            text-align: center;
        }
        table th:first-child {
            border-top-left-radius: 10px;
        }
        table th:last-child {
            border-top-right-radius: 10px;
        }
        td {
            border-top: 4px solid #F2906B;
            padding: 8px;
            background: white;
        }
        table td:first-child {
            border-bottom-left-radius: 10px;
        }
        table td:last-child {
            border-bottom-right-radius: 20px;
        }
        #underTable{
            margin-top: 6%;
            width: 500px;
        }
        #chooseWorker{
            margin-top: 3%;
        }
    </style>
</head>
<body>
<%@include file="menu.jsp" %>
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
<div id="tickets">
    <table class="tableOfTickets">
        <tr height="40">
            <th width="100">Название задачи</th>
            <th width="100">Статус задачи</th>
            <th width="100">Дедлайн задачи</th>
        </tr>
<c:forEach items="${collectionTickets}" var="listTickets">
    <tr height="40">
        <td hidden>${listTickets.idProjectTicket}</td>
        <td width="100"><a href="/chooseTicket${listTickets.idProjectTicket}">${listTickets.nameProjectTicket}</a></td>
        <td width="100">${listTickets.statusProjectTicket}</td>
        <td width="100">${listTickets.deadlineTicket}</td>
    </tr>
</c:forEach>
    </table>
</div>
    <c:if test="${isChooseTicket}">
    <div id="chooseTicket">
        <h4>Тикет: ${chosenTicket.nameProjectTicket}</h4>
        <table>
            <th>Status</th>
            <th>Start date</th>
            <th>End date</th>
            <th>Deadline</th>
            <th>Worker</th>
            <tr height="40">
                <td hidden>${chosenTicket.idProjectTicket}</td>
                <td width="120">${chosenTicket.statusProjectTicket}</td>
                <td width="120">${chosenTicket.startTicketDate}</td>
                <td width="120">${chosenTicket.endTicketDate}</td>
                <td width="120">${chosenTicket.deadlineTicket}</td>
                <td width="120">${chosenTicket.idWorker}</td>
            </tr>
        </table>
        <table id="underTable">
            <th width="600" align="center">specification</th>
            <tr><td width="600">${chosenTicket.specification}</td></tr>
        </table>
        <div id="chooseWorker">
        <form action="/chooseWorkerOnTicket" method="post">
            <%--<select name="workerName" path="nameWorker">--%>
                <%--<option value="status" disabled selected>Исполнитель:</option>--%>
            <%--<c:forEach items="${collectionWorkers}" var="listWorkers">--%>
                    <%--<option >${listWorkers.nameWorker}</option>--%>
            <%--</c:forEach>--%>
                <%--<select>--%>
                <hidden><input type="text" name="idTicket" value="${chosenTicket.idProjectTicket}"></hidden>
                    <input list="workers" name="nameWorker">
                    <datalist id="workers">
                        <c:forEach items="${collectionWorkers}" var="listWorker">
                            <option>${listWorker.idWorker}.${listWorker.nameWorker}</option>
                        </c:forEach>
                    </datalist>
                <input type="submit" value="Назначить исполнителя">
        </form>
        </div>
    </div>
    </c:if>
<%--<c:forEach items="${collectionWorkers}" var="listWorkers">--%>
    <%--<a href="#">${listWorkers.nameWorker}</a>--%>
    <%--<hr>--%>
<%--</c:forEach>--%>
</body>
</html>
