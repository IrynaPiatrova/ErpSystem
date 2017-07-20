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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Профиль</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
    <%@include file="springMessages.jsp" %>
    <%@include file="ScriptListOfPositions.jsp" %>
</head>
<body>
<%@include file="menu.jsp" %>

<spring:message code="profile.position" var="profilePosition"/>
<spring:message code="profile.position.choose" var="profilePositionChoose"/>
<spring:message code="profile.position.juniordeveloper" var="profilePositionJuniordeveloper"/>
<spring:message code="profile.position.middleDeveloper" var="profilePositionMiddleDeveloper"/>
<spring:message code="profile.position.seniorDeveloper" var="profilePositionSeniorDeveloper"/>
<spring:message code="profile.position.QAEngineer" var="profilePositionQAEngineer"/>
<spring:message code="profile.position.designer" var="profilePositionDesigner"/>
<spring:message code="profile.department" var="profileDepartment"/>
<spring:message code="profile.department.choose" var="profileDepartmentChoose"/>
<spring:message code="profile.department.developers" var="profileDepartmentDevelopers"/>
<spring:message code="profile.department.testers" var="profileDepartmentTesters"/>
<spring:message code="profile.department.designers" var="profileDepartmentDesigners"/>
<spring:message code="profile.employmentStatus" var="profileEmploymentStatus"/>
<spring:message code="profile.employmentStatus.choose" var="profileEmploymentStatusChoose"/>
<spring:message code="profile.employmentStatus.1" var="profileEmploymentStatus1"/>
<spring:message code="profile.employmentStatus.2" var="profileEmploymentStatus2"/>
<spring:message code="profile.employmentStatus.3" var="profileEmploymentStatus3"/>
<spring:message code="profile.employmentStatus.4" var="profileEmploymentStatus4"/>
<spring:message code="profile.telephone.new" var="profileTelephoneNew"/>
<spring:message code="profile.email.new" var="profileEmailNew"/>
<spring:message code="profile.photo.new" var="profilePhotoNew"/>
<spring:message code="profile.edit" var="profileEdition"/>
<spring:message code="label.complete" var="labelComplete"/>
<spring:message code="worker.name.new" var="workerNameNew"/>
<spring:message code="worker.login.new" var="workerLoginNew"/>
<spring:message code="worker.password.new" var="workerPasswordNew"/>


<div class="container">
    <c:choose>
    <c:when test="${adminEditProfile}">
    <form:form class="form-horizontal" action="editAdmin" method="post" modelAttribute="profile"
               enctype="multipart/form-data">
    <fieldset>

        <legend>${profileEdition} : ${profileData.worker.nameWorker} </legend>

        <table>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="textinput">${workerNameNew}</label>
                        <div class="col-md-4">
                            <form:input class="form-control input-md" type="text" autocomplete="off"
                                        path="worker.nameWorker"
                                        id="textinput" name="textinput"/>
                            <div><form:errors path="worker.nameWorker" style="color:red"/></div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <table class="form-group">
            <tr>
                <td align="center"><strong>${profileEditionAfter}</strong></td>
                <td align="center"><strong>${profileEditionBefore}</strong></td>
            </tr>
            <tr>
                <td>
                    <div>
                        <label class="col-md-4 control-label" for="level">${profileDepartment}</label>
                        <div class="col-md-4">
                            <form:select path="department" width="200" id="level" onchange="showPositions(this.value)">
                                <option value="department" disabled selected>${profileDepartmentChoose}</option>
                                <option value="development">${profileDepartmentDevelopers}</option>
                                <option value="testing">${profileDepartmentTesters}</option>
                                <option value="design">${profileDepartmentDesigners}</option>
                            </form:select>
                            <div><form:errors path="department" style="color:red"/></div>
                        </div>
                    </div>
                </td>
                <td>
                    <jsp:text> ${profileData.department} </jsp:text>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="positions">${profilePosition}</label>
                        <div class="col-md-4">
                            <form:select path="position" width="200" id="positions">
                                <option value="position" disabled selected>${profilePositionChoose}</option>

                            </form:select>
                            <div><form:errors path="position" style="color:red"/></div>
                        </div>
                    </div>
                </td>
                <td>
                    <jsp:text> ${profileData.position} </jsp:text>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="chooseEmplStatus">${profileEmploymentStatus}</label>
                        <div class="col-md-4">
                            <form:select path="employmentStatus" width="200" id="chooseEmplStatus">
                                <option value="status" disabled selected>${profileEmploymentStatusChoose}</option>
                                <option value="involved">${profileEmploymentStatus1}</option>
                                <option value="not involved">${profileEmploymentStatus2}</option>
                                <option value="on vacation">${profileEmploymentStatus3}</option>
                                <option value="on a sick leave">${profileEmploymentStatus4}</option>
                                <option value="dismissed">${profileEmploymentStatus5}</option>
                            </form:select>
                            <div><form:errors path="employmentStatus" style="color:red"/></div>
                        </div>
                    </div>
                </td>
                <td>
                    <jsp:text> ${profileData.employmentStatus} </jsp:text>
                </td>
            </tr>
        </table>
        <div class="form-group">
            <label class="col-md-4 control-label" for="singlebutton"></label>
            <div class="col-md-4">
                <input type="submit" id="singlebutton" class="btn btn-primary" value="${labelComplete}"/>
            </div>
        </div>
        </form:form>
        </c:when>

        <c:otherwise>
        <form:form class="form-horizontal" action="edit" method="post" modelAttribute="profile"
                   enctype="multipart/form-data">
        <fieldset>

            <legend>${profileEdition}</legend>

            <div class="form-group">
                <label class="col-md-4 control-label" for="inputLogin">${workerLoginNew}</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="worker.login"
                                id="inputLogin" name="textinput"/>
                </div>
                <div><form:errors path="worker.login" style="color:red"/></div>
            </div>
            <%--<div class="form-group">--%>
                <%--<label class="col-md-4 control-label" for="inputPassword">${workerPasswordNew}</label>--%>
                <%--<div class="col-md-4">--%>
                    <%--<form:input class="form-control input-md" type="text" autocomplete="off" path="worker.password"--%>
                                <%--id="inputPassword" name="textinput"/>--%>
                <%--</div>--%>
                <%--<div><form:errors path="worker.password" style="color:red"/></div>--%>
            <%--</div>--%>
            <div class="form-group">
                <label class="col-md-4 control-label" for="inputTelephone">${profileTelephoneNew}</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="telephone"
                                id="inputTelephone" name="textinput"/>
                </div>
                <div><form:errors path="telephone" style="color:red"/></div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="inputEmail">${profileEmailNew}</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="email"
                                id="inputEmail"
                                name="textinput"/>
                </div>
                <div><form:errors path="email" style="color:red"/></div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="inputEmail"><p>Выберите ключевой вопрос</p>
                    <p>для восстановления пароля</p></label>
                <div class="col-md-4">
                    <form:select class="form-control input-md" id="inputKeyWord" name="textinput" path="keyWord" width="200">
                        <option value="status" disabled selected>${keyAnswer}</option>
                        <option value="Mother's girls surname">Девичья фамилия матери</option>
                        <option value="What's city where you born">Город где вы родились</option>
                        <option value="Favourite eat">Любимое блюдо</option>
                    </form:select>
                </div>
                <div><form:errors path="keyWord" style="color:red"/></div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="inputEmail">Введите ответ на вопрос</label>
                <div class="col-md-4">
                    <input class="form-control input-md" type="text" autocomplete="off"
                                id="inputAnswerOnKeyWord"
                                name="answerOnKeyWord"/>
                </div>
                <div><form:errors path="answerOnKeyWord" style="color:red"/></div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="inputPhoto">${profilePhotoNew}</label>
                <div class="col-md-4">
                    <form:input id="inputPhoto" name="photo" class="input-file" type="file" path="photo"
                                accept="image/*"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="singlebutton"></label>
                <div class="col-md-4">
                    <input type="submit" id="singlebutton" class="btn btn-primary" value="${labelComplete}"/>
                </div>
            </div>

        </fieldset>
        </form:form>
        <div class="form-group">
            <form action="changePassword">
                <input type="submit" value="change password">
            </form>
        </div>
        </c:otherwise>
        </c:choose>
</div>
</body>
