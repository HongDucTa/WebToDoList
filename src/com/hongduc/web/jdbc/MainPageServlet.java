package com.hongduc.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class MainPageServlet
 */
@WebServlet("/MainPage")
public class MainPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	@Resource(name="jdbc/todolist")
	private DataSource dataSource;
	TodolistDBUtil todolistdb;
	
	HttpSession session;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		todolistdb = new TodolistDBUtil(dataSource);
		try {
			listTodo(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void init() throws ServletException
	{
		super.init();
		todolistdb = new TodolistDBUtil(dataSource);
	}
	protected void listTodo(HttpServletRequest request, HttpServletResponse response) throws Exception
	{/* Récupère la liste des to-dos et les affiche dans la page */
		HttpSession session = request.getSession();
		List<Todo> todolist = todolistdb.getTodos();
		session.setAttribute("todolist", todolist);
		request.getRequestDispatcher("/mainPage.jsp").forward(request, response);
	}
}
