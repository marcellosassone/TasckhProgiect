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

		<div class="top-row">
			<div class="field-wrap">
				<label> Data<span class="req">*</span>
				</label>
				<form:input type="date" path="data" />
			</div>
			<div class="field-wrap">
				<label> Primo Turno<span class="req">*</span>
				</label>
				<table>
					<tr>
						<td>Inizio:</td>
						<td><form:input type="time" required="true"
								autocomplete="off" path="firstshiftstart" /></td>
					</tr>
					<tr>
						<td>Fine:</td>
						<td><form:input type="time" required="true"
								autocomplete="off" path="firstshiftstop" /></td>
					</tr>
				</table>

			</div>

			<div class="field-wrap">
				<label> Secondo Turno<span class="req">*</span>
				</label>
				<table>
					<tr>
						<td>Inizio:</td>
						<td><form:input type="time" required="true"
								autocomplete="off" path="secondshiftstart" /></td>
					</tr>
					<tr>
						<td>Fine:</td>
						<td><form:input type="time" required="true"
								autocomplete="off" path="secondshiftstop" /></td>
					</tr>
				</table>

			</div>

			<div class="field-wrap">
				<label> Cod. Permesso<span class="req"></span>
				</label>
				<form:input type="text" path="codpermesso" />
			</div>

			<form:select path="codpermesso" required="true" autocomplete="off">
				<form:option value="" label="--- Select ---" />
				<form:option value="P" label="Permesso (giornaliero)" />
				<form:option value="M" label="Malattia" />
				<form:option value="H" label="Permesso (orario)" />
				<form:option value="F" label="Ferie" />
			</form:select>
		</div>

		<button type="submit" class="button button-block">Salva Modifiche</button>

	</form:form>
</body>
</html>