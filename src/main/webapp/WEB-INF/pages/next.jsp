<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 16.06.2017
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Page</title>
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <style>
        <%@include file='../css/style_for_up_body.css' %>
        <%@include file='../css/style_for_welcome_page.css' %>
    </style>
    <%--<script src="js/menu.js"></script>--%>
</head>
<body>
<%@include file="body.jsp" %>
<div class="slider">
    <input type="radio" name="slide_switch" id="id1"/>
    <label for="id1">
        <img src="http://thecodeplayer.com/uploads/media/3yiC6Yq.jpg" width="100"/>
    </label>
    <img src="http://thecodeplayer.com/uploads/media/3yiC6Yq.jpg"/>

    <!--Lets show the second image by default on page load-->
    <input type="radio" name="slide_switch" id="id2" checked="checked"/>
    <label for="id2">
        <img src="http://thecodeplayer.com/uploads/media/40Ly3VB.jpg" width="100"/>
    </label>
    <img src="http://thecodeplayer.com/uploads/media/40Ly3VB.jpg"/>

    <input type="radio" name="slide_switch" id="id3"/>
    <label for="id3">
        <img src="http://thecodeplayer.com/uploads/media/00kih8g.jpg" width="100"/>
    </label>
    <img src="http://thecodeplayer.com/uploads/media/00kih8g.jpg"/>

    <input type="radio" name="slide_switch" id="id4"/>
    <label for="id4">
        <img src="http://thecodeplayer.com/uploads/media/2rT2vdx.jpg" width="100"/>
    </label>
    <img src="http://thecodeplayer.com/uploads/media/2rT2vdx.jpg"/>

    <input type="radio" name="slide_switch" id="id5"/>
    <label for="id5">
        <img src="http://thecodeplayer.com/uploads/media/8k3N3EL.jpg" width="100"/>
    </label>
    <img src="http://thecodeplayer.com/uploads/media/8k3N3EL.jpg"/>
</div>

<!-- We will use PrefixFree - a script that takes care of CSS3 vendor prefixes
You can download it from http://leaverou.github.com/prefixfree/ -->
<script src="http://thecodeplayer.com/uploads/js/prefixfree.js" type="text/javascript"></script>
</body>
</html>
