<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 16.07.2017
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="hidden" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
</head>
<body>
<%@include file="menu.jsp" %>
<c:when test="${logedAs == true}">
    <h4>Не Залогинен</h4>
</c:when>
<c:otherwise>
    <form:form action="change" modelAttribute="profile">
        <h4>${profile.keyWord}</h4>
        <table>
            <tr>
                <td>Ответ на ключевой вопрос</td>
                <td><input type="text" name="answerOnKeyWord">
                    <div><form:errors path="answerOnKeyWord" style="color:red"/></div></td>
            </tr>

            <tr>
                <td>Введите новый пароль</td>
                <td><input type="text" name="newPassword">
                    <div><form:errors path="worker.password" style="color:red"/></div></td>
            </tr>

            <tr>
                <td>Повторите пароль</td>
                <td><input type="text" name="repeatNewPassword">
                    <div><form:errors path="worker.password" style="color:red"/></div></td>
            </tr>

            <tr>
                <td>
                    <input type="submit" value="Подтвердить">
                </td>
            </tr>
        </table>
    </form:form>
</c:otherwise>
</body>
</html>
