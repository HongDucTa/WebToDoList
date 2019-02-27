<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Main page</title>
	<link type="text/css" rel="stylesheet" href="css/session.css">
	<link type="text/css" rel="stylesheet" href="css/mainPage.css">
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
	<label>Todo List</label>
</div>
<c:if test="${account.getRole()=='instructor' }">
	<form action="AddTodo" method="get">
		<input id="add-button" type="submit" value="Add a todo">
	</form>
</c:if>
<div id="list-todo">
	<div id="content">
	<form action="MainPage" method="get">
	<table>
		<tr>
			<th>Description </th>
			<th id="th-date">Date</th>
			<c:if test = "${account.getRole()=='instructor'}">
				<th>Action</th>
			</c:if>
		</tr>
		<c:forEach var="todo" items="${todolist }" >
			<c:url var="EditLink" value="EditTodo">
				<c:param name="todoId" value="${todo.id}"/>
			</c:url>
			<c:url var="DeleteLink" value="DeleteTodo">
				<c:param name="todoId" value="${todo.id}"/>
			</c:url>
			<tr id="ligne">
				<td class="description"> ${todo.getDescription()}</td>
				<td class="date"> ${todo.getDate()}</td>
				<c:if test = "${account.getRole()=='instructor'}">
					<td> <a href="${EditLink }">Edit</a> | <a href="${DeleteLink }">Delete</a></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	</form>
	</div>
</div>
</body>
</html>