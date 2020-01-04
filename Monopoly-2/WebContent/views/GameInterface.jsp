<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Interfaccia di gioco</title>
</head>
<body>

	<h3>Game ID: <c:out value="${game.gameId}" /></h3>

	<ul>
		<li>Username1: <c:out value="${game.player1.username}" /></li>
	</ul>
	
	<ul>
		<li>Username2: <c:out value="${game.player2.username}" /></li>
	</ul>
	
	<ul>
		<li>Username3: <c:out value="${game.player3.username}" /></li>
	</ul>
	
	<ul>
		<li>Username4: <c:out value="${game.player4.username}" /></li>
	</ul>

</body>
</html>