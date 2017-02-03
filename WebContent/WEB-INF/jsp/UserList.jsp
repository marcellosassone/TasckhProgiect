<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista User</title>
</head>
<body>
<h1>Lista Users</h1>  
<table border="2" width="70%" cellpadding="2">  
<tr><th>Id</th><th>salutation</th><th>firstname</th><th>lastname</th><th>street</th><th>city</th><th>country</th><th>email</th><th>password</th><th>housenumber</th><th>admin</th><th>Edit</th><th>Delete</th></tr>  
  <c:forEach var="user" items="${list}">   
   <tr>  
    <td>${user.id}</td>  
    <td>${user.salutation}</td>  
    <td>${user.firstname}</td>  
    <td>${user.lastname}</td> 
    <td>${user.street}</td>
    <td>${user.city}</td>  
    <td>${user.country}</td>  
    <td>${user.email}</td>  
    <td>${user.password}</td>  
    <td>${user.housenumber}</td>
    <td>${user.admin}</td>  
    <td><a href="/TasckhProgect/admin/ModUser">Edit</a></td>  
   <!--  <td><button type="submit" onClick='confirm("Sei sicuro etcetc?")'>Delete </button></td> -->
    <td><a href="/TasckhProgect/admin/delUser"  onClick='return confirm("Sei sicuro di Voler Eliminare il Record?")'>Delete</a></td>
	   
   </tr>  
  </c:forEach>  
  </table>  
  <br/>  
  <a href="<c:url value="/logout" />">Logout</a>
</body>
</html>