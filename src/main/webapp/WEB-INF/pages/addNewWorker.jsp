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
    <%@include file="../resources/springMessages.jsp" %>
    <meta charset="UTF-8">
    <title>${addNewWorker}</title>
    <%@include file="../resources/head.jsp" %>
    <%@include file="../resources/bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
    <style>
        <%@include file="../css/style_for_new_worker_ticket.css"%>
    </style>
</head>

<body>
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
    <form action="${pageContext.request.contextPath}/addNewWorker" method="get">
        <input type="submit" value="${labelBack}" width="100">
    </form>
</div>
</body>
</html>
