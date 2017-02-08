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
	<h1>Lista Users</h1>
	<table class="rwd-table">
		<tr>
			<th>Id</th>
			<th>Salutation</th>
			<th>Firstname</th>
			<th>Lastname</th>
			<th>Street</th>
			<th>N°</th>
			<th>City</th>
			<th>Country</th>
			<th>Mail</th>
			<th>Password</th>			
			<th>Admin</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="user" items="${list}">
			<tr>
				<td data-th="Id">${user.id}</td>
				<td data-th="Salutation">${user.salutation}</td>
				<td data-th="Firstname">${user.firstname}</td>
				<td data-th="Lastname">${user.lastname}</td>
				<td data-th="Street">${user.street}</td>
				<td data-th="N°">${user.housenumber}</td>
				<td data-th="City">${user.city}</td>
				<td data-th="Country">${listcountry.get(user.country)}</td>
				<td data-th="Mail">${user.email}</td>
				<td data-th="Password">${user.password}</td>
				<td data-th="Admin">${user.admin}</td>
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
</body>
</html>