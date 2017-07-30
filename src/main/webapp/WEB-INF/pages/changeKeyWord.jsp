<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 23.07.2017
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="hidden" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="../resources/springMessages.jsp" %>
    <title>${changeKeyWord}</title>
    <meta charset="UTF-8">
    <%@include file="../resources/head.jsp" %>
    <%@include file="../resources/bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
</head>
<body>

<div class="container">
    <form:form class="form-horizontal" action="changeKeyWord" modelAttribute="profile">

        <div class="form-group">
            <label class="col-md-4 control-label">${writePassword}</label>
            <div class="col-md-4">
                <input class="form-control input-md" type="password" autocomplete="off"
                       name="password"/>
            </div>
            <div><form:errors path="worker.password" style="color:red"/></div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label">${chooseKeyQuestion}</label>
            <div class="col-md-4">
                <select class="form-control input-md" name="keyWord" width="200">
                    <option value="status" disabled selected>${keyQuestion}</option>
                    <option value="Mother's girls surname">${chooseKeyQuestion1}</option>
                    <option value="What's city where you born">${chooseKeyQuestion2}</option>
                    <option value="Favourite eat">${chooseKeyQuestion3}</option>
                </select>
            </div>
            <div><form:errors path="keyWord" style="color:red"/></div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label">${writeAnswerOnQuestion}</label>
            <div class="col-md-4">
                <input class="form-control input-md" type="text" autocomplete="off"
                       id="inputAnswerOnKeyWord"
                       name="answerOnKeyWord"/>
            </div>
            <div><form:errors path="answerOnKeyWord" style="color:red"/></div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <input type="submit" id="singlebutton" class="btn btn-primary" value="${labelComplete}"/>
            </div>
        </div>

    </form:form>
</div>
</body>
</html>
