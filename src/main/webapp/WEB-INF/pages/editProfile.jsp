<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 07.07.2017
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="hidden" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Профиль</title>
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
</head>
<body>
<div class="container">
    <form:form class="form-horizontal" action="edit" method="post" modelAttribute="profileData">
        <fieldset>

            <!-- Form Name -->
            <legend>Редактировать</legend>

            <!-- Text input-->
            <div class="form-group">
                    <%--<spring:message code="username" var="username"/>--%>
                <label class="col-md-4 control-label" for="textinput">position</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="position"
                                id="textinput" name="textinput"/>
                </div>
            </div>
            <div class="form-group">
                    <%--<spring:message code="username" var="username"/>--%>
                <label class="col-md-4 control-label" for="textinput">employment status</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="employmentStatus"
                                id="textinput" name="textinput"/>
                </div>
            </div>
            <div class="form-group">
                    <%--<spring:message code="username" var="username"/>--%>
                <label class="col-md-4 control-label" for="textinput">department</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="department"
                                id="textinput" name="textinput"/>
                </div>
            </div>
            <div class="form-group">
                    <%--<spring:message code="username" var="username"/>--%>
                <label class="col-md-4 control-label" for="textinput">telephone</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="telephone"
                                id="textinput" name="textinput"/>
                </div>
            </div>
            <div class="form-group">
                    <%--<spring:message code="username" var="username"/>--%>
                <label class="col-md-4 control-label" for="textinput">email</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="email" id="textinput"
                                name="textinput"/>
                </div>
            </div>


                <%--<!-- File Button -->--%>
                <%--<div class="form-group">--%>
                <%--<label class="col-md-4 control-label" for="filebutton">upload photo</label>--%>
                <%--<div class="col-md-4">--%>
                <%--<input id="filebutton" name="filebutton" class="input-file" type="file">--%>
                <%--</div>--%>
                <%--</div>--%>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="singlebutton"></label>
                <div class="col-md-4">
                    <input type="submit" id="singlebutton" class="btn btn-primary" value="submit"/>

                </div>
            </div>

        </fieldset>
    </form:form>
</div>
</body>
