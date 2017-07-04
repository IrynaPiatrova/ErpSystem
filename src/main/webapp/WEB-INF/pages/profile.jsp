<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 04.07.2017
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>${profile.department}</p>
    <p>${profile.email}</p>
    <p>${profile.idProfile}</p>
    <p>${profile.position}</p>
    <p>${profile.startDateProfile}</p>
    <p>${profile.telephone}</p>
    <p><img src="${photo}" class="img-responsive img-circle"
            title="John Doe" alt="John Doe" width="30px" height="30px"></p>
</body>
</html>
