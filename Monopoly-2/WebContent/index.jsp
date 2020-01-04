<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<html>

<head>
<meta charset="UTF-8">
<title>Interfaccia Iniziale</title>
</head>
<body>

	<h1>Registrati al sito</h1>
	
	<form action='<c:url value="/authentication/registration"></c:url>' method='post'>
		<label for='username'>Username:</label> 
		<input type='text' id='username' name='username' placeholder='Insert the username' required />
		<br>
		<br>
		<label for='password'>Password:</label> 
		<input type='text' id='password' name='password' placeholder='Insert the password' required />
		<br>
		<br>
		<button type='submit'>Registrazione</button>
	</form>

	<ul>
		<li>Output Registrazione: <c:out value="${outputR}" /></li>
	</ul>
	
	<h1>Effettua login</h1>
	
	<form action='<c:url value="/authentication/login"></c:url>' method='post'>
		<label for='username'>Username:</label> 
		<input type='text' id='username' name='username' placeholder='Insert the username' required />
		<br>
		<br>
		<label for='password'>Password:</label> 
		<input type='text' id='password' name='password' placeholder='Insert the password' required />
		<br>
		<br>
		<button type='submit'>Login</button>
	</form>
	
	<ul>
		<li>Stato login: <c:out value="${outputL}" /></li>
	</ul>

</body>
</html>