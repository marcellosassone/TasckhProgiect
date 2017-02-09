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
	<div><form method="GET" action="/TasckhProgect/user/compileTimesheet">
	<select name="currMonth" onchange="submit()">
	<option value="0">January</option>
	<option value="1">February</option>
	<option value="2">March</option>
	<option value="3">April</option>
	<option value="4">May</option>
	<option value="5">June</option>
	<option value="6">July</option>
	<option value="7">August</option>
	<option value="8">September</option>
	<option value="9">October</option>
	<option value="10">November</option>
	<option value="11">December</option>
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
				<td data-th="date">${daily.date}</td>
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