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
    <meta charset="UTF-8">
    <title>Title</title>
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
    <%@include file="springMessages.jsp" %>
    <style>
        <%@include file='../css/chat.css' %>
    </style>
    <script src="https://use.fontawesome.com/45e03a14ce.js"></script>
    <script language="javascript" type="text/javascript">
        $(document).ready(function () {
            ajaxRequest();
            /* setInterval("ajaxRequest()",2000);*/
        });
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
                        <ul class="list-unstyled" id="lists">
                           <%-- <li class="left clearfix">
                                &lt;%&ndash;<c:forEach items="${selectCategory}" var="message" >&ndash;%&gt;
                                <span class="chat-img1 pull-left">
                      &lt;%&ndash;<span class="chat-img1 pull-right">&ndash;%&gt;
                      <img src="<mytag:convertImage imageByte="${listComment.photo}"/>" class="img-circle" id="photo">
                      </span>
                                <div class="chat-body1 clearfix">
                                    <p>Contrary to popular belief, Lorem Ipsum is not simply random text.
                                        It has roots
                                        in a piece of classical Latin literature from 45 BC, making it over 2000
                                        years
                                        old. Richard McClintock, a Latin professor at Hampden-Sydney College in
                                        Virginia.</p>
                                    <div class="chat_time pull-right" id="time">09:40PM</div>
                                    &lt;%&ndash;<div class="chat_time pull-left">09:40PM</div>&ndash;%&gt;
                                </div>
                                &lt;%&ndash;</c:forEach>&ndash;%&gt;
                            </li>
                            <li class="left clearfix">--%>
                        </ul>
                    </div><!--chat_area-->

                    <div class="message_write">
                        <textarea class="form-control" placeholder="type a message"></textarea>
                        <div class="clearfix"></div>
                        <div class="chat_bottom">
                            <a href="#" class="btn btn-default">Send</a>
                        </div>
                    </div>
                </div>
            </div> <!--message_section-->
        </div>
    </div>
</div>
</body>
</html>
