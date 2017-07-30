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
    <%@include file="../resources/springMessages.jsp" %>
    <meta charset="UTF-8">
    <title>${addTicket}</title>
    <%@include file="../resources/head.jsp" %>
    <%@include file="../resources/bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
    <style>
        <%@include file="../css/style_for_new_worker_ticket.css"%>
    </style>
</head>

<body>
<form:form action="isSuccessAddNewTicket" method="post" modelAttribute="ticket">
<div id="centerLayer">
    <table>
        <tr height="40">
            <td>${nameTicket}</td>
            <td><form:input type="text" name="nameProjectTicket" path="nameProjectTicket"/>
                <div><form:errors path="nameProjectTicket" style="color:red"/></div>
            </td>
        </tr>
        <tr>
            <td>${specificationTicket}</td>
            <td><form:textarea id="textzone" name="specification" path="specification" border-radius="10" cols="50"
                               rows="4"/>
                <div><form:errors path="specification" style="color:red"/></div>
            </td>
        </tr>
        <tr height="10"></tr>
        <tr height="40" hidden>
            <td>${statusTicket}</td>
            <td>
                <form:select path="statusProjectTicket">
                <option value="opened" selected>${statusTicketOpened}</option>
                </form:select>
        </tr>
        <tr height="40">
            <td>${deadlineTicket}</td>
            <td height="30"><input type="date" name="deadlineDate" path="deadlineTicket"/>
                <div><form:errors path="deadlineTicket" style="color:red"/></div>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="${labelComplete}"></td>
        </tr>
    </table>
    </form:form>
    <br>
    <form margin-top="20px" action="${pageContext.request.contextPath}/" method="get">
        <input type="submit" value="${labelBack}">
    </form>
</div>
</body>
</html>
