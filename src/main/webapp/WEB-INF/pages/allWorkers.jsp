<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 10.07.2017
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>AllWorkers</title>
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <style>
        .row{
            margin-top:40px;
            padding: 0 10px;
        }
        .clickable{
            cursor: pointer;
        }

        .panel-heading div {
            margin-top: -18px;
            font-size: 15px;

        }
        .panel-heading div span{
            margin-left:5px;
        }
        .panel-body{
            display: none;
        }
        div.panel.panel-primary {
            border-color: #404040;
        }
        div.panel-heading {
            background-color: #363636;
        }
    </style>
    <script>
        (function(){
            'use strict';
            var $ = jQuery;
            $.fn.extend({
                filterTable: function(){
                    return this.each(function(){
                        $(this).on('keyup', function(e){
                            $('.filterTable_no_results').remove();
                            var $this = $(this), search = $this.val().toLowerCase(), target = $this.attr('data-filters'), $target = $(target), $rows = $target.find('tbody tr');
                            if(search == '') {
                                $rows.show();
                            } else {
                                $rows.each(function(){
                                    var $this = $(this);
                                    $this.text().toLowerCase().indexOf(search) === -1 ? $this.hide() : $this.show();
                                })
                                if($target.find('tbody tr:visible').size() === 0) {
                                    var col_count = $target.find('tr').first().find('td').size();
                                    var no_results = $('<tr class="filterTable_no_results"><td colspan="'+col_count+'">No results found</td></tr>')
                                    $target.find('tbody').append(no_results);
                                }
                            }
                        });
                    });
                }
            });
            $('[data-action="filter"]').filterTable();
        })(jQuery);

        $(function(){
            // attach table filter plugin to inputs
            $('[data-action="filter"]').filterTable();

            $('.container').on('click', '.panel-heading span.filter', function(e){
                var $this = $(this),
                    $panel = $this.parents('.panel');

                $panel.find('.panel-body').slideToggle();
                if($this.css('display') != 'none') {
                    $panel.find('.panel-body input').focus();
                }
            });
            $('[data-toggle="tooltip"]').tooltip();
        })
    </script>
</head>
<body>
<%@include file="menu.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Работники</h3>
                    <div class="pull-right">
							<span class="clickable filter" data-toggle="tooltip" title="Toggle table filter" data-container="body">
								<i class="glyphicon glyphicon-filter"></i>
							</span>
                    </div>
                </div>
                <div class="panel-body">
                    <input type="text" class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Поиск работников..." />
                </div>
                <table class="table table-hover" id="dev-table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Имя</th>
                        <th>Логин</th>
                        <th>Подразделение</th>
                        <th>Должность</th>
                        <th>Дата принятия на работу</th>
                        <th>Статус</th>
                        <th>Телефон</th>
                        <th>Почта</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="it" value="1"/>
                    <c:forEach var="list" items="${allWorkers}" begin="1" step="1" varStatus="status">
                        <tr>
                            <td>${status.index}</td>
                            <td>${list.nameWorker}   </td>
                            <td>${list.login}</td>
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
            </div>
        </div>
    </div>
</div>
</body>
</html>
