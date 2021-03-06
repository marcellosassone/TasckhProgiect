<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/TasckhProgect/resources/css/userList.css">

<title>BEGEAR</title>
</head>

<body>
	<%--<div class="divHeader">
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
	</div> --%>
	<jsp:include page="TopDiv.jsp"></jsp:include>
	
	<div class="container">

		<%-- GESTIONE MENU ADMIN --%>
		<div class="navbar"
			style="height:150px; display:${hide ? 'block' : 'none'}">
			<form action="/TasckhProgect/login" method="post">
				<button type="submit" title="Dashboard" class="btn-link-profile"></button>
			</form>

<%--
			<form action="/TasckhProgect/user/loadDoc" method="get">
				<button type="submit" title="Gestione Documenti"
					class="btn-link-document"></button>
			</form> 
--%>

			<form action="/TasckhProgect/admin/load" method="get">
				<button type="submit" title="Lista Utenti" class="btn-link-contact"></button>
			</form>

			<form action="/TasckhProgect/logout" method="get">
				<button type="submit" title="Logout" class="btn-link-logout"></button>
			</form>

		</div>
		<%-- GESTIONE MENU USER --%>
		<div class="navbar" style="height:150px;display:${hide ? 'none' : 'block'}">
			<form action="/TasckhProgect/login" method="post">
				<button type="submit" title="Dashboard" class="btn-link-profile"></button>
			</form>

			<form action="/TasckhProgect/user/compileTimesheet" method="get">
				<button type="submit" title="Timesheet" class="btn-link-timesheet"></button>
			</form>
<%-- 
			<form action="/TasckhProgect/admin/load" method="get">
				<button type="submit" title="Lista Utenti" class="btn-link-contact"></button>
			</form>
--%>
			<form action="/TasckhProgect/logout" method="get">
				<button type="submit" title="Logout" class="btn-link-logout"></button>
			</form>

		</div>
		<div class="container2">
			<h1>
				Gestione Documenti<br>
				<c:out value="${nomeCognome}" />
			</h1>
			<div class="divTop">
				<form method="POST" action="/TasckhProgect/user/filtraDocumenti">

					<table class="rwd-table">
						<tr>
							<td><input type="text" name="ricerca" placeholder="Cerca..." /></td>
							<td><input type="submit" value="Cerca" /></td>
						</tr>
					</table>
				</form>

				<form method="POST"
					action="/TasckhProgect/user/finalizzaModificaDoc">

					<table class="rwd-table">
						<tr>

							<th>Data <a
								href="/TasckhProgect/user/sortDoc/date/${dateSort eq null or dateSort eq true ? 'ASC' : 'DESC'}?idUser=${idUser}"><img
									Title="Ordina" src="/TasckhProgect/resources/img/sort.png"></a></th>
							<th>Nome<a
								href="/TasckhProgect/user/sortDoc/name/${nameSort eq null or nameSort eq true ? 'ASC' : 'DESC'}?idUser=${idUser}"><img
									Title="Ordina" src="/TasckhProgect/resources/img/sort.png"></a></th>
							<th>Descrizione</th>
							<th style="width: 50px"></th>
							<th style="width: 50px"></th>
							<th style="width: 50px"></th>

						</tr>

						<c:forEach var="doc" items="${listaDoc}">
							<tr>

								<td>${doc.data}</td>
								<td>${doc.nome}</td>
								<td><c:choose>
										<c:when test="${editable eq true && id_editable eq doc.id}">
											<input type=text name=descr />
										</c:when>
										<c:otherwise>
											<c:out value="${doc.descrizione}" />
										</c:otherwise>
									</c:choose></td>
								<td><a href="/TasckhProgect/user/downloadDoc/${doc.id}"><img
										src="/TasckhProgect/resources/img/download.png"
										title="Download"></a></td>
								<td><a href="/TasckhProgect/user/updateDoc/${doc.id}"><img
										src="/TasckhProgect/resources/img/update.png" title="Modifica"></a></td>
								<td><a href="/TasckhProgect/user/deleteDoc/${doc.id}"
									onClick='return confirm("Sei sicuro di Voler Eliminare il file?")'><img
										src="/TasckhProgect/resources/img/delete.png" title="Cancella"></a></td>
							</tr>
						</c:forEach>
					</table>

					<input type="hidden" name="id_editabile" value="${id_editable}" />
					<button type="submit" class="">
						<img title="Salva Modifiche"
							src="/TasckhProgect/resources/img/saveUpdate.png">
					</button>
				</form>
				<div class="divBottom" style="display:${hide ? 'none' : 'block'}">
					<h1>Documenti Globali</h1>
					<table class="rwd-table">
						<tr>

							<th>Data<a
								href="/TasckhProgect/user/sortDoc/date/${dateSort eq null or dateSort eq true ? 'ASC' : 'DESC'}?idUser=${idUser}"><img
									Title="Ordina" src="/TasckhProgect/resources/img/sort.png"></a></th>
							<th>Nome<a
								href="/TasckhProgect/user/sortDoc/name/${nameSort eq null or nameSort eq true ? 'ASC' : 'DESC'}?idUser=${idUser}"><img
									Title="Ordina" src="/TasckhProgect/resources/img/sort.png"></a></th>
							<th>Descrizione</th>
							<th style="width: 50px"></th>


						</tr>
						<c:forEach var="doc" items="${listaDocAdmin}">
							<tr>

								<td>${doc.data}</td>
								<td>${doc.nome}</td>
								<td><c:choose>
										<c:when test="${editable eq true && id_editable eq doc.id}">
											<input type=text name=descr />
										</c:when>
										<c:otherwise>
											<c:out value="${doc.descrizione}" />
										</c:otherwise>
									</c:choose></td>
								<td><a href="/TasckhProgect/user/downloadDoc/${doc.id}"><img
										src="/TasckhProgect/resources/img/download.png"
										title="Download"></a></td>

							</tr>
						</c:forEach>
					</table>
				</div>

			</div>


			<div class="divBottom">
				<h1>Carica file</h1>
				
				<form:form method="POST" commandName="formDoc"
					action="/TasckhProgect/user/inserisciDoc"
					enctype="multipart/form-data">
					<form:label path="descrizione">Upload </form:label>
					<input type="file" name="file" />
					<input type="hidden" name="idUser" value="${idUser}" />
					
					<form:label path="tipo">Tipo documento  </form:label>					
					<form:select path="tipo"> 
						<form:option value="NONE" label="-- Select --" />
						<form:options items="${doctypeList}" />
					</form:select><br><br>
					
					<form:label path="descrizione">Descrizione  </form:label>
					<form:input path="descrizione" />

					<button type="submit" class="">
						<img title="Upload" src="/TasckhProgect/resources/img/upload2.png">
					</button>

					<br>
					<br>

				</form:form>
				</div>
			</div>
		</div>
</body>
</html>