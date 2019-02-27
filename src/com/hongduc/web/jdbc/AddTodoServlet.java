package com.hongduc.web.jdbc;

import java.io.IOException;
import java.time.LocalDate;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class AddTodoServlet
 */
@WebServlet("/AddTodo")
public class AddTodoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@Resource(name="jdbc/todolist")
	private DataSource dataSource;
	private TodolistDBUtil todolistDbUtil;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/addTodo.jsp").forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		todolistDbUtil = new TodolistDBUtil(dataSource);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String description = req.getParameter("description");
		Todo todo = new Todo(description,LocalDate.now());
		todolistDbUtil.addTodo(todo);
		resp.sendRedirect("/JAVAEE-TODOLIST/MainPage");
	}

}
