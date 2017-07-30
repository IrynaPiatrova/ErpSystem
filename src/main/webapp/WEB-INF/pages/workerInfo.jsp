<%--
  Created by IntelliJ IDEA.
  User: klinster
  Date: 15.07.2017
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@include file="../resources/springMessages.jsp" %>
    <title>${titleWorkerPerfomance}</title>
    <meta charset="UTF-8">
    <%@include file="../resources/head.jsp" %>
    <%@include file="../resources/bootstrapLinks.jsp" %>
    <%@include file="menu.jsp" %>
    <style>
        <%@include file='../css/table.css' %>
    </style>
    <c:set var="numberOfUnsuccesTickets" value="0" scope="page"/>
    <c:set var="numberOfSuccessTickets" value="0" scope="page"/>
    <c:set var="numberOfNotFinishedTickets" value="0" scope="page"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>

<body>
<h3 style="margin-left: 300px">${labelShowWorker} ${tempWorker}</h3>
<div id="centerLayer">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">${labelTicketsChoose}</h3>
                    </div>
                    <table class="table table-hover" id="dev-table">
                        <thead>
                        <tr>
                            <th>${nameTicket}</th>
                            <th>${specificationTicket}</th>
                            <th>${statusTicketDescription}</th>
                            <th>${workerTicketPerfomance}</th>
                        </tr>
                        <c:forEach items="${collectionTickets}" var="listTickets">
                        <tr class="clickable"
                            onclick="location.href='/chooseTicket${listTickets.idProjectTicket}'">
                            <td hidden>${listTickets.idProjectTicket}</td>
                            <td>${listTickets.nameProjectTicket}</td>
                            <td>${listTickets.specification}</td>
                            <td>${listTickets.statusProjectTicket}</td>
                            <c:choose>
                                <c:when test="${listTickets.perfomance == 'Successfully'}">
                                    <td bgcolor="#32cd32">${listTickets.perfomance}</td>
                                    <c:set var="numberOfSuccessTickets" value="${numberOfSuccessTickets+1}"
                                           scope="page"/>
                                </c:when>
                                <c:when test="${listTickets.perfomance == 'Unsuccessfully'}">
                                    <td bgcolor="#dc143c">${listTickets.perfomance}</td>
                                    <c:set var="numberOfUnsuccesTickets" value="${numberOfUnsuccesTickets+1}"
                                           scope="page"/>
                                </c:when>
                                <c:otherwise>
                                    <td>${listTickets.perfomance}</td>
                                    <c:set var="numberOfNotFinishedTickets" value="${numberOfNotFinishedTickets+1}"
                                           scope="page"/>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="piechart_3d" style="width: 900px; height: 500px; margin-left: 300px">
</div>
<script type="text/javascript">
    google.charts.load("current", {packages: ["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Ticket', 'Perfomance'],
            ['Succesfully', ${numberOfSuccessTickets}],
            ['Unsuccesfully', ${numberOfUnsuccesTickets}],
            ['Not finished', ${numberOfNotFinishedTickets}],
        ]);

        var options = {
            is3D: true,
            slices: {
                0: {color: '#32cd32'},
                1: {color: '#dc143c'},
                2: {color: '#d3d3d3'}
            }
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
    }
</script>
</body>
</html>
