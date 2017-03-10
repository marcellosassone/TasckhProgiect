<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/TasckhProgect/resources/css/userList.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TopDiv</title>
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
				<img src="/TasckhProgect/user/loadAvatar/${sessionScope.id}"
					alt="Profile Avatar"
					style="height: 50px; width: 50px; -moz-border-radius: 9px 9px 9px 9px; -webkit-border-radius: 9px 9px 9px 9px; border-radius: 9px 9px 9px 9px">
			</div>
			<div class="divUserCont">
				<c:out value="${firstname} ${lastname}" />
			</div>
		</div>
	</div>
</body>
</html>