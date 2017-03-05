<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/TasckhProgect/resources/css/stile.css">
</head>
<body>
<div class="divHeader">
	<div class="logo"><img src="/TasckhProgect/resources/img/Logo1.png"  style="height:60%;
	width:60%"/></div><div class="divUserLog"><div class="divUserTitle">Benvenuto</div><div class="divUserPhoto"><img src="/TasckhProgect/resources/img/avatar.jpg" alt="Profile Avatar" style="height:50px;width:50px;-moz-border-radius: 9px 9px 9px 9px;
  -webkit-border-radius: 9px 9px 9px 9px;border-radius: 9px 9px 9px 9px"></div><div class="divUserCont"><c:out value="${firstname} ${lastname}" /></div></div>
	</div>
	<div class="container">
	<div class="navbar">
								<form action="/TasckhProgect/login" method="post">
  									<button type="submit" title="Dashboard" class="btn-link-profile"></button>
								</form>
								
							
								<form action="/TasckhProgect/user/compileTimesheet" method="get">
  									<button type="submit" title="Timesheet"  class="btn-link-timesheet"></button>
								</form>
								
								<form action="/TasckhProgect/user/loadDoc" method="get">
  									<button type="submit" title="Gestione Documenti" class="btn-link-document"></button>
								</form>
									
								<form action="" method="get">
  									<button type="submit" title="Contact" class="btn-link-contact"></button>
								</form>
								
								<form action="/TasckhProgect/logout" method="get">
  									<button type="submit" title="Logout" class="btn-link-logout"></button>
								</form>							
								
							</div>
	<div class="container2">						
	<div class="form">

		
		<div >
			<div >
				<h1>Modifica Profilo</h1>

				<form:form action="/TasckhProgect/finalizeUpdateUser" method="POST"
					commandName="formUserMod">
					<form:input type="hidden" path="id" />
					<form:input type="hidden" path="admin" />
					<div class="top-row">
						<div class="field-wrap">
							<label class="active"> Posizione<span class="req"></span>
							</label>
							<form:input type="text" path="salutation" />
						</div>
						<div class="field-wrap">
							<label class="active"> Nome <span class="req">*</span>
							</label>
							<form:input type="text" required="true" autocomplete="off"
								path="firstname" />
						</div>

						<div class="field-wrap">
							<label class="active"> Cognome <span class="req">*</span>
							</label>
							<form:input type="text" required="true" autocomplete="off"
								path="lastname" />
						</div>
						<div class="field-wrap">
							<label class="active"> Codice fiscale<span class="req">*</span>
							</label>
							<form:input type="text" required="true" autocomplete="off"
								path="cf" pattern="^[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]$" oninvalid="this.setCustomValidity('Codice fiscale non corretto')"/>
								

						</div>
						<div class="field-wrap">
							<label class="active"> Indirizzo <span class="req"></span>
							</label>
							<form:input type="text" path="street" />
						</div>
						<div class="field-wrap">
							<label class="active"> N° civico <span class="req"></span>
							</label>
							<form:input type="number" path="housenumber" />
						</div>
						<div class="field-wrap">
							<label class="active"> CAP<span class="req">*</span>
							</label>
							<form:input type="text" required="true" autocomplete="off"
								path="cap" />
								
						</div>
						<div class="field-wrap">
							<label class="active"> Città <span class="req"></span>
							</label>
							<form:input type="text" path="city" />
						</div>
						<div class="field-wrap">
							<label class="active"> Nazione <span class="req"></span>
							</label>
							<form:select path="country" required="true" autocomplete="off">
								<form:options items="${ListaCountry}" />
							</form:select>
						</div>
					</div>

					<div class="field-wrap">
						<label class="active"> Email <span class="req">*</span>
						</label>
						<form:input type="email" required="true" autocomplete="off"
							path="email" />
					</div>

					<div class="field-wrap">
						<label class="active"> Password<span class="req">*</span>
						</label>
						<form:input type="password" required="true" autocomplete="off"
							path="password" />
					</div>

					<button type="submit" class="button button-block">Salva Modifiche</button>

				</form:form>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="/TasckhProgect/resources/js/script.js"></script>
</body>
</html>