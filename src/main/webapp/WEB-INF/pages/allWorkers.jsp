<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 10.07.2017
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>AllWorkers</title>
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
</head>
<body>
<%@include file="menu.jsp" %>
<div class="tableFilms">
    <table class="table table-bordered">

        <c:if test="${not empty workers}">
            <c:forEach items="${workers}" var="worker">
                <tr>
                    <td><c:out value="${worker}"/></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
</div>
</body>
</html>
