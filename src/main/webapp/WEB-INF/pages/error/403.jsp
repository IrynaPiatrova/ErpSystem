<%--
  Created by IntelliJ IDEA.
  User: klinster
  Date: 24.07.2017
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>403 Error page</title>
    <%@include file="/WEB-INF/pages/springMessages.jsp" %>
    <style>
        <%@include file="/WEB-INF/css/style_for_errors.css"%>
    </style>
</head>

<body class="bodyException">

<div class="error-div">
    <font size="8" style="font-family: sans-serif"> 403 Error</font> <br/><br/><br/>
    <font size="8" style="font-family: sans-serif"> Forbidden...</font> <br/><br/><br/>
    <button style="margin-left: 30%; width: 80px" type="button" name="back" class="buttonAuth"
            onclick="history.back()">${labelBack}</button>

</div>

</body>
</html>
