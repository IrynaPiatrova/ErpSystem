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
    <title>Добавление нового сотрудника</title>
</head>
<body>
<form:form id="form" action="isSuccessAddNewWorker" method="post" modelAttribute="profile">
    <input type="text" name="nameWorker" placeholder="Введите имя">
    <div><form:errors path="nameWorker" style="color:red"/></div>
    <br>
    <input type="text" name="login" placeholder="Введите логин">
    <div><form:errors path="login" style="color:red"/></div>
    <br>
    <input type="password" name="password" placeholder="Введите пароль">
    <div><form:errors path="password" style="color:red"/></div>
    <br>
    <input type="text" name="position" placeholder="Введите должность">
    <div><form:errors path="position" style="color:red"/></div>
    <br>
    <input type="text" name="department" placeholder="Введите отдел">
    <div><form:errors path="department" style="color:red"/></div>
    <br>
    <input type="text" name="employmentStatus" placeholder="Введите статус"><!--Нужно сделать compobox-->
    <div><form:errors path="employmentStatus" style="color:red"/></div>
    <br>
    <input type="text" name="telephone" placeholder="Введите телефон">
    <div><form:errors path="telephone" style="color:red"/></div>
    <br>
    <input type="text" name="email" placeholder="Введите email">
    <div><form:errors path="email" style="color:red"/></div>
    <br>
    <input type="date" name="startDate">
    <div><form:errors path="startDate" style="color:red"/></div>
    <br>
    <input type="file" name="photo">
    <br>
    <input type="submit" value="Подтвердить">
    <br>
</form:form>
</body>
</html>
