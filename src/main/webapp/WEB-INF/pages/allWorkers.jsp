<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 10.07.2017
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <%@include file="../resources/springMessages.jsp" %>
    <title>${titleAllWorkers}</title>
    <meta charset="UTF-8">
    <%@include file="../resources/head.jsp" %>
    <%@include file="../resources/bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <%@include file="../resources/scriptTableWorkers.jsp" %>
    <style>
        <%@include file='../css/table.css' %>
    </style>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">${labelWorkersChoose}</h3>
                    <div class="pull-right">
							<span class="clickable filter" data-toggle="tooltip" title="${labelToggleFilter}"
                                  data-container="body">
								<i class="glyphicon glyphicon-filter"></i>
							</span>
                    </div>
                </div>
                <div class="panel-body">
                    <input type="text" class="form-control" id="dev-table-filter" data-action="filter"
                           data-filters="#dev-table" placeholder="${labelSearchInput}"/>
                </div>
                <table class="table table-hover" id="dev-table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>${workerNameDescription}</th>
                        <th>${workerLoginDescription}</th>
                        <th>${profileDepartmentDescription}</th>
                        <th>${profilePositionDescription}</th>
                        <th>${profileStartDateDescription}</th>
                        <th>${profileEmploymentStatusDescription}</th>
                        <th>${profileTelephoneDescription}</th>
                        <th>${profileEmailDescription}</th>
                    </tr>
                    </thead>
                    <tbody id="tBody">
                    <c:set var="it" value="1"/>
                    <c:forEach var="list" items="${allWorkers}" begin="1" step="1" varStatus="status">
                        <tr class="rowLink">
                            <td class="choosedNumber">${status.index}</td>
                            <td class="choosedId" hidden>${list.idWorker}</td>
                            <td>${list.nameWorker} </td>
                            <td class="choosedLogin">${list.login}</td>
                            <td>${list.profile.department}</td>
                            <td>${list.profile.position}</td>
                            <td>${list.profile.startDateProfile}</td>
                            <td>${list.profile.employmentStatus}</td>
                            <td>${list.profile.telephone}</td>
                            <td><a href="mailto:${list.profile.email}?subject=Вопрос">${list.profile.email}</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <table>
                    <tr>
                        <td>
                            <form action="${pageContext.request.contextPath}/findByIdAndEditWorker" method="post"
                                  id="formButton">
                                <p/>
                                <input type="hidden" name="idWorker" id="idWorker" value="">
                                <input type="hidden" id="editButton" value="Редактировать профиль"
                                       class="btn btn-default">
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/findByIdAndShowInfo" method="post">
                                <p/>
                                <input type="hidden" name="idWorker" id="idWorkerToShow" value="">
                                <input type="hidden" id="editButtonToShow" value="Дополнительная информация"
                                       class="btn btn-default">
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
