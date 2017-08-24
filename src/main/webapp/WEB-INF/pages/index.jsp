<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 15.06.2017
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../resources/springMessages.jsp" %>
    <meta charset="UTF-8">
    <title>${titleAuthorization}</title>
    <%@include file="../resources/head.jsp" %>
    <style>
        <%@include file='../css/style_for_authorization.css' %>
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>

</head>

<body>
<div class="body"></div>
<div class="grad"></div>
<div class="header">
    <div>ERP<span>System</span></div>
</div>
<br>
<div class="login">
    <span style="float: right">
             <a href="?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png" width="8%"
                                     height="10%"></a>
            <a href="?lang=ru"><img
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg=="
                    width="8%" height="10%"></a>
    </span>
    <div>
        <a href="<%--${pageContext.request.contextPath}--%>/changePassword">${forgotPassword}</a>
    </div>
    <c:if test="${successChangePassword == true}">
        <div>
            <p>${successChangePas}</p>
        </div>
    </c:if>
    <br><br>
    <form:form class="form-inline" action="main" method="post" modelAttribute="logPass">
        <form:input type="text" placeholder="${username}" autocomplete="off" path="login"/>
        <div><form:errors path="login" style="color:red"/></div>
        <form:input type="password" placeholder="${password}" path="password"/>
        <div><form:errors path="password" style="color:red"/></div>
        <br><br>
        <input type="submit" value="${labelAuthorization}"/>
    </form:form>
</div>
</body>
</html>
