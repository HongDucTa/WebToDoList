<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<link type="text/css" rel="stylesheet" href="css/login.css">
</head>
<body>
<div id="login-title">
		<h2>WebToDoList</h2>
</div>
<div id="login-form">
	<form action="Login" method="post">
		<div id="labelUsername">
			<label>Username</label>
		</div>
		<input type="text" name="username" value="${username}" >
		<div id="labelPassword">
			<label>Password</label>
		</div>
		<input type="password" name="password">
		<div>
			<input id="login-button" type="submit" value="Login">
		</div>
		<div>
			<span class="erreur">${erreur}</span>
		</div>
	</form>
</div>
</body>
</html>