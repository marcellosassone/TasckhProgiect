<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TasckhProgect/resources/css/userList.css">
<title>Compile TimeSheet</title>
</head>
<body>

	
	<div class="logo"><input type="image" SRC="/TasckhProgect/resources/img/Logo1.png" title="Back Home" style="height:60%;
	width:60%"/></div>
	<div class="container">
	<div class="navbar">
								
								<form action="/TasckhProgect/login" method="post">
  									<button type="submit" title="Dashboard" class="btn-link-profile"></button>
								</form>
								
							
								<form action="/TasckhProgect/user/loadDoc" method="get">
  									<button type="submit" title="Gestione Documenti" class="btn-link-document"></button>
								</form>
									
								<form action="" method="post">
  									<button type="submit" title="Contact" class="btn-link-contact"></button>
								</form>
								
								<form action="/TasckhProgect/logout" method="get">
  									<button type="submit" title="Logout" class="btn-link-logout"></button>
								</form>		
							</div>
	
	<div class="containerDT">
	
	<div class="divTop" style="display:${hide ? 'none' : 'block'}">
	<h1>Compilazione TimeSheet</h1>
	<form:form action="/TasckhProgect/user/finalizeCompile" method="POST"
		commandName="formDailyTime">
		<table class="rwd-table">
			<tr>
				<th style="border-bottom: none"></th>
				<th colspan="2" style="border-bottom: none"><label> Primo turno<span
						class="req">*</span>
				</label></th>
				<th colspan="2" style="border-bottom: none"><label> Secondo turno<span
						class="req">*</span>
				</label></th>
				<th style="border-bottom: none"></th>
				<th style="border-bottom: none"></th>
			</tr>
			<tr>
				<th><label> Data<span class="req">*</span>
				</label></th>
				<th>Start</th>
				<th>End</th>
				<th>Start</th>
				<th>End</th>
				<th><label>Codice Permesso <span class="req"></span>
				</label></th>
			</tr>

			<tr>
				<td><form:input type="date" path="data" /></td>
				<td><form:input type="time" required="true" autocomplete="off"
						path="firstshiftstart" /></td>
				<td><form:input type="time" required="true" autocomplete="off"
						path="firstshiftstop" /></td>
				<td><form:input type="time" required="true" autocomplete="off"
						path="secondshiftstart" /></td>
				<td><form:input type="time" required="true" autocomplete="off"
						path="secondshiftstop" /></td>
				<td><form:select path="codpermesso" autocomplete="off">
						<form:option value="" label="--- Select ---" />
						<form:option value="P" label="Permesso (giornaliero)" />
						<form:option value="M" label="Malattia" />
						<form:option value="H" label="Permesso (orario)" />
						<form:option value="F" label="Ferie" />
					</form:select></td>

			</tr>

		</table>

		<button type="submit" class="button button-block"> <img title="SAVE TIMESHEET" src="/TasckhProgect/resources/img/floppy.png">  </button>

	</form:form>
	</div>
	<br>
	<br>

	<div class="divBottom">
	<h1>Timesheet Mensile</h1>
	<div id="mainselection">
	
		<form method="POST" action="/TasckhProgect/user/compileTimesheet">
			<select name="currMonth" onchange="submit()">
				<option value="0" <c:if test="${defaultMonth eq 0}">selected</c:if>>January</option>
				<option value="1" <c:if test="${defaultMonth eq 1}">selected</c:if>>February</option>
				<option value="2" <c:if test="${defaultMonth eq 2}">selected</c:if>>March</option>
				<option value="3" <c:if test="${defaultMonth eq 3}">selected</c:if>>April</option>
				<option value="4" <c:if test="${defaultMonth eq 4}">selected</c:if>>May</option>
				<option value="5" <c:if test="${defaultMonth eq 5}">selected</c:if>>June</option>
				<option value="6" <c:if test="${defaultMonth eq 6}">selected</c:if>>July</option>
				<option value="7" <c:if test="${defaultMonth eq 7}">selected</c:if>>August</option>
				<option value="8" <c:if test="${defaultMonth eq 8}">selected</c:if>>September</option>
				<option value="9" <c:if test="${defaultMonth eq 9}">selected</c:if>>October</option>
				<option value="10"
					<c:if test="${defaultMonth eq 10}">selected</c:if>>November</option>
				<option value="11"
					<c:if test="${defaultMonth eq 11}">selected</c:if>>December</option>
			</select>
		</form>
	</div>
	<form:form action="/TasckhProgect/timesheetDownload/${id}" method="POST"
		commandName="formDailyTime">
		<input Type="hidden" name="currMonth" value ="${currMonth}">
	<table class="rwd-table">
		<tr>
			<th style="border-bottom: none"></th>
			<th colspan="2" style="border-bottom: none"><label> First Shift<span
					class="req"></span>
			</label></th>
			
			<th colspan="2" style="border-bottom: none"><label> Second Shift<span
					class="req"></span>
			</label></th>
			<th style="border-bottom: none"></th>
			<th style="border-bottom: none"></th>
		</tr>

		<tr>
			<th><label> Date<span class="req"></span>
			</label></th>
			<th>Start</th>
			<th>End</th>
			<th>Start</th>
			<th>End</th>
			<th><label>Permit Code <span class="req"></span>
			</label></th>
		</tr>
		
		<c:forEach var="daily" items="${listTimesheet}">
			<tr>
				<td data-th="date"><fmt:formatDate value="${daily.data}"  pattern="E d"/></td>
				<td data-th="firstshiftstart"><c:out value="${fn:substring(daily.firstshiftstart, 0, 5)}" /></td>
				<td data-th="firstshiftstop"><c:out value="${fn:substring(daily.firstshiftstop, 0, 5)}" /></td>
				<td data-th="secondshiftstart"><c:out value="${fn:substring(daily.secondshiftstart, 0, 5)}" /></td>
				<td data-th="secondshiftstop"><c:out value="${fn:substring(daily.secondshiftstop, 0, 5)}" /></td>
				<td data-th="codpermesso">${daily.codpermesso}</td>
			</tr>
		</c:forEach>
	</table>
	<button type="submit" class="button button-block"> <img title="Export Timesheet in Excel File" src="/TasckhProgect/resources/img/ms_excel.png">  </button>
	
	</form:form>
	</div>
	</div>
	</div>
</body>
</html>