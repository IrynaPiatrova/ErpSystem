<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 07.07.2017
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
               <img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" class="img-responsive img-circle"
                    title="John Doe" alt="John Doe" width="30px" height="30px">
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
                            <%--<li><a href="/findWorker">Поиск пользователя</a></li>--%>
                            <li><a href="#">Contact Support</a></li>
                            <li><a href="/addNewWorker">Add new worker</a></li>
                            <li><a href="/allWorkers">All workers</a> </li>
                        </ul>
                    </li>
                    <li/>
                </c:if>
                <li/>
                <li><a href="/profile">Профиль</a></li>
                <li><a href="/addNewTicket">Тикеты</a></li>
                <li/>
                <li><a href="#">Чат</a></li>
            </ul>
        </div>
    </div>
</nav>
