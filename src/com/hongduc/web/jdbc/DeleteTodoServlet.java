package com.hongduc.web.jdbc;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class DeleteTodoServlet
 */
@WebServlet("/DeleteTodo")
public class DeleteTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@Resource(name="jdbc/todolist")
	private DataSource dataSource;
	private TodolistDBUtil todolistDbUtil;
	int id;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id = Integer.parseInt(request.getParameter("todoId"));
		todolistDbUtil.deleteTodo(id);
		response.sendRedirect("/JAVAEE-TODOLIST/MainPage");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		todolistDbUtil = new TodolistDBUtil(dataSource);
	}

	
}
