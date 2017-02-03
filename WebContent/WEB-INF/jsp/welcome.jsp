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

<h1>Welcome, <c:out value="${firstname} ${lastname}"/></h1>

<form:form action="/TasckhProgect/user/ModUser" method="GET" >
          
          
          <input type="submit" value="Update"/>
          
          </form:form>

<td><a href="/TasckhProgect/user/ModUser">Edit</a></td>  
   <!--  <td><button type="submit" onClick='confirm("Sei sicuro etcetc?")'>Delete </button></td> -->
    <td><a href="/TasckhProgect/user/delUser"  onClick='return confirm("Are you sure you want to delete your profile?")'>Delete</a></td>
	<td><a href="<c:url value="/logout" />">Logout</a></td>
</body>

</body>
</html>