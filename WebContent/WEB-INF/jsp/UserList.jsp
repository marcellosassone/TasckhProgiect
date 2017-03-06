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
<div class="divHeader">
	<div class="logo"><img src="/TasckhProgect/resources/img/Logo1.png"  style="height:60%;
	width:60%"/></div><div class="divUserLog"><div class="divUserTitle">Benvenuto</div><div class="divUserPhoto"><img src="/TasckhProgect/resources/img/avatar.jpg" alt="Profile Avatar" style="height:50px;width:50px;-moz-border-radius: 9px 9px 9px 9px;
  -webkit-border-radius: 9px 9px 9px 9px;border-radius: 9px 9px 9px 9px"></div><div class="divUserCont"><c:out value="${firstname} ${lastname}" /></div></div>
	</div>
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
	<h1>Lista Utenti</h1>
	<table class="rwd-table">
		<tr>
			
			<th>Ruolo</th>
			<th>Nome</th>
			<th>Cognome</th>
			<th>Mail</th>
			<th>Password</th>			
			<th style="width:50px"></th>
			<th style="width:50px"></th>
			<th style="width:50px"></th>
			<th style="width:50px"></th>
			
		</tr>
		<c:forEach var="user" items="${list}">
			<tr >
				
				<td data-th="Ruolo">${user.salutation}</td>
				<td data-th="Nome">${user.firstname}</td>
				<td data-th="Cognome">${user.lastname}</td>
				<td data-th="Mail">${user.email}</td>
				<td data-th="Password">${user.password}</td>
				<td data-th="TimeSheet"><form:form action="/TasckhProgect/admin/compileTimesheet/${user.id}"
						method="GET">
						<input Type="hidden" name="currMonth" value ="${currMonth}">
						<input Type="hidden" name="currYear" value ="${currYear}">
						<button type="submit" class="btn-link"><img src="/TasckhProgect/resources/img/time.png" title="Timesheet"></button>
					</form:form></td>
				<td data-th="Gestione Doc"><form:form action="/TasckhProgect/admin/loadDoc/${user.id}"
						method="POST">
						<button type="submit" class="btn-link"><img src="/TasckhProgect/resources/img/upload.png" title="Gestione Documenti"></button>
					</form:form></td>
				<td data-th="Edit"><form:form action="/TasckhProgect/admin/ModUser/${user.id}"
						method="POST">
						<button type="submit" class="btn-link"> <img src="/TasckhProgect/resources/img/update.png" title="Modifica">  </button>
					</form:form></td>
				<!--  <td><button type="submit" onClick='confirm("Sei sicuro etcetc?")'>Delete </button></td> -->
				<td data-th="Delete"><form:form action="/TasckhProgect/admin/delUser/${user.id}"
						method="POST">
						<button type="submit" class="btn-link" onClick='return confirm("sei sicuro di voler eliminare?")'><img src="/TasckhProgect/resources/img/delete.png" title="Cancella"></button>
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
		
		</tr>
		</table>
		</div>
		</div>
</body>
</html>