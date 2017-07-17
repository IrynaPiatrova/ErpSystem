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

        select {
            border-radius: 10px;
        }

        option {
            width: 200px;
        }
    </style>
</head>
<body>
<%@include file="menu.jsp" %>
<%@include file="springMessages.jsp" %>

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

            <tr height="40" hidden>
                <td>${profileEmploymentStatus}</td>
                <td><form:select path="employmentStatus" width="200">
                    <option value="status" disabled>${profileEmploymentStatusChoose}</option>
                    <option value="involved">${profileEmploymentStatus1}</option>
                    <option value="not involved" selected>${profileEmploymentStatus2}</option>
                    <option value="on vacation">${profileEmploymentStatus3}</option>
                    <option value="on a sick leave">${profileEmploymentStatus4}</option>
                    <option value="dismissed">${profileEmploymentStatus5}</option>
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

            <tr height="40" width="100">
                <td><input type="submit" value="${labelNext}"></td>
            </tr>
        </table>
    </form:form>
    <form action="${pageContext.request.contextPath}/" method="get" width="100">
        <input type="submit" value="${labelBack}">
    </form>
</div>
</body>
</html>
