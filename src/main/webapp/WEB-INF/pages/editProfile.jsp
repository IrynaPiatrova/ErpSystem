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
</head>
<body>
<%@include file="menu.jsp" %>
<%@include file="springMessages.jsp" %>

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
                    <jsp:text> ${profileData.department} </jsp:text>
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
