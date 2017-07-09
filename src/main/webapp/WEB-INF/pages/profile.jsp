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
    <title>Профиль</title>
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
</head>
<body>
<%@include file="menu.jsp" %>
<form action="edit" method="get"><input type="submit" class="btn btn-default btn-lg" value="Редактировать"></form>
<div class="container">
    <table class="table table-striped">
        <tbody>
        <tr>
            <c:choose>
                <c:when test="${photo == null}">
                    <td>
                        <div class="col-sm-2 col-md-2"><img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png"
                                                            class="img-rounded img-responsive"></div>
                    </td>
                </c:when>
                <c:otherwise>
                    <td>
                        <div class="col-sm-2 col-md-2"><img src="<mytag:convertImage imageByte="${photo}"/>"
                                                            class="img-rounded img-responsive"></div>
                    </td>
                </c:otherwise>
            </c:choose>


            <td></td>
        </tr>
        <tr>
            <td><h1>${nameUser}</h1></td>
        </tr>
        <tr>
            <td>Департамент</td>
            <td>${profile.department}</td>

        </tr>
        <tr>
            <td>Позиция</td>
            <td>${profile.position}</td>
        </tr>
        <tr>
            <td>Почта</td>
            <td><a href="mailto:${profile.email}?subject=Вопрос">${profile.email}</a></td>
        </tr>
        <tr>
            <td>Телефон</td>
            <td>${profile.telephone}</td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
