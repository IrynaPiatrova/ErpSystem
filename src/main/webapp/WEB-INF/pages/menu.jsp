<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 07.07.2017
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/taglib/tags.tld" %>

<nav class="navbar navbar-inverse">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#navbar-collapse-3">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-brand">
                <a href="logOut">Выйти</a>
            </span>
            <span class="user-avatar pull-left" style="margin-right:20px; margin-top:8px;">
                <c:choose>
                    <c:when test="${photo == null}">
                        <img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" class="img-responsive img-circle"
                             width="30px" height="30px">
                    </c:when>
                    <c:otherwise>
                        <img src="<mytag:convertImage imageByte="${photo}"/>" class="img-responsive img-circle"
                             width="30px" height="30px">
                    </c:otherwise>
                </c:choose>
            </span>
            <span class="navbar-brand">${nameUser}</span>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-collapse-3">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="main">Главная</a></li>
                <li/>
                <c:if test="${isAdmin}">
                    <li class="dropdown"><a href="/allWorkers" class="dropdown-toggle" data-toggle="dropdown">Пользователи<b
                            class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/addNewWorker">Add new worker</a></li>
                            <li><a href="/allWorkers">All workers</a></li>
                        </ul>
                    </li>
                    <li/>
                </c:if>
                <li/>
                <li><a href="/profile">Профиль</a></li>
                <c:choose>
                    <c:when test="${isAdmin}">
                        <li class="dropdown"><a href="/allTickets" class="dropdown-toggle" data-toggle="dropdown">Тикеты<b
                                class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="/addNewTicket">Новый тикет</a></li>
                                <li><a href="/allTickets">Список тикетов</a></li>
                            </ul>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/allTickets">Мои Тикеты</a></li>
                    </c:otherwise>
                </c:choose>
                <li/>
                <li><a href="/chat">Чат</a></li>
                <li><a href="?lang=en"><img src="http://www.world-globe.ru/files/flags/akrotiri_l.png"
                                            class="img-responsive img-circle"
                                            width="30px" height="30px"></a></li>
                <li/>
                <li><a href="?lang=ru"><img
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAACFCAMAAAApQEceAAAAD1BMVEX////VKx4AOaZUesNGNHkRZge8AAAAhUlEQVR4nO3PQQ3AMAwAsXQbf8wlsccpshl4BgAAAAAAAAAAfvIuMc8SIjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI1IjUiNSI18y0xZwmRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakRqRGpEakZo1kQtIGmsJ+yo/MAAAAABJRU5ErkJggg=="
                        class="img-responsive img-circle"
                        width="30px" height="30px"></a></li>
                <li/>
            </ul>
        </div>
    </div>
</nav>
