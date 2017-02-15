<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<html>
<head>
  <meta charset="UTF-8">
  <title>login form</title>
  
  <link rel="stylesheet" href="/TasckhProgect/resources/css/styleIndex.css">
 

</head>

<body>

  
<div class="login">
  <header class="login-header"><span class="text">LOGIN</span><span class="loader"></span></header>
  <c:out value="${errore}" />
				<form:form action="/TasckhProgect/login" method="POST"
					commandName="formUser">
					<img src="/TasckhProgect/resources/img/Logo1.png" style="width:50%;height:50%;padding-left:10px; margin-bottom:15px">
    <form:input class="login-input" type="text" placeholder="email" path="email"/>
    <form:input class="login-input" type="password" placeholder="password" path="password"/>
    <button class="login-btn">login</button>
  </form:form>
</div>
      <script src="/TasckhProgect/resources/js/index.js"></script>      

</body>
</html>
