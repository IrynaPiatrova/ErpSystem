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
    <style>
        <%@include file='../css/table.css' %>
    </style>
</head>
<body>
<%@include file="menu.jsp" %>
<%@include file="springMessages.jsp"%>

<div id="centerLayer">
    <div class="container">
        <form:form action="allTickets" method="post" modelAttribute="ticket">
            <select name="statusProject" path="statusProjectTicket" class="selectpicker">
                <option value="status" disabled selected>${statusTicketChoose}</option>
                <option value="opened">${statusTicketOpened}</option>
                <option value="in_progress">${statusTicketInProgress}</option>
                <option value="paused">${statusTicketPaused}</option>
                <option value="ready_for_testing">${statusTicketReadyForTest}</option>
                <option value="finished">${statusTicketFinished}</option>
                <option value="all tickets">${allTickets}</option>
                <div><form:errors path="statusProjectTicket" style="color:red"/></div>
            </select>
            <input type="submit" value="${labelShow}" class="btn btn-default">
        </form:form>
        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Тикеты</h3>
                    </div>
                    <table class="table table-hover" id="dev-table">
                        <thead>
                        <tr>
                            <th>${nameTicket}</th>
                            <th>${statusTicket}</th>
                            <th>${deadlineDate}</th>
                        </tr>
                        <c:forEach items="${collectionTickets}" var="listTickets">
                        <tr class="clickable"
                            onclick="location.href='/chooseTicket${listTickets.idProjectTicket}'">
                            <td hidden>${listTickets.idProjectTicket}</td>
                            <td>${listTickets.nameProjectTicket}</td>
                            <td>${listTickets.statusProjectTicket}</td>
                            <td>${listTickets.deadlineTicket}</td>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
