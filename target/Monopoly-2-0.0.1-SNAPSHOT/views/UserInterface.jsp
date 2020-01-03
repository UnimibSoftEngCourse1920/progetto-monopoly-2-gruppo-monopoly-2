<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<meta charset="UTF-8">
<title>Interfaccia Utente</title>
</head> 
<body>

	<h1>Interfaccia Utente</h1>
	

	<ul>
		<li>ID: <c:out value="${user.id}" /></li>
		<li>Username: <c:out value="${user.username}" /></li>
	</ul>
	
	<form  action='<c:url value="/game/entrygame"></c:url>' method='post'>
		<input type="hidden" name="id" id="id" value="${user.id}"></input>
		<input type="hidden" name="username" id="username" value="${user.username}"></input>
		<button type="submit">New Game</button>
	</form>

</body>
</html>