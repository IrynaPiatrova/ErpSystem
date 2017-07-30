<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 14.07.2017
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/taglib/tags.tld" %>
<html>
<head>
    <%@include file="../resources/springMessages.jsp" %>
    <meta charset="UTF-8">
    <title>${titleChat}</title>
    <%@include file="../resources/head.jsp" %>
    <%@include file="../resources/bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
    <style>
        <%@include file='../css/chat.css' %>
    </style>
    <script src="https://use.fontawesome.com/45e03a14ce.js"></script>
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            ajaxRequest();
            setInterval("updatePage()", 5000);
        });
        function updatePage() {
            $("#lists").empty();
            ajaxRequest();
        };
        function ajaxRequest() {
            $.ajax({
                type: "get",
                url: "/messages",
                async: true,
                dataType: 'json',
                success: function (response) {
                    $.each(response, function (index, chatDTO) {
                        var positionPhoto = '<span class="chat-img1 pull-left">';
                        var positionName = '<div class="chat_time pull-left" id="time">';
                        var comment = chatDTO.comment;
                        var nameWorker = chatDTO.nameWorker;
                        var photo = chatDTO.photo;
                        var isCurentUser = chatDTO.curentUser;
                        if (isCurentUser) {
                            positionPhoto = '<span class="chat-img1 pull-right">';
                            positionName = '<div class="chat_time pull-right" id="time">';
                        }
                        $(
                                '<li class="left clearfix">' +
                                positionPhoto +
                                ' <img src="' + "data:image/png;base64," + photo + '" class="img-circle" id="photo">' +
                                '</span>' +

                                '<div class="chat-body1 clearfix">' +
                                '<p>' + comment + '</p>' + positionName + nameWorker + '</div>' +
                                ' </div></li><li class="left clearfix">').appendTo('#lists');
                    });
                },
                error: function (XHR, status) {
                    alert("Ошибка: " + status);
                }
            });
        }
    </script>
</head>

<body>
<div class="main_section">
    <div class="container">
        <div class="chat_container">
            <div class="col-sm-9 message_section">
                <div class="row">
                    <div class="chat_area">
                        <ul class="list-unstyled" id="lists"></ul>
                    </div>
                    <div class="message_write">
                        <form:form action="addMessage" method="GET">
                            <textarea name="textMessage" class="form-control" placeholder="${inputMessage}"></textarea>
                            <div class="clearfix"></div>
                            <div class="chat_time pull-right">
                                <input type="submit" value="${submit}" class="btn btn-default">
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
