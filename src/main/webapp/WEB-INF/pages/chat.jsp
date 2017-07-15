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
<html>
<head>
    <title>Title</title>
    <%@include file="bootstrapLinks.jsp" %>
    <style>
        <%@include file='../css/chat.css' %>
    </style>
    <script src="https://use.fontawesome.com/45e03a14ce.js"></script>
</head>
<body>
<%@include file="menu.jsp" %>
<%@include file="springMessages.jsp"%>

<div class="main_section">
    <div class="container">
        <div class="chat_container">
            <div class="col-sm-3 chat_sidebar">
                <div class="row">
                    <%--поиск--%>
                    <%--<div id="custom-search-input">
                        <div class="input-group col-md-12">
                            <input type="text" class="  search-query form-control" placeholder="Conversation"/>
                            <button class="btn btn-danger" type="button">
                                <span class=" glyphicon glyphicon-search"></span>
                            </button>
                        </div>
                    </div>--%>
                    <div class="member_list">
                        <ul class="list-unstyled">
                            <li class="left clearfix">
                                <c:forEach items="${collectionMessages}" var="listMessages">
                                 <span class="chat-img pull-left">
                                 <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                                      alt="User Avatar" class="img-circle">
                                 </span>
                                    <div class="chat-body clearfix">
                                        <div class="header_sec">
                                            <strong class="primary-font">Jack Sparrow</strong> <strong
                                                class="pull-right">
                                            09:45AM</strong>
                                        </div>
                                        <div class="contact_sec">
                                            <span class="badge pull-right">3</span>
                                        </div>
                                    </div>
                                </c:forEach>
                            </li>
                        </ul>
                    </div>
                </div>
            </div><!--chat_sidebar-->

            <div class="col-sm-9 message_section">
                <div class="row">
                    <div class="chat_area">
                        <ul class="list-unstyled">
                            <li class="left clearfix">
                     <span class="chat-img1 pull-left">
                     <%--<span class="chat-img1 pull-right">--%>
                     <img src="https://lh6.googleusercontent.com/-y-MY2satK-E/AAAAAAAAAAI/AAAAAAAAAJU/ER_hFddBheQ/photo.jpg"
                          alt="User Avatar" class="img-circle">
                     </span>
                                <div class="chat-body1 clearfix">
                                    <p>Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots
                                        in a piece of classical Latin literature from 45 BC, making it over 2000 years
                                        old. Richard McClintock, a Latin professor at Hampden-Sydney College in
                                        Virginia.</p>
                                    <div class="chat_time pull-right">09:40PM</div>
                                    <%--<div class="chat_time pull-left">09:40PM</div>--%>
                                </div>
                            </li>
                            <li class="left clearfix">
                        </ul>
                    </div><!--chat_area-->

                    <div class="message_write">
                        <textarea class="form-control" placeholder="type a message"></textarea>
                        <div class="clearfix"></div>
                        <div class="chat_bottom">
                            <a href="#" class="pull-right btn btn-success">Send</a>
                        </div>
                    </div>
                </div>
            </div> <!--message_section-->
        </div>
    </div>
</div>
</body>
</html>
