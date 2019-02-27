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
 * Servlet implementation class EditTodoServlet
 */

@WebServlet("/EditTodo")
public class EditTodoServlet extends HttpServlet {
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
		Todo todo = todolistDbUtil.fetchTodoById(id);
		request.setAttribute("todo", todo);
		request.getRequestDispatcher("/editTodo.jsp").forward(request, response);
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		todolistDbUtil = new TodolistDBUtil(dataSource);
	}

	public EditTodoServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String description = req.getParameter("description");
		Todo todo = new Todo(id,description,LocalDate.now());
		todolistDbUtil.editTodo(todo);
		resp.sendRedirect("/JAVAEE-TODOLIST/MainPage");
	}
	
	

}
