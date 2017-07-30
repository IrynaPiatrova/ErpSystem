<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 16.06.2017
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <%@include file="../resources/springMessages.jsp" %>
    <title>${titleMainPage}</title>
    <meta charset="UTF-8">
    <%@include file="../resources/head.jsp" %>
    <%@include file="../resources/bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
    <%@include file="../resources/scriptMainPage.jsp"%>
    <style>
        <%@include file='../css/style_for_up_body.css' %>
        <%@include file='../css/table.css' %>
        body {
            background: url(http://komionline.ru/files/media/image/preview/rab00_1.jpg) no-repeat;
            -moz-background-size: 100%; /* Firefox 3.6+ */
            -webkit-background-size: 100%; /* Safari 3.1+ и Chrome 4.0+ */
            -o-background-size: 100%; /* Opera 9.6+ */
            background-size: 100%; /* Современные браузеры */
        }
    </style>
</head>

<body>
<div class="container">
    <c:choose>
        <c:when test="${isAdmin}">
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">${labelNotAppointedAndReadyTickets}</h3>
                        </div>
                        <table class="table table-hover" id="dev-table1">
                            <thead>
                            <tr>
                                <th>${nameTicket}</th>
                                <th>${specificationTicketDescription}</th>
                                <th>${statusTicketDescription}</th>
                            </tr>
                            <c:choose>
                            <c:when test="${collectionTickets.size()==0}">
                            <tr>
                                <td> ${labelTicketNo} </td>
                            </tr>
                            </c:when>
                            <c:otherwise>
                            <c:forEach items="${collectionTickets}" var="listTickets">
                            <tr class="clickable"
                                onclick="location.href='/chooseTicket${listTickets.idProjectTicket}'">
                                <td hidden>${listTickets.idProjectTicket}</td>
                                <td>${listTickets.nameProjectTicket}</td>
                                <td>${listTickets.specification}</td>
                                <td>${listTickets.statusProjectTicket}</td>
                            </tr>
                            </c:forEach>
                            </c:otherwise>
                            </c:choose>
                        </table>
                    </div>
                </div>
            </div>
            <hr style="margin-left: 50px"/>
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">${labelRequests}</h3>
                        </div>
                        <table class="table table-hover" id="dev-table2">
                            <thead>
                            <tr>
                                <th>${workerNameDescription}</th>
                                <th>${labelVacationType}</th>
                                <th>${absenceStart}</th>
                                <th>${absenceEnd}</th>
                            </tr>
                            <c:choose>
                            <c:when test="${collectionVacation.size()==0}">
                            <tr>
                                <td> ${labelVacationNo} </td>
                            </tr>
                            </c:when>
                            <c:otherwise>
                            <c:forEach items="${collectionVacation}" var="listVacations">
                            <tr class="rowLink">
                                <td class="choosedId" hidden>${listVacations.idTimeVocation}</td>
                                <td>${listVacations.worker.nameWorker}</td>
                                <td>${listVacations.type}</td>
                                <td>${listVacations.startVocDate}</td>
                                <td>${listVacations.endVocDate}</td>
                            </tr>
                            </c:forEach>
                            </c:otherwise>
                            </c:choose>
                        </table>
                    </div>
                </div>
            </div>
            <div style="margin-left: 10px">
                <table>
                    <tr>
                        <form action="${pageContext.request.contextPath}/vacationResponse" method="post"
                              id="formButton">
                            <p/>
                            <input type="hidden" name="idTimeVacation" id="idTimeVacation" value="">
                            <input type="hidden" name="choice" id="choice" value="">
                            <td>
                                <input type="hidden" id="acceptButton" value="${acceptVacation}"
                                       class="btn btn-default">
                            </td>
                            <td style="margin-left: 20px">
                                <input type="hidden" id="rejectButton" value="${rejectVacation}"
                                       class="btn btn-default">
                            </td>
                        </form>
                    </tr>
                </table>
            </div>


        </c:when>
        <c:otherwise>
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">${labelActualTicket}</h3>
                        </div>
                        <table class="table table-hover" id="dev-table">
                            <thead>
                            <tr>
                                <th>${nameTicket}</th>
                                <th>${specificationTicketDescription}</th>
                                <th>${deadlineDate}</th>
                            </tr>
                            <c:choose>
                            <c:when test="${collectionTickets.size()==0}">
                            <tr>
                                <td> ${labelActualTicketNo} </td>
                            </tr>
                            </c:when>
                            <c:otherwise>
                            <c:forEach items="${collectionTickets}" var="listTickets">
                            <tr class="clickable"
                                onclick="location.href='/chooseTicket${listTickets.idProjectTicket}'">
                                <td hidden>${listTickets.idProjectTicket}</td>
                                <td>${listTickets.nameProjectTicket}</td>
                                <td>${listTickets.specification}</td>
                                <td>${listTickets.deadlineTicket}</td>
                            </tr>
                            </c:forEach>
                            </c:otherwise>
                            </c:choose>
                        </table>
                    </div>
                </div>
            </div>
            <hr style="margin-left: 50px"/>

            <div style="margin-left: 10px">
                <button class="btn btn-default" id="requestButton"
                        style="margin-bottom: 20px; margin-left: 70px">${labelCreateRequest}</button>
                <div style="color: crimson; font-size: large">${vacationValidation}</div>
                <table>
                    <tr>
                        <td>
                            <p/>
                            <input type="hidden" id="sickLeaveButton" value="${labelRequestSickLeave}"
                                   class="btn btn-default" style="margin-right: 20px">
                        </td>
                        <td>
                            <p/>
                            <input type="hidden" id="vacationButton" value="${labelRequestVacation}"
                                   class="btn btn-default">
                        </td>
                    </tr>
                </table>

                <form:form action="/createRequestVacation" method="post" modelAttribute="vacation" id="form1">
                    <table id="sickLeaveTR" style="display: none">
                        <tr>
                            <td>${absenceStart}</td>
                            <td><form:input type="date" name="StartDate" path="startVocDate" height="30"/>
                                <div><form:errors path="startVocDate" style="color:red"/></div>
                            </td>
                        </tr>
                        <tr>
                            <td>${absenceEnd}</td>
                            <td><form:input type="date" name="EndDate" path="endVocDate" height="30"/>
                                <div><form:errors path="endVocDate" style="color:red"/></div>
                            </td>
                        </tr>
                        <tr>
                            <form:input type="hidden" path="type" value="sick leave"/>
                            <input type="hidden" id="requestSickLeaveButton" value="${labelCreateRequest}"
                                   class="btn btn-default">
                        </tr>
                    </table>
                </form:form>

                <form:form action="/createRequestVacation" method="post" modelAttribute="vacation" id="form2">
                    <table id="vacationTR" style="display: none">
                        <tr>
                            <td>${absenceStart}</td>
                            <td><form:input type="date" name="StartDate" path="startVocDate" height="30"/>
                                <div><form:errors path="startVocDate" style="color:red"/></div>
                            </td>
                        </tr>
                        <tr>
                            <td>${absenceEnd}</td>
                            <td><form:input type="date" name="EndDate" path="endVocDate" height="30"/>
                                <div><form:errors path="endVocDate" style="color:red"/></div>
                            </td>
                        </tr>
                        <tr>
                            <form:input type="hidden" path="type" value="vacation"/>
                            <input type="hidden" id="requestVacationButton" value="${labelCreateRequest}"
                                   class="btn btn-default">
                        </tr>
                    </table>
                </form:form>

            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
