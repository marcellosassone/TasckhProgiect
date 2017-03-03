<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!doctype html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TasckhProgect/resources/css/userList.css">
<title>Lista User</title>
</head>
<body>
<div class="logo"><img src="/TasckhProgect/resources/img/Logo1.png"  style="height:60%;
	width:60%"/></div>
	<div class="container">
	<div class="navbar" style="height:150px">
								<form action="/TasckhProgect/login" method="post">
  									<button type="submit" title="Dashboard" class="btn-link-profile"></button>
								</form>
									
								<form action="/TasckhProgect/user/loadDoc" method="get">
  									<button type="submit" title="Gestione Documenti" class="btn-link-document"></button>
								</form>
								
								<form action="/TasckhProgect/logout" method="get">
  									<button type="submit" title="Logout" class="btn-link-logout"></button>
								</form>							
								
							</div>
	<div class="containerList">
	<h1>Lista Users</h1>
	<table class="rwd-table">
		<tr>
			
			
			<th>Nome</th>
			<th>Cognome</th>
			<th>Mail</th>
			<th>Password</th>			
			<th>Admin</th>
			<th>Timesheet</th>
			<th>Gestione Doc</th>
			<th>Edit</th>
			<th>Delete</th>
			
		</tr>
		<c:forEach var="user" items="${list}">
			<tr >
				
				
				<td data-th="Nome">${user.firstname}</td>
				<td data-th="Cognome">${user.lastname}</td>
				<td data-th="Mail">${user.email}</td>
				<td data-th="Password">${user.password}</td>
				<td data-th="Admin">${user.admin}</td>
				<td data-th="TimeSheet"><form:form action="/TasckhProgect/admin/compileTimesheet/${user.id}"
						method="GET">
						<input Type="hidden" name="currMonth" value ="${currMonth}">
						<input type="submit" value="TimeSheet" />
					</form:form></td>
				<td data-th="Gestione Doc"><form:form action="/TasckhProgect/admin/loadDoc/${user.id}"
						method="POST">
						<input type="submit" value="Documenti" />
					</form:form></td>
				<td data-th="Edit"><form:form action="/TasckhProgect/admin/ModUser/${user.id}"
						method="POST">
						<input type="submit" value="Update" />
					</form:form></td>
				<!--  <td><button type="submit" onClick='confirm("Sei sicuro etcetc?")'>Delete </button></td> -->
				<td data-th="Delete"><form:form action="/TasckhProgect/admin/delUser/${user.id}"
						method="POST">
						<input type="submit" value="Delete" onClick='return confirm("sei sicuro di voler eliminare?")' />
					</form:form></td>
					
			</tr>
		</c:forEach>
	</table>
	<br />
	<table>
	<tr>
	<td><form:form action="/TasckhProgect/admin/insertUser" method="GET">

			<input type="submit" value="Insert" />

		</form:form></td>
		<td><form:form action="/TasckhProgect/logout" method="GET">

			<input type="submit" value="Logout" />

		</form:form></td>
		</tr>
		</table>
		</div>
		</div>
</body>
</html>