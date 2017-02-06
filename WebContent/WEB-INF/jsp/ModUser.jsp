<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/TasckhProgect/resources/css/stile.css">
</head>
<body>
	<div class="form">

		
		<div >
			<div >
				<h1>Update User</h1>

				<form:form action="/TasckhProgect/finalizeUpdateUser" method="POST"
					commandName="formUserMod">
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="admin" />
					<div class="top-row">
						<div class="field-wrap">
							<label class="active"> Salutation<span class="req"></span>
							</label>
							<form:input type="text" path="salutation" />
						</div>
						<div class="field-wrap">
							<label class="active"> First Name<span class="req">*</span>
							</label>
							<form:input type="text" required="true" autocomplete="off"
								path="firstname" />
						</div>

						<div class="field-wrap">
							<label class="active"> Last Name<span class="req">*</span>
							</label>
							<form:input type="text" required="true" autocomplete="off"
								path="lastname" />
						</div>

						<div class="field-wrap">
							<label class="active"> Street<span class="req"></span>
							</label>
							<form:input type="text" path="street" />
						</div>
						<div class="field-wrap">
							<label class="active"> House Number<span class="req"></span>
							</label>
							<form:input type="text" path="housenumber" />
						</div>
						<div class="field-wrap">
							<label class="active"> City<span class="req"></span>
							</label>
							<form:input type="text" path="city" />
						</div>
						<div class="field-wrap">
							<label class="active"> Country<span class="req"></span>
							</label>
							<form:select path="country" required="true" autocomplete="off">
								<form:options items="${ListaCountry}" />
							</form:select>
						</div>
					</div>

					<div class="field-wrap">
						<label class="active"> Email Address<span class="req">*</span>
						</label>
						<form:input type="email" required="true" autocomplete="off"
							path="email" />
					</div>

					<div class="field-wrap">
						<label class="active"> Set A Password<span class="req">*</span>
						</label>
						<form:input type="password" required="true" autocomplete="off"
							path="password" />
					</div>

					<button type="submit" class="button button-block">Save Updates</button>

				</form:form>

			</div>
		</div>
	</div>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="/TasckhProgect/resources/js/script.js"></script>
</body>
</html>