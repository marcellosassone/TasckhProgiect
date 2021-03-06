<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TasckhProgect/resources/css/userList.css">
<title>Compile TimeSheet</title>
</head>
<body>
<%-- 
	<div class="divHeader">
		<div class="logo">
			<img src="/TasckhProgect/resources/img/Logo1.png"
				style="height: 60%; width: 60%" />
		</div>
		<div class="divUserLog">
			<div class="divUserTitle">Benvenuto</div>
			<div class="divUserPhoto">
				<img src="/TasckhProgect/user/downloadDoc/20"
					alt="Profile Avatar"
					style="height: 50px; width: 50px; -moz-border-radius: 9px 9px 9px 9px; -webkit-border-radius: 9px 9px 9px 9px; border-radius: 9px 9px 9px 9px">
			</div>
			<div class="divUserCont">
				<c:out value="${firstname} ${lastname}" />
			</div>
		</div>
	</div>
--%>
<jsp:include page="TopDiv.jsp"></jsp:include>
	<div class="container">
		<div class="navbar" style="height:150px; display:${hide ? 'none' : 'block'}">

			<form action="/TasckhProgect/login" method="post">
				<button type="submit" title="Dashboard" class="btn-link-profile"></button>
			</form>


 			<form action="/TasckhProgect/user/loadDoc" method="get">
				<button type="submit" title="Gestione Documenti"
					class="btn-link-document"></button>
			</form>

<%--		<form action="" method="post">
				<button type="submit" title="Contact" class="btn-link-contact"></button>
			</form>
 --%>
			<form action="/TasckhProgect/logout" method="get">
				<button type="submit" title="Logout" class="btn-link-logout"></button>
			</form>
		</div>
		<div class="navbar" style="display:${hide ? 'block' : 'none'}">

			<form action="/TasckhProgect/login" method="post">
				<button type="submit" title="Dashboard" class="btn-link-profile"></button>
			</form>


 			<form action="/TasckhProgect/user/loadDoc" method="get">
				<button type="submit" title="Gestione Documenti"
					class="btn-link-document"></button>
			</form>

			<form action="/TasckhProgect/admin/load" method="get">
						<button type="submit" title="Lista Utenti" class="btn-link-contact"></button>
			</form>
 
			<form action="/TasckhProgect/logout" method="get">
				<button type="submit" title="Logout" class="btn-link-logout"></button>
			</form>
		</div>
		<div class="containerDT">

			<div class="divTop" style="display:${hide ? 'none' : 'block'}">
				<h1>Compilazione TimeSheet</h1>
				<form:form action="/TasckhProgect/user/finalizeCompile"
					method="POST" commandName="formDailyTime">
					<table class="rwd-table">
						<tr>
							<th style="border-bottom: none"></th>
							<th colspan="2" style="border-bottom: none"><label>
									Primo turno<span class="req">*</span>
							</label></th>
							<th colspan="2" style="border-bottom: none"><label>
									Secondo turno<span class="req">*</span>
							</label></th>
							<th style="border-bottom: none"></th>
							<th style="border-bottom: none"></th>
						</tr>
						<tr>
							<th><label> Data<span class="req">*</span>
							</label></th>
							<th>Inizio</th>
							<th>Fine</th>
							<th>Inizio</th>
							<th>Fine</th>
							<th><label>Codice Permesso <span class="req"></span>
							</label></th>
						</tr>

						<tr>
							<td><form:input type="date" path="data" /></td>
							<td><form:input type="time" required="true"
									autocomplete="off" path="firstshiftstart" /></td>
							<td><form:input type="time" required="true"
									autocomplete="off" path="firstshiftstop" /></td>
							<td><form:input type="time" required="true"
									autocomplete="off" path="secondshiftstart" /></td>
							<td><form:input type="time" required="true"
									autocomplete="off" path="secondshiftstop" /></td>
							<td><form:select path="codpermesso" autocomplete="off">
									<form:option value="" label="--- Select ---" />
									<form:option value="P" label="Permesso (giornaliero)" />
									<form:option value="M" label="Malattia" />
									<form:option value="H" label="Permesso (orario)" />
									<form:option value="F" label="Ferie" />
								</form:select></td>

						</tr>

					</table>

					<button type="submit" class="">
						<img title="SAVE TIMESHEET"
							src="/TasckhProgect/resources/img/floppy.png">
					</button>

				</form:form>
			</div>
			<br> <br>

			<div class="divBottom">
				<h1>Timesheet Mensile<br>${user.firstname} ${user.lastname}</h1>
				<div style="width:320px;margin:auto">
				<form method="POST" action="/TasckhProgect/user/compileTimesheet">
				<input Type="hidden" name="idUser" value="${user.id}">
					<div id="mainselection" style="float: left">

						
							<select name="currMonth" onchange="submit()">
								<option value="0"
									<c:if test="${defaultMonth eq 0}">selected</c:if>>Gennaio</option>
								<option value="1"
									<c:if test="${defaultMonth eq 1}">selected</c:if>>Febbraio</option>
								<option value="2"
									<c:if test="${defaultMonth eq 2}">selected</c:if>>Marzo</option>
								<option value="3"
									<c:if test="${defaultMonth eq 3}">selected</c:if>>Aprile</option>
								<option value="4"
									<c:if test="${defaultMonth eq 4}">selected</c:if>>Maggio</option>
								<option value="5"
									<c:if test="${defaultMonth eq 5}">selected</c:if>>Giugno</option>
								<option value="6"
									<c:if test="${defaultMonth eq 6}">selected</c:if>>Luglio</option>
								<option value="7"
									<c:if test="${defaultMonth eq 7}">selected</c:if>>Agosto</option>
								<option value="8"
									<c:if test="${defaultMonth eq 8}">selected</c:if>>Settembre</option>
								<option value="9"
									<c:if test="${defaultMonth eq 9}">selected</c:if>>Ottobre</option>
								<option value="10"
									<c:if test="${defaultMonth eq 10}">selected</c:if>>Novembre</option>
								<option value="11"
									<c:if test="${defaultMonth eq 11}">selected</c:if>>Dicembre</option>
							</select>
						
					</div>
					<div id="mainselection">
						<select name="currYear" onchange="submit()">
							<option>${currYear}</option>
							<option>${prevYear}</option>
						</select>
					</div>
					</form>
				</div>
				<form:form action="/TasckhProgect/timesheetDownload/${id}"
					method="POST" commandName="formDailyTime">
					<input Type="hidden" name="currMonth" value="${currMonth}">
					<table class="rwd-table">
						<tr>
							<th rowspan="2" style="width: 15%"><label> Data<span class="req"></span>
							</label></th>
<!-- 							<th style="border-bottom: none"></th> -->
							<th colspan="2" style="border-bottom: none"><label>
									Primo Turno<span class="req"></span>
							</label></th>

							<th colspan="2" style="border-bottom: none"><label>
									Secondo Turno<span class="req"></span>
							</label></th>
							<th rowspan="2" style="width: -15%"><label>Cod. Permesso<span class="req"></span>
							</label></th>
<!-- 							<th style="border-bottom: none"></th> -->
							<th style="border-bottom: none"></th>
						</tr>

						<tr>
<!-- 							<th style="width: 15%"><label> Data<span class="req"></span> -->
<!-- 							</label></th> -->
							<th style="width: 15%">Inizio</th>
							<th style="width: 15%">Fine</th>
							<th style="width: 15%">Inizio</th>
							<th style="width: 15%">Fine</th>
<!-- 							<th rowspan="2" style="width: -15%"><label>Codice Permesso<span class="req"></span> -->
<!-- 							</label></th> -->
						</tr>

						<c:forEach var="daily" items="${listTimesheet}">
							<tr>
								<td data-th="date"><fmt:formatDate value="${daily.data}"
										pattern="E d" /></td>
								<td data-th="firstshiftstart"><c:out
										value="${fn:substring(daily.firstshiftstart, 0, 5)}" /></td>
								<td data-th="firstshiftstop"><c:out
										value="${fn:substring(daily.firstshiftstop, 0, 5)}" /></td>
								<td data-th="secondshiftstart"><c:out
										value="${fn:substring(daily.secondshiftstart, 0, 5)}" /></td>
								<td data-th="secondshiftstop"><c:out
										value="${fn:substring(daily.secondshiftstop, 0, 5)}" /></td>
								<td data-th="codpermesso">${daily.codpermesso}</td>
							</tr>
						</c:forEach>
					</table>
					<button type="submit" class=""
						style="margin-bottom: 30px;">
						<img title="Export Timesheet in Excel File"
							src="/TasckhProgect/resources/img/ms_excel.png">
					</button>

				</form:form>
			</div>
		</div>
	</div>
</body>
</html>