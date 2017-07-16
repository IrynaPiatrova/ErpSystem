<%--
  Created by IntelliJ IDEA.
  User: Roma
  Date: 14.07.2017
  Time: 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/taglib/tags.tld" %>
<html>
<head>
    <title>${chosenTicket.nameProjectTicket}</title>
    <meta charset="UTF-8">
    <%@include file="head.jsp" %>
    <%@include file="bootstrapLinks.jsp" %>
    <script type="text/javascript"></script>
    <style>
        <%@include file='../css/style_for_ticket_comments.css' %>
    </style>
    <script>
        function empty_form() {
            var txt = document.getElementById('msg').value;
            if (txt == '') {
                alert('Выберите сотрудника.');
                return false;
            }
            return true;
        }
    </script>
    <script>
        function empty_comment() {
            var txt = document.getElementById('commentMessage').value;
            if (txt == '') {
                alert('Напишите сообщение.');
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<%@include file="menu.jsp" %>
<%@include file="springMessages.jsp" %>

<div id="centerLayer">
    <h4>${nameTicket}: ${chosenTicket.nameProjectTicket}</h4>
    <table class="tableTicket">
        <th>${statusTicketChoose}</th>
        <th>${startDateTicket}</th>
        <th>${endDateTicket}</th>
        <th>${deadlineDate}</th>
        <th>${workerTicket}</th>
        <tr height="40">
            <td hidden>${chosenTicket.idProjectTicket}</td>
            <td width="120">${chosenTicket.statusProjectTicket}</td>
            <td width="120">${chosenTicket.startTicketDate}</td>
            <td width="120">${chosenTicket.endTicketDate}</td>
            <td width="120">${chosenTicket.deadlineTicket}
            <td width="120">${chosenTicket.worker.nameWorker}</td>
        </tr>
    </table>
    <table id="specificationTicket">
        <th>${specificationTicket}</th>
        <tr>
            <td>${chosenTicket.specification}</td>
        </tr>
    </table>
    <c:choose>
        <%--Проверяет есть ли у тикета комменты--%>
        <c:when test="${collectionOfComments.size() == 0}">
            <div>
                <h4>${noComments}</h4>
            </div>
        </c:when>
        <c:otherwise>
            <h4>${comments}</h4>
            <div id="allComments">
                <c:forEach items="${collectionOfComments}" var="listComment">
                    <div id="allCommentsPhoto">
                            <%--<c:choose>--%>
                            <%--<c:when test="${listComment.photo == null}">--%>
                            <%--<img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" class="img-responsive img-circle"--%>
                            <%--width="50px" height="50px">--%>
                            <%--</c:when>--%>
                            <%--<c:otherwise>--%>
                        <img src="<mytag:convertImage imageByte="${listComment.photo}"/>"
                             class="img-responsive img-circle"
                             width="50px" height="50px">
                            <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                    </div>
                    <div id="allCommentsComment">
                        <div>
                            <p style="color:blue">${listComment.nameWorker}</p>
                            <p>${listComment.commentDate} - ${listComment.comment}</p>
                            <hr>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
    <div id="form">
        <form action="${pageContext.request.contextPath}/writeComment" method="post" onsubmit="return empty_comment()">
            <div id="writeComment">
                <div id="comment_photo">
                    <%--<c:choose>--%>
                    <%--<c:when test="${workerPhoto == null}">--%>
                    <%--<img src="https://lut.im/7JCpw12uUT/mY0Mb78SvSIcjvkf.png" class="img-responsive img-circle"--%>
                    <%--width="50px" height="50px">--%>
                    <%--</c:when>--%>
                    <%--<c:otherwise>--%>
                    <img src="<mytag:convertImage imageByte="${workerPhoto}"/>" class="img-responsive img-circle"
                         width="50px" height="50px">
                    <%--</c:otherwise>--%>
                    <%--</c:choose>--%>
                </div>
                <div id="comment_comment">
                    <input type="text" name="idTicket" hidden="true" value="${chosenTicket.idProjectTicket}">
                    <textarea id="commentMessage" name="text_comment" cols="70" rows="2"
                              placeholder="${writeComments}"></textarea>
                </div>
                <div id="comment_submit">
                    <input type="image" value="${submit}"/>
                </div>
            </div>
        </form>
    </div>

    <%--Ниже идет проверка - если зашел админ, то отобразить кнопку назначения сотрудника,--%>
    <%--если работник, то отобразить кнопку завершить тикет--%>
    <%--Внутри еще 2 choose, которые проверяют:--%>
    <%---для админа - если назначен сотрудник, то повторно назначить нельзя--%>
    <%---для работника - если статус тикета "готов к проверке" или "завершен",--%>
    <%--то у него не будет кнопки завершить тикет--%>

    <c:choose>
        <c:when test="${isAdmin}">
            <c:choose>
                <c:when test="${isWorkerOnTicketNotChoosen == true}">
                    <div id="chooseWorker">
                        <form:form action="/chooseWorkerOnTicket" method="post" onsubmit="return empty_form()">
                            <input type="text" name="idTicket" hidden="true" value="${chosenTicket.idProjectTicket}">
                            <input list="workers" id="msg" name="nameWorker" placeholder="${employee}">
                            <datalist id="workers">
                                <c:forEach items="${collectionWorkers}" var="listWorker" begin="1" varStatus="status">
                                    <option>${status.index}.${listWorker.nameWorker}</option>
                                </c:forEach>
                            </datalist>
                            <input type="submit" value="${choseWorker}">
                        </form:form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="chooseWorker">
                        <h4>${disableChooseWorkerOnTicket}</h4>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${isTicketNotFinished == true}">
                    <div id="endTicket">
                        <form action="${pageContext.request.contextPath}/workerEndTicket" method="post">
                            <input type="text" name="idTicket" hidden="true" value="${chosenTicket.idProjectTicket}">
                            <input type="submit" value="${complete}">
                        </form>
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="endTicket">
                        <h4>${disableWorkerEndTicket}</h4>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
