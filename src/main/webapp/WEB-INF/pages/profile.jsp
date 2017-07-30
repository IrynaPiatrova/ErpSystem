<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 04.07.2017
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/taglib/tags.tld" %>
<html>
<head>
    <%@include file="../resources/springMessages.jsp" %>
    <meta charset="UTF-8">
    <title>${labelProfile}</title>
    <%@include file="../resources/head.jsp" %>
    <%@include file="../resources/bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
</head>

<body>
<form action="edit" method="get"><input type="submit" class="btn btn-default btn-lg" value="${profileEdition}"></form>
<div class="container">
    <table class="table table-striped">
        <tbody>
        <tr>
            <%--если нет в базе фото то по дефолту--%>
            <%--<c:choose>--%>
            <%--<c:when test="${photo == null}">--%>
            <%--<td>--%>
            <%--<div class="col-sm-2 col-md-2"><img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png"--%>
            <%--class="img-rounded img-responsive"></div>--%>
            <%--</td>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <td>
                <div class="col-sm-2 col-md-2"><img src="<mytag:convertImage imageByte="${photo}"/>"
                                                    class="img-rounded img-responsive"></div>
            </td>
            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <td></td>
        </tr>
        <tr>
            <td><h1>${nameUser}</h1></td>
        </tr>
        <tr>
            <td>${profileDepartmentDescription}</td>
            <td>${profile.department}</td>

        </tr>
        <tr>
            <td>${profilePositionDescription}</td>
            <td>${profile.position}</td>
        </tr>
        <tr>
            <td>${profileEmailDescription}</td>
            <td><a href="mailto:${profile.email}?subject=Вопрос">${profile.email}</a></td>
        </tr>
        <tr>
            <td>${profileTelephoneDescription}</td>
            <td>${profile.telephone}</td>
        </tr>
        </tbody>
    </table>
    <c:if test="${successChangePassword == true}">
        <div>
            <p>${successChangePas}</p>
        </div>
    </c:if>
    <c:if test="${successChangeKeyWord == true}">
        <div>
            <p>${successChangeKey}</p>
        </div>
    </c:if>
</div>
</body>
</html>
