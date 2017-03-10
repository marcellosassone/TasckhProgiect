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
	<jsp:include page="TopDiv.jsp"></jsp:include>

	<div class="container">
		<div class="navbar" style="height:150px">
			<form action="/TasckhProgect/login" method="post">
				<button type="submit" title="Dashboard" class="btn-link-profile"></button>
			</form>

			<form action="/TasckhProgect/user/loadDoc" method="get">
				<button type="submit" title="Gestione Documenti"
					class="btn-link-document"></button>
			</form>

<%-- 			<form action="/TasckhProgect/admin/insertUser" method="get">
				<button type="submit" title="Inserimento nuovo utente"
					class="btn-link-newuser"></button>
			</form> --%>
			
			<form action="/TasckhProgect/logout" method="get">
				<button type="submit" title="Logout" class="btn-link-logout"></button>
			</form>

		</div>
		<div class="containerList">
			<h1>Lista Utenti</h1>
			<table class="rwd-table">
				<tr>

					<th style="width: 15%">Ruolo</th>
					<th style="width: 15%">Nome</th>
					<th style="width: 15%">Cognome</th>
					<th style="width: 15%">Mail</th>
					<th style="width: 15%">Password</th>
					<th style="width: -5%"></th>
					<th style="width: -5%"></th>
					<th style="width: -5%"></th>
					<th style="width: -5%"></th>

				</tr>
				<c:forEach var="user" items="${list}">
					<tr>

						<td data-th="Ruolo">${user.salutation}</td>
						<td data-th="Nome">${user.firstname}</td>
						<td data-th="Cognome">${user.lastname}</td>
						<td data-th="Mail">${user.email}</td>
						<td data-th="Password">${user.password}</td>
						<td data-th="TimeSheet"><form:form
								action="/TasckhProgect/admin/compileTimesheet/${user.id}"
								method="GET">
								<input Type="hidden" name="currMonth" value="${currMonth}">
								<input Type="hidden" name="currYear" value="${currYear}">
								<button type="submit" class="btn-link">
									<img src="/TasckhProgect/resources/img/time.png"
										title="Timesheet">
								</button>
							</form:form></td>
						<td data-th="Gestione Doc"><form:form
								action="/TasckhProgect/admin/loadDoc/${user.id}" method="POST">
								<button type="submit" class="btn-link">
	 								<img src="/TasckhProgect/resources/img/upload.png"
										title="Gestione Documenti">
								</button>
							</form:form></td>
						<td data-th="Edit"><form:form
								action="/TasckhProgect/admin/ModUser/${user.id}" method="POST">
								<button type="submit" class="btn-link">
									<img src="/TasckhProgect/resources/img/update.png"
										title="Modifica">
								</button>
							</form:form></td>
						<td data-th="Delete"><form:form
								action="/TasckhProgect/admin/delUser/${user.id}" method="POST">
								<button type="submit" class="btn-link"
									onClick='return confirm("sei sicuro di voler eliminare?")'>
									<img src="/TasckhProgect/resources/img/delete.png"
										title="Cancella">
								</button>
							</form:form></td>

					</tr>
				</c:forEach>
			</table>
			<br>
		</div>
		<br>
<form:form action="/TasckhProgect/admin/insertUser"
						method="GET">
<!-- 						<input type="submit" value="Insert" /> -->
						<button type="submit" class="button" >Inserisci nuovo</button>
					</form:form>
	</div>
</body>
</html>