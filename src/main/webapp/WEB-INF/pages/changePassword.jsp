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
    <style>
        #centerLayer {
            position: absolute;
            margin-left: 30%;
            margin-top: 10%;
            padding: 10px;
            overflow: auto;
        }
        #mainContent{
            margin-top: 5%;
        }
    </style>
</head>
<body>
<spring:message code="exist.keyWord" var="keywordExist"/>
<c:choose>
<c:when test="${logedAs == null}">
    <div id="centerLayer">
        <form:form action="isLoginExist" modelAttribute="worker">
            <table>
                <tr>
                    <td>Введите логин</td>
                    <td><input type="text" name="login" autocomplete="off">
                        <div><form:errors path="login" style="color:red"/></div></td>
                </tr>
                <tr>
                    <td><input type="submit"></td>
                </tr>
            </table>
        </form:form>
    </div>
</c:when>
<c:otherwise>
    <c:if test="${logedAs != true}">
        <%@include file="menu.jsp" %>
    </c:if>
    <form:form action="change" modelAttribute="profile">
        <div id="centerLayer">
        <div>
            <c:choose>
                <c:when test="${keyWord == null}">
                    <h4>${keywordExist}</h4>
                </c:when>
                <c:otherwise>
                    <h4>${keyWord}</h4>
                        <div id="mainContent">
                            <table>

                                <tr>
                                    <td>Ответ на ключевой вопрос</td>
                                    <td><input type="text" name="answerOnKeyWord">
                                        <div><form:errors path="answerOnKeyWord" style="color:red"/></div></td>
                                </tr>

                                <tr>
                                    <td>Введите новый пароль</td>
                                    <td><input type="password" name="newPassword"></td>
                                </tr>

                                <tr>
                                    <td>Повторите пароль</td>
                                    <td><input type="password" name="repeatNewPassword">
                                        <div><form:errors path="worker.password" style="color:red"/></div></td>
                                </tr>

                                <tr>
                                    <td>
                                        <input type="submit" value="Подтвердить">
                                    </td>
                                </tr>
                            </table>
                        </div>
                </c:otherwise>
            </c:choose>
        </div>
        </div>
    </form:form>
</c:otherwise>
</c:choose>
</body>
</html>
