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
    <%@include file="../resources/springMessages.jsp" %>
    <title>${changePassword}</title>
    <meta charset="UTF-8">
    <%@include file="../resources/head.jsp" %>
    <%@include file="../resources/bootstrapLinks.jsp" %>
</head>
<body>
<c:choose>
<c:when test="${logedAs == null}">
<div class="container">
    <form:form class="form-horizontal" action="isLoginExist" modelAttribute="worker">

    <a href="?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png"
                            class="img-responsive img-circle"
                            width="30px" height="30px"></a>
    <a href="?lang=ru"><img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg=="
            class="img-responsive img-circle"
            width="30px" height="30px"></a>

    <div>

        <div class="form-group">
            <label class="col-md-4 control-label">${writeLogin}</label>
            <div class="col-md-4">
                <input class="form-control input-md" type="text" autocomplete="off"
                       name="login"/>
            </div>
            <div><form:errors path="login" style="color:red"/></div>
        </div>

        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <input type="submit" id="singlebutton" class="btn btn-primary" value="${labelComplete}"/>
            </div>
        </div>

    </div>

    </form:form>

    </c:when>
    <c:otherwise>
    <c:choose>
    <c:when test="${logedAs != true}">
        <%@include file="menu.jsp" %>
    </c:when>
    <c:otherwise>
    <a href="?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png"
                            class="img-responsive img-circle"
                            width="30px" height="30px"></a>
    <a href="?lang=ru"><img
            src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg=="
            class="img-responsive img-circle"
            width="30px" height="30px"></a>
    </c:otherwise>
    </c:choose>
    <div class="container">
        <form:form class="form-horizontal" action="changePas" modelAttribute="profile">

            <c:choose>
                <c:when test="${keyWord == null}">
                    <h4>${keywordExist}</h4>
                </c:when>
                <c:otherwise>

                    <div class="form-horizontal">

                        <div class="form-group" id="keyQuestion" style="font-size: large">
                            <label class="col-md-4 control-label">${keyWord}</label>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">${keyQuestionAnswer}</label>
                            <div class="col-md-4">
                                <input class="form-control input-md" type="text" autocomplete="off"
                                       name="answerOnKeyWord"/>
                            </div>
                            <div><form:errors path="answerOnKeyWord" style="color:red"/></div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">${newPassword}</label>
                            <div class="col-md-4">
                                <input class="form-control input-md" type="password" autocomplete="off"
                                       name="newPassword"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label">${repeatNewPassword}</label>
                            <div class="col-md-4">
                                <input class="form-control input-md" type="password" autocomplete="off"
                                       name="repeatNewPassword"/>
                                <div><form:errors path="worker.password" style="color:red"/></div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-md-4 control-label" for="singlebutton"></label>
                            <div class="col-md-4">
                                <input type="submit" id="singlebutton" class="btn btn-primary"
                                       value="${labelComplete}"/>
                            </div>
                        </div>

                    </div>

                </c:otherwise>
            </c:choose>
        </form:form>
    </div>
    </c:otherwise>
    </c:choose>
</body>
</html>
