<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Edit to do</title>
	<link type="text/css" rel="stylesheet" href="css/session.css">
	<link type="text/css" rel="stylesheet" href="css/addTodo.css">
</head>
<body>
<div id="session">
	<div id="session-box">
		<label id="session-hello">Hello ${sessionScope.account.getUsername()}!<br>Logged in as ${sessionScope.account.getRole()}.</label>
		<form id="session-form" action="LogOut" method="get">
			<input id="session-button" type="submit" value="Log out">
		</form>
	</div>
</div>
<div id="title">
	<label>Add todo</label>
</div>
<form action="AddTodo" method="post">
	<div id="add-form">
		<label>Description :</label>
		<input id="add-input-description" type="text" name="description" value=""/>
		<input type="submit" value="Add">
	</div>
</form>
</body>
</html>