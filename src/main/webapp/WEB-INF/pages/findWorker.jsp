<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 10.07.2017
  Time: 15:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Search</title>
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <script>
        $(document).ready(function () {
            $("#workers").hide();
            $("#buttonSearch").click(function () {
                $("#workers").empty();
                $("#workers").hide();
                var textSeach = $("#textSeach").val();
                if (textSeach.length == 0) {
                    console.error("Введите id");
                }
                ajaxRequest(textSeach);
                $("#workers").show();
            });
        });
        function ajaxRequest(term) {
            $.ajax({
                type: "GET",
                url: "http://localhost:8080/findWorkerById?id=" + term,
                success: function (data) {
                    $('#films').append($('<tr>' +
                            /*'<td>' +
                            ' <input type="image" src="' + $(this).attr("photo") + '">' +
                            '</td>' +*/
                            '<td>' +
                            '<p><a href="profile/" ' + term +'>' + $(this).attr("nameWorker") + '</a></p>' +
                            '</td>' +
                            '</tr>'));

                }
            });
        }
    </script>
</head>
<body>
<%@include file="menu.jsp" %>
<form:form class="form-inline">
    <div class="search" align="center">
        <input type="text" id="textSeach" class="form-control" placeholder="Введите id">
        <input type="button" id="buttonSearch" class="btn btn-primary" value="Поиск">
    </div>
</form:form>
<div class="tableFilms">
    <table id="workers" class="table table-bordered"></table>
</div>
</body>
</html>
