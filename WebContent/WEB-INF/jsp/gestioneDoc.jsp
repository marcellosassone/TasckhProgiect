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
<div class="logo"><input type="image" SRC="/TasckhProgect/resources/img/Logo1.png" title="Back Home" style="height:60%;
	width:60%" onClick="history.go(-1);return true;"/></div>
	
	<h1>Gestione Documenti</h1>
	<div class="divTop">
	<form method="POST"
		action="/TasckhProgect/user/filtraDocumenti">
		
		<table class="rwd-table">
			<tr>
				<td><input type="text" name="ricerca" placeholder="Cerca..." /></td>
				<td><input type="submit" value="Cerca" /></td>
			</tr>
		</table>
	</form>

	<form method="POST" action="/TasckhProgect/user/finalizzaModificaDoc">

		<table class="rwd-table">
			<tr>
				
				<th>Data<a
					href="/TasckhProgect/user/sortDoc/date/${dateSort eq null or dateSort eq true ? 'ASC' : 'DESC'}"><img Title="Ordina" src="/TasckhProgect/resources/img/sort.png"></a></th>
				<th>Nome<a
					href="/TasckhProgect/user/sortDoc/name/${nameSort eq null or nameSort eq true ? 'ASC' : 'DESC'}"><img Title="Ordina" src="/TasckhProgect/resources/img/sort.png"></a></th>
				<th>Descrizione</th>
				<th>Download</th>
				<th>Modifica</th>
				<th>Cancella</th>
				
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
					<td><a href="/TasckhProgect/user/downloadDoc/${doc.id}"><img src="/TasckhProgect/resources/img/download.png" title="Download"></a></td>
					<td><a href="/TasckhProgect/user/updateDoc/${doc.id}"><img src="/TasckhProgect/resources/img/update.png" title="Update"></a></td>
					<td><a href="/TasckhProgect/user/deleteDoc/${doc.id}"
						onClick='return confirm("Sei sicuro di Voler Eliminare il file?")'><img src="/TasckhProgect/resources/img/delete.png" title="Cancella"></a></td>
				</tr>
			</c:forEach>
		</table>
		
		<input type="hidden" name="id_editabile" value="${id_editable}" />
		 <button type="submit" class="button button-block"> <img title="Salva Modifiche" src="/TasckhProgect/resources/img/saveUpdate.png">  </button>
	</form>
	</div>
	
	<h1>Upload file</h1>
	<div class="divBottom">
	<form:form method="POST" commandName="formDoc"
		action="/TasckhProgect/user/inserisciDoc" enctype="multipart/form-data">
		<table class="rwd-table">
			<tr>
				<td><form:label path="descrizione">Upload </form:label><input type="file" name="file" /></td>
			
			</tr>
			<tr>
				<td><form:label path="descrizione">Descrizione  </form:label><form:input path="descrizione" /></td>
				<td><input type="submit" value="Inserisci" /></td>
			</tr>
		</table>
		<br>
		<br>
		
	</form:form>
	</div>
</body>
</html>