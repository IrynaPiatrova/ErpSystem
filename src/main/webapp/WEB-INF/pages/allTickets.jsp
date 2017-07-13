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
        #tickets{
            margin-left: 5%;
            margin-top: 0%;
            float: left;
            width: 40%;
            height: 75%;
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
            border-top-left-radius: 5px;
        }
        table th:last-child {
            border-top-right-radius: 5px;
        }
        td {
            border-top: 4px solid #F2906B;
            padding: 1px;
            background: white;
        }
        table td:first-child {
            border-bottom-left-radius: 10px;
        }
        table td:last-child {
            border-bottom-right-radius: 10px;
        }
        #underTable{
            margin-top: 6%;
            width: 500px;
        }
        #chooseWorker{
            margin-top: 5%;
        }
        .clickable {
            cursor: pointer;
        }
        .tableTicket{
            table-layout: fixed;
        }
        #allComments{
            width: 500px;
            height: 200px;
            background-color: white;
            overflow: auto;
            margin-top: 2%;
        }
        #allCommentsPhoto{
            float: left;
        }
        #allCommentsComment{
            margin-left: 3%;
        }
        #writeComment{
            margin-top: 5%;
            width: 650px;
            height: 50px;
        }
        #comment_photo{
            align-content: center;
            width: 60px;
            float: left;
        }
        #comment_comment{
            align-content: center;
            width: 540px;
            margin-left: 0%;
        }
        #comment_submit{
            align-content: center;
            width: 50px;
            float: right;
            margin-right: 15%;
            margin-bottom: 25%;
        }
    </style>
    <script>
        function empty_form () {
            var txt = document.getElementById('msg').value;
            if(txt == '') {
                alert('Выберите сотрудника.');
                return false;
            }
            return true;
        }
    </script>
    <script>
        function empty_comment () {
            var txt = document.getElementById('commentMessage').value;
            if(txt == '') {
                alert('Напишите сообщение.');
                return false;
            }
            return true;
        }
    </script>
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
<div id="tickets">
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
    <c:if test="${isChooseTicket}">
    <div id="chooseTicket">
        <h4>${nameTicket}: ${chosenTicket.nameProjectTicket}</h4>
        <table class="tableTicket">
            <th>${statusTicketChoose}</th>
            <th>${startDateTicket}</th>
            <th>${endDateTicket}</th>
            <th>${deadlineDate}</th>
            <th>${workerTicket}</th>
            <tr height="40">
                <td hidden>${chosenTicket.idProjectTicket}</td>
                <td width="120">${chosenTicket.statusProjectTicket}</td>
                <td width="120">${chosenTicket.startTicketDate}</td>
                <td width="120">${chosenTicket.endTicketDate}</td>
                <td width="120">${chosenTicket.deadlineTicket}
                        <%--${chosenTicket.deadlineTicket.day}---%>
                        <%--${chosenTicket.deadlineTicket.month}</td>--%>
                <td width="120">${chosenTicket.idWorker.nameWorker}</td>
            </tr>
        </table>
        <table id="underTable">
            <th width="600" align="center">${specificationTicket}</th>
            <tr><td width="600">${chosenTicket.specification}</td></tr>
        </table>
        <c:choose>
            <c:when test="${collectionOfComments.size() == 0}">
                <h4>${noComments}</h4>
            </c:when>
        <c:when test="${collectionOfComments.size() != 0}">
            <h4>${comments}</h4>
        <div id="allComments">
            <c:forEach items="${collectionOfComments}" var="listComment">
                <div id="allCommentsPhoto">
                    <c:choose>
                        <c:when test="${listComment.photo == null}">
                            <img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" class="img-responsive img-circle"
                                 width="50px" height="50px">
                        </c:when>
                        <c:otherwise>
                            <img src="<mytag:convertImage imageByte="${listComment.photo}"/>" class="img-responsive img-circle"
                                 width="50px" height="50px">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div id="allCommentsComment">
                    <p style="color:blue">${listComment.nameWorker}</p>
                    <p>${listComment.comment}</p>
                    <hr>
                </div>
            </c:forEach>
        </div>
        </c:when>
        </c:choose>
        <div id="form">
            <form action="/writeComment" method="post" onsubmit="return empty_comment()">
                <div id="writeComment">
                <div id="comment_photo">
                <c:choose>
                    <c:when test="${workerPhoto == null}">
                        <img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" class="img-responsive img-circle"
                             width="50px" height="50px">
                    </c:when>
                    <c:otherwise>
                        <img src="<mytag:convertImage imageByte="${workerPhoto}"/>" class="img-responsive img-circle"
                             width="50px" height="50px">
                    </c:otherwise>
                </c:choose>
                </div>
                <div id="comment_comment">
                    <input type="text" name="idTicket" hidden="true" value="${chosenTicket.idProjectTicket}">
                    <textarea id="commentMessage" name="text_comment" cols="60" rows="2" placeholder="${writeComments}"></textarea>
                </div>
                <div id="comment_submit">
                    <input type="image" src="http://s1.iconbird.com/ico/0612/GooglePlusInterfaceIcons/w128h1281338911579directionalright.png" height="30" width="30" />
                </div>
                </div>
            </form>
        </div>
        <div id="chooseWorker">
        <form:form action="/chooseWorkerOnTicket" method="post" onsubmit="return empty_form()">
                <input type="text" name="idTicket" hidden="true" value="${chosenTicket.idProjectTicket}">
                    <input list="workers" id="msg" name="nameWorker" placeholder="${employee}">
                    <datalist id="workers">
                        <c:forEach items="${collectionWorkers}" var="listWorker">
                            <option>${listWorker.idWorker}.${listWorker.nameWorker}</option>
                        </c:forEach>
                    </datalist>
                <input type="submit" value="${choseWorker}">
        </form:form>
        </div>
    </div>
    </c:if>
</body>
</html>
