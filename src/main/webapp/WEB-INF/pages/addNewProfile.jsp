<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 02.07.2017
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <title>Добавление нового сотрудника</title>
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
        body {
            background: #FAEBD7;
        }
        input {
            width: 300px;
            border-radius: 10px;
        }
        select{
            border-radius: 10px;
        }
        option {
            width: 200px;
        }
    </style>
</head>
<body>
<%@include file="menu.jsp" %>
<%--<span style="float: right" margin-right="10px" margin-top="10px">--%>
             <%--<a href="/isSuccessAddNewProfile?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png" width="8%" height="8%"></a>--%>
            <%--<a href="/isSuccessAddNewProfile?lang=ru"><img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg==" width="8%" height="8%"></a>--%>
    <%--</span>--%>
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
<spring:message code="profile.telephone" var="profileTelephone"/>
<spring:message code="profile.email" var="profileEmail"/>
<spring:message code="profile.startDate" var="profileStartDate"/>
<spring:message code="profile.photo" var="profilePhoto"/>
<spring:message code="label.next" var="labelNext"/>
<spring:message code="label.back" var="labelBack"/>
<div id="centerLayer">
<form:form action="isSuccessAddNewProfile" method="post" modelAttribute="profile">
    <table>
        <tr height="40">
            <td>${profileDepartment}</td>
            <td><form:select path="department" width="200">
                <option value="department" disabled selected>${profileDepartmentChoose}</option>
                <option value="development">${profileDepartmentDevelopers}</option>
                <option value="testing">${profileDepartmentTesters}</option>
                <option value="design">${profileDepartmentDesigners}</option>
            </form:select>
                <div><form:errors path="department" style="color:red"/></div>
            </td>
        </tr>

        <tr height="40">
            <td>${profilePosition}</td>
            <td><form:select path="position" width="200">
                <option value="position" disabled selected>${profilePositionChoose}</option>
                <option value="junior developer">${profilePositionJuniordeveloper}</option>
                <option value="middle developer">${profilePositionMiddleDeveloper}</option>
                <option value="senior developer">${profilePositionSeniorDeveloper}</option>
                <option value="QAEngineer">${profilePositionQAEngineer}</option>
                <option value="designer">${profilePositionDesigner}</option>
            </form:select>
                <div><form:errors path="position" style="color:red"/></div>
            </td>
        </tr>

        <tr height="40">
            <td>${profileEmploymentStatus}</td>
            <td><form:select path="employmentStatus"  width="200">
                <option value="status" disabled selected>${profileEmploymentStatusChoose}</option>
                <option value="status 1">${profileEmploymentStatus1}</option>
                <option value="status 2">${profileEmploymentStatus2}</option>
                <option value="status 3">${profileEmploymentStatus3}</option>
                <option value="status 4">${profileEmploymentStatus4}</option>
            </form:select>
                <div><form:errors path="employmentStatus" style="color:red"/></div>
            </td>
        </tr>

        <tr height="40">
            <td>${profileTelephone}</td>
            <td><form:input type="text" name="telephone" path="telephone"/>
                <div><form:errors path="telephone" style="color:red"/></div>
            </td>
        </tr>

        <tr height="40">
            <td>${profileEmail}</td>
            <td><form:input type="text" name="email" path="email"/>
                <div><form:errors path="email" style="color:red"/></div>
            </td>
        </tr>

        <tr height="40">
            <td>${profileStartDate}</td>
            <td><input type="date" name="Date" path="startDateProfile" height="30"/>
                <div><form:errors path="startDateProfile" style="color:red"/></div>
            </td>
        </tr>

        <tr>
            <td><p>Выберите ключевой вопрос</p>
                <p>для восстановления пароля</p></td>
            <td><form:select name="keyWord" path="keyWord" width="200">
                <option value="status" disabled selected>${profileEmploymentStatusChoose}</option>
                <option value="status 1">Девичья фамилия матери</option>
                <option value="status 2">Город где вы родились</option>
                <option value="status 3">Любимое блюдо</option>
            </form:select></td>
        </tr>

        <tr>
            <td><p>Введите ответ на вопрос</p></td>
            <td><form:input type="text" name="answerOnKeyWord" path="answerOnKeyWord"/></td>
        </tr>
       <%-- <tr height="40">
            <td>${profilePhoto}</td>
            <td><form:input type="file" name="photo" path="photo"/>
            </td>
        </tr>--%>

        <tr height="40"  width="100">
            <td><input type="submit" value="${labelNext}"></td>
        </tr>
    </table>
</form:form>
<form action="/" method="get" width="100">
    <input type="submit" value="${labelBack}">
</form>
</div>
</body>
</html>
