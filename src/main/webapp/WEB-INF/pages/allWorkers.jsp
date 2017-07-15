<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 10.07.2017
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>AllWorkers</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <style>
        <%@include file='../css/table.css' %>
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <%@include file="table.jsp" %>
</head>
<body>
<%@include file="menu.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Работники</h3>
                    <div class="pull-right">
							<span class="clickable filter" data-toggle="tooltip" title="Toggle table filter"
                                  data-container="body">
								<i class="glyphicon glyphicon-filter"></i>
							</span>
                    </div>
                </div>
                <div class="panel-body">
                    <input type="text" class="form-control" id="dev-table-filter" data-action="filter"
                           data-filters="#dev-table" placeholder="Поиск работников..."/>
                </div>
                <table class="table table-hover" id="dev-table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Имя</th>
                        <th>Логин</th>
                        <th>Подразделение</th>
                        <th>Должность</th>
                        <th>Дата принятия на работу</th>
                        <th>Статус</th>
                        <th>Телефон</th>
                        <th>Почта</th>
                    </tr>
                    </thead>
                    <tbody id="tBody">
                    <c:set var="it" value="1"/>
                    <c:forEach var="list" items="${allWorkers}" begin="1" step="1" varStatus="status">
                        <tr class="rowLink">
                            <td>${status.index}</td>
                            <td>${list.nameWorker} </td>
                            <td class="choosedLogin">${list.login}</td>
                            <td>${list.profile.department}</td>
                            <td>${list.profile.position}</td>
                            <td>${list.profile.startDateProfile}</td>
                            <td>${list.profile.employmentStatus}</td>
                            <td>${list.profile.telephone}</td>
                            <td><a href="mailto:${list.profile.email}?subject=Вопрос">${list.profile.email}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <form action="/findWorkerByLogin" method="post" id="formButton">
                    <p/>
                    <input type="hidden" name="login" id="login" value="">
                    <input type="hidden" id="editButton" value="Редактировать профиль" class="btn btn-default">
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript"> // этот скрипт должен быть после формы чтобы видеть элемент с id=login$(document).ready(function(){

$(document).on('click', '.rowLink', function () {
    var choosedLogin = $(this).find('td.choosedLogin').html(); // получаем значение со строки "td"
    document.getElementById("login").value = choosedLogin;
    document.getElementById("editButton").type = "submit";
    document.getElementById("editButton").value = choosedLogin;
    //нужно придумать как сделать это с надписью  формата:
    // "Редактировать профиль" + id выбранного пользвателя
});
</script>
</body>
</html>
