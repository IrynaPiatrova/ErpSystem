<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 22.06.2017
  Time: 17:15
  To change this template use File | Settings | File Templates.
--%>
<!-- Second navbar for search -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse-3">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="user-avatar pull-left" style="margin-right:20px; margin-top:8px;">
               <img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" class="img-responsive img-circle" title="John Doe" alt="John Doe" width="30px" height="30px">
            </span>
            <span class="navbar-brand">Администратор</span>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="navbar-collapse-3">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="main">Главная</a></li>
                <li/>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Пользователи<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Support</a></li>
                        <li><a href="#">Contact Support</a></li>
                        <%--<li class="divider"></li>--%>
                        <li><a href="#">Logout</a></li>
                    </ul>
                </li>
                <li/>
                <li><a href="#">Тикеты</a></li>
                <li/>
                <li><a href="#">Чат</a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container -->
</nav><!-- /.navbar -->
