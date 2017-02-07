<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<h1>
		Welcome,
		<c:out value="${firstname} ${lastname}" />
	</h1>
	<table>
	<tr>
		<td><form:form action="/TasckhProgect/user/ModUser" method="GET">

			<input type="submit" value="Update" />

		</form:form></td>

		<td><form:form action="/TasckhProgect/user/delUser" method="POST">
			<input type="submit" value="Delete"
				onClick='return confirm("sei sicuro di voler eliminare?")' />
		</form:form></td>
	

		<td><form:form action="/TasckhProgect/logout" method="GET">

			<input type="submit" value="Logout" />

		</form:form></td>

		</tr>
		</table>	
</body>

</body>
</html>