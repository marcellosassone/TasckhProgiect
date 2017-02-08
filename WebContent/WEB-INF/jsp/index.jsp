<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TasckhProgect/resources/css/stile.css">



<title>User Login</title>
</head>
<body>

	<div class="form">

		<ul class="tab-group">
			<li class="tab active"><a href="#login">Log In</a></li>
			<li class="tab"><a href="#signup">Sign Up</a></li>
		</ul>

		<div class="tab-content">


			<div id="login">
				<h1>Login!</h1>
				<p style="color: white">
					<c:out value="${errore}" />
				</p>

				<form:form action="/TasckhProgect/login" method="POST"
					commandName="formUser">

					<div class="field-wrap">
						<label> Email Address<span class="req">*</span>
						</label>
						<form:input type="text" path="email" />
					</div>

					<div class="field-wrap">
						<label> Password<span class="req">*</span>
						</label>
						<form:input type="password" path="password" />
					</div>

					<p class="forgot">
						<a href="#">Forgot Password?</a>
					</p>

					<button class="button button-block">Log In</button>

				</form:form>
			</div>

			<div id="signup">
				<h1>Sign Up</h1>

				<form:form action="/TasckhProgect/insertUser" method="POST"
					commandName="formUserSignIn">

					<div class="top-row">
						<div class="field-wrap">
							<label> Salutation<span class="req"></span>
							</label>
							<form:input type="text" path="salutation" />
						</div>
						<div class="field-wrap">
							<label> First Name<span class="req">*</span>
							</label>
							<form:input type="text" required="true" autocomplete="off"
								path="firstname" />
						</div>

						<div class="field-wrap">
							<label> Last Name<span class="req">*</span>
							</label>
							<form:input type="text" required="true" autocomplete="off"
								path="lastname" />
						</div>

						<div class="field-wrap">
							<label> Street<span class="req"></span>
							</label>
							<form:input type="text" path="street" />
						</div>
						<div class="field-wrap">
							<label> House Number<span class="req"></span>
							</label>
							<form:input type="number" value="" path="housenumber" />
						</div>
						<div class="field-wrap">
							<label> City<span class="req"></span>
							</label>
							<form:input type="text" path="city" />
						</div>
						<div class="field-wrap">
							<label class="active"> Country<span class="req"></span>
							</label> 
							<form:select path="country" required="true" autocomplete="off">
								<form:option value="${null}" label="--- Select ---" />
								<form:options items="${ListaCountry}" />
							</form:select>
							<form:errors path="country" cssClass="error" />
						</div>
					</div>

					<div class="field-wrap">
						<label> Email Address<span class="req">*</span>
						</label>
						<form:input type="email" required="true" autocomplete="off"
							path="email" />
					</div>

					<div class="field-wrap">
						<label> Set A Password<span class="req">*</span>
						</label>
						<form:input type="password" required="true" autocomplete="off"
							path="password" />
					</div>

					<button type="submit" class="button button-block">Get Started</button>

				</form:form>

			</div>

		</div>
		<!-- tab-content -->

	</div>
	<!-- /form -->
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="/TasckhProgect/resources/js/script.js"></script>

</body>

</html>