<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TasckhProgect/resources/css/userList.css">
<title>Compile TimeSheet</title>
</head>
<body>
	<h1>Compile TimeSheet</h1>
	<form:form action="/TasckhProgect/user/finalizeCompile" method="POST"
		commandName="formDailyTime">
		<table class="rwd-table">
		<tr>
				<th style="border-bottom:none"></th>
				<th style="border-bottom:none"><label> First Shift<span class="req">*</span>
				</label></th>
				<th style="border-bottom:none"></th>
				<th style="border-bottom:none"><label> Second Shift<span class="req">*</span>
				</label></th>
				<th style="border-bottom:none"></th>
				<th style="border-bottom:none"></th>
				

			    </tr>
			    <tr>
			    <th ><label> Date<span class="req">*</span>
				</label></th>
			    
			    <th>Start</th>
			    <th>End</th>
			    <th>Start</th>
				<th>End</th>
				<th ><label>Permit Code <span class="req"></span>
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
		
		<button type="submit" class="button button-block" style="position:absolute">Save Timesheet</button>

	</form:form>
	<br>
	<br>
	<br>
	<br>
	
	
	
	<h1>Monthly Timesheet</h1>
	<div><form method="POST" action="/TasckhProgect/user/compileTimesheet">
	<select name="currMonth" onchange="submit()">
	<option value="0" <c:if test="${defaultMonth eq 0}">selected</c:if> >January</option>
	<option value="1" <c:if test="${defaultMonth eq 1}">selected</c:if> >February</option>
	<option value="2" <c:if test="${defaultMonth eq 2}">selected</c:if> >March</option>
	<option value="3" <c:if test="${defaultMonth eq 3}">selected</c:if> >April</option>
	<option value="4" <c:if test="${defaultMonth eq 4}">selected</c:if> >May</option>
	<option value="5" <c:if test="${defaultMonth eq 5}">selected</c:if> >June</option>
	<option value="6" <c:if test="${defaultMonth eq 6}">selected</c:if> >July</option>
	<option value="7" <c:if test="${defaultMonth eq 7}">selected</c:if> >August</option>
	<option value="8" <c:if test="${defaultMonth eq 8}">selected</c:if> >September</option>
	<option value="9" <c:if test="${defaultMonth eq 9}">selected</c:if> >October</option>
	<option value="10" <c:if test="${defaultMonth eq 10}">selected</c:if> >November</option>
	<option value="11" <c:if test="${defaultMonth eq 11}">selected</c:if> >December</option>
	</select>
	</form></div>
	
	
	<table class="rwd-table">
	<tr>
		<th style="border-bottom:none"></th>
				<th style="border-bottom:none"><label> First Shift<span class="req">*</span>
				</label></th>
				<th style="border-bottom:none"></th>
				<th style="border-bottom:none"><label> Second Shift<span class="req">*</span>
				</label></th>
				<th style="border-bottom:none"></th>
				<th style="border-bottom:none"></th>
				

			    </tr>
			    
				
				<tr>
			    <th ><label> Date<span class="req">*</span>
				</label></th>
			    
			    <th>Start</th>
			    <th>End</th>
			    <th>Start</th>
				<th>End</th>
				<th ><label>Permit Code <span class="req"></span>
				</label></th>
				</tr>
		<c:forEach var="daily" items="${listTimesheet}">
			<tr >
				<td data-th="date">${daily.data}</td>
				<td data-th="firstshiftstart">${daily.firstshiftstart}</td>
				<td data-th="firstshiftstop">${daily.firstshiftstop}</td>
				<td data-th="secondshiftstart">${daily.secondshiftstart}</td>
				<td data-th="secondshiftstop">${daily.secondshiftstop}</td>
				<td data-th="codpermesso">${daily.codpermesso}</td>	
			</tr>
		</c:forEach>
	</table>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>