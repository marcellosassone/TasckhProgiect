<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TasckhProgect/resources/css/stile.css">


<title>Nuovo Utente</title>
</head>
<body>
	<div class="divHeader">
		<div class="logo">
			<img src="/TasckhProgect/resources/img/Logo1.png"
				style="height: 60%; width: 60%" />
		</div>
		<div class="divUserLog">
			<div class="divUserTitle">Benvenuto</div>
			<div class="divUserPhoto">
				<img src="/TasckhProgect/resources/img/avatar.jpg"
					alt="Profile Avatar"
					style="height: 50px; width: 50px; -moz-border-radius: 9px 9px 9px 9px; -webkit-border-radius: 9px 9px 9px 9px; border-radius: 9px 9px 9px 9px">
			</div>
			<div class="divUserCont">
				<c:out value="${firstname} ${lastname}" />
			</div>
		</div>
	</div>
	<div class="container">
		<%-- menu admin --%>
		<div class="navbar">
			<form action="/TasckhProgect/login" method="post">
				<button type="submit" title="Dashboard" class="btn-link-profile"></button>
			</form>

			<form action="/TasckhProgect/user/loadDoc" method="get">
				<button type="submit" title="Gestione Documenti"
					class="btn-link-document"></button>
			</form>
			
			<form action="/TasckhProgect/admin/load" method="get">
				<button type="submit" title="Lista Utenti" class="btn-link-contact"></button>
			</form>
			
			<form action="/TasckhProgect/logout" method="get">
				<button type="submit" title="Logout" class="btn-link-logout"></button>
			</form>

		</div>
		<div class="form">
			<div>
				<div>
					<h1>Crea Utente</h1>

					<form:form action="/TasckhProgect/insertUserFromAdmin"
						method="POST" commandName="formUserSignIn">

						<div class="top-row">
							<div class="field-wrap">
								<label> Posizione<span class="req"></span>
								</label>
								<form:input type="text" path="salutation" />
							</div>
							<div class="field-wrap">
								<label> Nome<span class="req">*</span>
								</label>
								<form:input type="text" required="true" autocomplete="off"
									path="firstname" />
							</div>

							<div class="field-wrap">
								<label> Cognome<span class="req">*</span>
								</label>
								<form:input type="text" required="true" autocomplete="off"
									path="lastname" />
							</div>
							<div class="field-wrap">
								<label> Codice fiscale<span class="req">*</span>
								</label>
								<form:input type="text" required="true" autocomplete="off"
									path="cf"
									pattern="^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$"
									oninvalid="this.setCustomValidity('Codice fiscale non corretto')" 
									style="text-transform:uppercase"/>


							</div>
							<div class="field-wrap">
								<label> Via<span class="req"></span>
								</label>
								<form:input type="text" path="street" />
							</div>
							<div class="field-wrap">
								<label> N° civico<span class="req"></span>
								</label>
								<form:input type="number" value="" path="housenumber" />
							</div>
							<div class="field-wrap">
								<label> CAP<span class="req">*</span>
								</label>
								<form:input type="text" required="true" autocomplete="off"
									path="cap" />

							</div>
							<div class="field-wrap">

								<label> Città<span class="req"></span>
								</label>
								<form:input type="text" path="city" />
							</div>
							<div class="field-wrap">
								<label class="active"> Nazione<span class="req"></span>
								</label>
								<form:select path="country" required="true" autocomplete="off">
									<form:option value="${null}" label="--- Select ---" />
									<form:options items="${ListaCountry}" />
								</form:select>
								<form:errors path="country" cssClass="error" />
							</div>
						</div>

						<div class="field-wrap">
							<label> Email<span class="req">*</span>
							</label>
							<form:input type="email" required="true" autocomplete="off"
								path="email" />
						</div>

						<div class="field-wrap">
							<label> Password<span class="req">*</span>
							</label>
							<form:input type="password" required="true" autocomplete="off"
								path="password" />
						</div>

						<button type="submit" class="button button-block">Salva</button>

					</form:form>

				</div>

			</div>
			<!-- tab-content -->
		</div>
	</div>
	<!-- /form -->
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

	<script src="/TasckhProgect/resources/js/script.js"></script>

</body>

</html>