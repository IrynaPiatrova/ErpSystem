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
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
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
                            <form:input class="form-control input-md" type="text" autocomplete="off" path="worker.nameWorker"
                                        id="textinput" name="textinput"/>
                            <div><form:errors path="worker.nameWorker" style="color:red"/> </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <table class="form-group">
            <tr>
                <td align="center"><strong> После изменения </strong></td>
                <td align="center"><strong> На данный момент </strong></td>
            </tr>
            <tr>
                <td>
                    <div>
                        <label class="col-md-4 control-label" for="chooseDepartment">${profileDepartment}</label>
                        <div class="col-md-4">
                            <form:select path="department" width="200" id="chooseDepartment">
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
                    <jsp:text> ${profile.department} </jsp:text>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="choosePosition">${profilePosition}</label>
                        <div class="col-md-4">
                            <form:select path="position" width="200" id="choosePosition">
                                <option value="position" disabled selected>${profilePositionChoose}</option>
                                <option value="junior developer">${profilePositionJuniordeveloper}</option>
                                <option value="middle developer">${profilePositionMiddleDeveloper}</option>
                                <option value="senior developer">${profilePositionSeniorDeveloper}</option>
                                <option value="QAEngineer">${profilePositionQAEngineer}</option>
                                <option value="designer">${profilePositionDesigner}</option>
                            </form:select>
                            <div><form:errors path="position" style="color:red"/></div>
                        </div>
                    </div>
                </td>
                <td>
                    <jsp:text> ${profile.position} </jsp:text>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="form-group">
                        <label class="col-md-4 control-label" for="chooseEmplStatus">${profileEmploymentStatus}</label>
                        <div class="col-md-4">
                            <form:select path="employmentStatus" width="200" id="chooseEmplStatus">
                                <option value="status" disabled selected>${profileEmploymentStatusChoose}</option>
                                <option value="status 1">${profileEmploymentStatus1}</option>
                                <option value="status 2">${profileEmploymentStatus2}</option>
                                <option value="status 3">${profileEmploymentStatus3}</option>
                                <option value="status 4">${profileEmploymentStatus4}</option>
                            </form:select>
                            <div><form:errors path="employmentStatus" style="color:red"/></div>
                        </div>
                    </div>
                </td>
                <td>
                    <jsp:text> ${profile.employmentStatus} </jsp:text>
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


                <%--вставить еще логин и пароль И ВАЛИДАЦИЮ ДЛЯ НИХ--%>

            <div class="form-group">
                <label class="col-md-4 control-label" for="inputLogin">${workerLoginNew}</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="worker.login"
                                id="inputLogin" name="textinput"/>
                </div>
                <div><form:errors path="worker.login" style="color:red"/></div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label" for="inputPassword">${workerPasswordNew}</label>
                <div class="col-md-4">
                    <form:input class="form-control input-md" type="text" autocomplete="off" path="worker.password"
                                id="inputPassword" name="textinput"/>
                </div>
                <div><form:errors path="worker.password" style="color:red"/></div>
            </div>
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
        </c:otherwise>
        </c:choose>
</div>
</body>
