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
	
	<form  action='<c:url value="/authentication/logut"></c:url>' method='post'>
		<button type="submit">Logout</button>
	</form>
	
	<br>
	<br>
	<br>
	
	<form  action='<c:url value="/game/entry"></c:url>' method='post'>
		<input type="hidden" name="id" id="id" value="${user.id}"></input>
		<input type="hidden" name="username" id="username" value="${user.username}"></input>
		<button type="submit">New Game</button>
	</form>
	
	<form  action='<c:url value="/game/entry"></c:url>' method='get'>
		<input type="hidden" name="id" id="id" value="${user.id}"></input>
		<input type="hidden" name="username" id="username" value="${user.username}"></input>
		<label for='gameId'>Game ID:</label> 
		<input type='text' id='gameId' name='gameId' placeholder='Insert the game ID' required />
		<button type="submit">Join Game</button>
	</form>
	
	<ul>
		<li>Risultato ingresso partita: <c:out value="${outputGE}" /></li>
	</ul>

</body>
</html>