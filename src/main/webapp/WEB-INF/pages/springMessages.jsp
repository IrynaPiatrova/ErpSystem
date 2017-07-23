<%--
  Created by IntelliJ IDEA.
  User: klinster
  Date: 15.07.2017
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="username" var="username"/>
<spring:message code="password" var="password"/>
<spring:message code="profile.position" var="profilePosition"/>
<spring:message code="profile.position.description" var="profilePositionDescription"/>
<spring:message code="profile.position.choose" var="profilePositionChoose"/>
<spring:message code="profile.position.juniordeveloper" var="profilePositionJuniordeveloper"/>
<spring:message code="profile.position.middleDeveloper" var="profilePositionMiddleDeveloper"/>
<spring:message code="profile.position.seniorDeveloper" var="profilePositionSeniorDeveloper"/>
<spring:message code="profile.position.QAEngineer" var="profilePositionQAEngineer"/>
<spring:message code="profile.position.designer" var="profilePositionDesigner"/>
<spring:message code="profile.department" var="profileDepartment"/>
<spring:message code="profile.department.description" var="profileDepartmentDescription"/>
<spring:message code="profile.department.choose" var="profileDepartmentChoose"/>
<spring:message code="profile.department.developers" var="profileDepartmentDevelopers"/>
<spring:message code="profile.department.testers" var="profileDepartmentTesters"/>
<spring:message code="profile.department.designers" var="profileDepartmentDesigners"/>
<spring:message code="profile.employmentStatus" var="profileEmploymentStatus"/>
<spring:message code="profile.employmentStatus.description" var="profileEmploymentStatusDescription"/>
<spring:message code="profile.employmentStatus.choose" var="profileEmploymentStatusChoose"/>
<spring:message code="profile.employmentStatus.1" var="profileEmploymentStatus1"/>
<spring:message code="profile.employmentStatus.2" var="profileEmploymentStatus2"/>
<spring:message code="profile.employmentStatus.3" var="profileEmploymentStatus3"/>
<spring:message code="profile.employmentStatus.4" var="profileEmploymentStatus4"/>
<spring:message code="profile.employmentStatus.5" var="profileEmploymentStatus5"/>
<spring:message code="profile.telephone" var="profileTelephone"/>
<spring:message code="profile.telephone.description" var="profileTelephoneDescription"/>
<spring:message code="profile.telephone.new" var="profileTelephoneNew"/>
<spring:message code="profile.email" var="profileEmail"/>
<spring:message code="profile.email.description" var="profileEmailDescription"/>
<spring:message code="profile.email.new" var="profileEmailNew"/>
<spring:message code="profile.startDate" var="profileStartDate"/>
<spring:message code="profile.startDate.description" var="profileStartDateDescription"/>
<spring:message code="profile.photo" var="profilePhoto"/>
<spring:message code="profile.photo.new" var="profilePhotoNew"/>
<spring:message code="profile.edit" var="profileEdition"/>
<spring:message code="profile.edit.before" var="profileEditionBefore"/>
<spring:message code="profile.edit.after" var="profileEditionAfter"/>
<spring:message code="worker.name" var="workerName"/>
<spring:message code="worker.name.description" var="workerNameDescription"/>
<spring:message code="worker.name.new" var="workerNameNew"/>
<spring:message code="worker.login" var="workerLogin"/>
<spring:message code="worker.login.description" var="workerLoginDescription"/>
<spring:message code="worker.login.new" var="workerLoginNew"/>
<spring:message code="worker.password" var="workerPassword"/>
<spring:message code="worker.password.new" var="workerPasswordNew"/>
<spring:message code="worker.end.ticket" var="completeTicket"/>
<spring:message code="admin.reject.ticket" var="rejectTicket"/>
<spring:message code="admin.reject.vacation" var="rejectVacation"/>
<spring:message code="admin.accept.vacation" var="acceptVacation"/>
<spring:message code="allWorkers.involved" var="allWorkersInvolved"/>
<spring:message code="ticket.name" var="nameTicket"/>
<spring:message code="ticket.specification" var="specificationTicket"/>
<spring:message code="ticket.specification.description" var="specificationTicketDescription"/>
<spring:message code="ticket.status" var="statusTicket"/>
<spring:message code="ticket.status.description" var="statusTicketDescription"/>
<spring:message code="ticket.status.choose" var="statusTicketChoose"/>
<spring:message code="ticket.status.opened" var="statusTicketOpened"/>
<spring:message code="ticket.status.inProgress" var="statusTicketInProgress"/>
<spring:message code="ticket.status.paused" var="statusTicketPaused"/>
<spring:message code="ticket.status.readyForTest" var="statusTicketReadyForTest"/>
<spring:message code="ticket.status.finished" var="statusTicketFinished"/>
<spring:message code="ticket.startDate" var="startDateTicket"/>
<spring:message code="ticket.endDate" var="endDateTicket"/>
<spring:message code="ticket.deadline" var="deadlineTicket"/>
<spring:message code="allTickets.ticket.name" var="nameTicket"/>
<spring:message code="allTickets.ticket.specification" var="specificationTicket"/>
<spring:message code="allTickets.ticket.status.allTickets" var="allTickets"/>
<spring:message code="allTickets.ticket.label.show" var="labelShow"/>
<spring:message code="allTickets.ticket.startDate" var="startDateTicket"/>
<spring:message code="allTickets.ticket.endDate" var="endDateTicket"/>
<spring:message code="allTickets.ticket.comments" var="comments"/>
<spring:message code="allTickets.ticket.noComments" var="noComments"/>
<spring:message code="allTickets.ticket.writeComments" var="writeComments"/>
<spring:message code="allTickets.ticket.employee" var="employee"/>
<spring:message code="allTickets.ticket.choseWorker" var="choseWorker"/>
<spring:message code="allTickets.ticket.deadline" var="deadlineDate"/>
<spring:message code="allTickets.ticket.worker" var="workerTicket"/>
<spring:message code="allTickets.ticket.perfomance" var="workerTicketPerfomance"/>
<spring:message code="allTicket.ticket.disableWorkerEndTicket" var="disableWorkerEndTicket"/>
<spring:message code="allTicket.ticket.disableChooseWorkerOnTicket" var="disableChooseWorkerOnTicket"/>
<spring:message code="label.authorization" var="labelAuthorization"/>
<spring:message code="label.complete" var="labelComplete"/>
<spring:message code="label.next" var="labelNext"/>
<spring:message code="label.back" var="labelBack"/>
<spring:message code="label.submit" var="submit"/>
<spring:message code="label.menu.logout" var="labelLogOut"/>
<spring:message code="label.menu.main" var="labelMain"/>
<spring:message code="label.menu.workers" var="labelWorkersChoose"/>
<spring:message code="label.menu.workers.add" var="labelWorkersAdd"/>
<spring:message code="label.menu.workers.all" var="labelWorkersAll"/>
<spring:message code="label.menu.profile" var="labelProfile"/>
<spring:message code="label.menu.tickets" var="labelTicketsChoose"/>
<spring:message code="label.menu.tickets.add" var="labelTicketsAdd"/>
<spring:message code="label.menu.tickets.all" var="labelTicketsAll"/>
<spring:message code="label.menu.tickets.my" var="labelTicketsMy"/>
<spring:message code="label.menu.chat" var="labelChat"/>
<spring:message code="label.toggle.filter" var="labelToggleFilter"/>
<spring:message code="label.searching.input" var="labelSearchInput"/>
<spring:message code="label.edit.worker.button" var="labelEditWorkerButton"/>
<spring:message code="label.show.worker.button" var="labelShowWorkerButton"/>
<spring:message code="label.show.worker" var="labelShowWorker"/>
<spring:message code="label.actual.ticket" var="labelActualTicket"/>
<spring:message code="label.actual.ticket.no" var="labelActualTicketNo"/>
<spring:message code="label.ticket.no" var="labelTicketNo"/>
<spring:message code="label.notAppointedAndReady.tickets" var="labelNotAppointedAndReadyTickets"/>
<spring:message code="label.vacation.type" var="labelVacationType"/>
<spring:message code="label.request.sickLeave" var="labelRequestSickLeave"/>
<spring:message code="label.request.vacation" var="labelRequestVacation"/>
<spring:message code="label.createRequest" var="labelCreateRequest"/>
<spring:message code="label.createRequestChoose" var="labelCreateRequestChoose"/>
<spring:message code="label.requests" var="labelRequests"/>
<spring:message code="label.requests" var="labelRequests"/>
<spring:message code="vacation.start" var="absenceStart"/>
<spring:message code="vacation.end" var="absenceEnd"/>
<spring:message code="label.vacation.no" var="labelVacationNo"/>
<spring:message code="chat.message.input.name" var="inputMessage"/>


