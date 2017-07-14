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
<%@ taglib prefix="mytag" uri="/WEB-INF/taglib/tags.tld" %>
<html>
<head>
    <title>Все тикеты</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <script type="text/javascript"></script>
    <style>
        body {
            background: #D3D3D3;
        }
        #centerLayer {
            position: absolute;
            margin-left: 10%;
            margin-right: 10%;
            margin-top: 3%;
            padding: 10px;
            overflow: auto;
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
            border-top-left-radius: 5px;
        }
        table th:last-child {
            border-top-right-radius: 5px;
        }
        td {
            border-top: 4px solid #F2906B;
            padding: 1px;
            /*background: white;*/
        }
        table td:first-child {
            border-bottom-left-radius: 10px;
        }
        table td:last-child {
            border-bottom-right-radius: 10px;
        }
    </style>
</head>
<body>
<%@include file="menu.jsp" %>

<spring:message code="allTickets.ticket.name" var="nameTicket"/>
<spring:message code="allTickets.ticket.specification" var="specificationTicket"/>
<spring:message code="ticket.status" var="statusTicket"/>
<spring:message code="ticket.status.choose" var="statusTicketChoose"/>
<spring:message code="ticket.status.opened" var="statusTicketOpened"/>
<spring:message code="ticket.status.inProgress" var="statusTicketInProgress"/>
<spring:message code="ticket.status.paused" var="statusTicketPaused"/>
<spring:message code="ticket.status.readyForTest" var="statusTicketReadyForTest"/>
<spring:message code="ticket.status.finished" var="statusTicketFinished"/>
<spring:message code="allTickets.ticket.status.allTickets" var="allTickets"/>
<spring:message code="allTickets.ticket.label.show" var="labelShow"/>
<spring:message code="allTickets.ticket.startDate" var="startDateTicket"/>
<spring:message code="allTickets.ticket.endDate" var="endDateTicket"/>
<spring:message code="allTickets.ticket.comments" var="comments"/>
<spring:message code="allTickets.ticket.noComments" var="noComments"/>
<spring:message code="allTickets.ticket.writeComments" var="writeComments"/>
<spring:message code="allTickets.ticket.employee" var="employee"/>
<spring:message code="allTickets.ticket.choseWorker" var="choseWorker"/>
<spring:message code="allTickets.ticket.deadline" var="deadlineDate"/>
<spring:message code="allTickets.ticket.worker" var="workerTicket"/>

<div id="centerLayer">
    <form:form action="allTickets" method="post" modelAttribute="ticket">
            <select name="statusProject" path="statusProjectTicket">
                <option value="status" disabled selected>${statusTicketChoose}</option>
                <option value="opened">${statusTicketOpened}</option>
                <option value="in_progress">${statusTicketInProgress}</option>
                <option value="paused">${statusTicketPaused}</option>
                <option value="ready_for_testing">${statusTicketReadyForTest}</option>
                <option value="finished">${statusTicketFinished}</option>
                <option value="all tickets">${allTickets}</option>
                <div><form:errors path="statusProjectTicket" style="color:red"/></div>
            <select>
            <input type="submit" value="${labelShow}">
    </form:form>
        <table class="tableOfTickets">
            <tr height="40">
                <th width="100">${nameTicket}</th>
                <th width="100">${statusTicket}</th>
                <th width="100">${deadlineDate}</th>
            </tr>
        <c:forEach items="${collectionTickets}" var="listTickets">
            <tr height="40" class="clickable" style="background-color:#ECECEC;"
                onMouseOver="this.style.backgroundColor='#00FFFF';"
                onMouseOut="this.style.backgroundColor='#ECECEC'"
                onclick="location.href='/chooseTicket${listTickets.idProjectTicket}'">
                <td hidden>${listTickets.idProjectTicket}</td>
                <td width="100">${listTickets.nameProjectTicket}</td>
                <td width="100">${listTickets.statusProjectTicket}</td>
                <td width="100">${listTickets.deadlineTicket}</td>
            </tr>
        </c:forEach>
        </table>
</div>
</body>
</html>
